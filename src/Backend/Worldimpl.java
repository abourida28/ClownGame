package Backend;

import Backend.Objects.*;
import eg.edu.alexu.csd.oop.game.World;

import java.util.Queue;
import java.util.Stack;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;

import static java.lang.Math.abs;

public class Worldimpl implements World {

    private int score = 0;
    private static int MAX_Time = 60 * 1000;
    private final int width;
    private final int height;
    private long endTime, startTime = System.currentTimeMillis();
    private final List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();
    private final List<GameObject> constants = new LinkedList<GameObject>();
    private final RandomShapeGenerator randomShapeGenerator;
    private int clock;

    public Worldimpl(int screenWidth, int screenHeight, RandomShapeGenerator randomShapeGenerator) {
        clock = 0;
        this.randomShapeGenerator = randomShapeGenerator;
        width = screenWidth;
        height = screenHeight;
        control.add(new Clown(width / 2, height - 79, 64, true, Color.BLUE));
        for (int i = 0; i < 10; i++) {
            int posX = (int) (Math.random() * width);
            int posY = -1 * (int) (Math.random() * height);
            AbstractShape randomShape = randomShapeGenerator.createRandomShape(posX, posY);
            moving.add(randomShape);
        }

    }

    private boolean intersectLeft(AbstractShape s1, Clown s2) {
        double distanceX = (s1.getX() + s1.getWidth() / 2.0 - (s2.getX() + s2.getWidth() / 2.0));
        double distanceY = s1.getY() + s1.getHeight() / 2.0 - (s2.getY() + s2.getHeight() / 2.0);
        double actualDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        return (distanceX < 0.0 && distanceX <= s2.getWidth() / 2.0 + s2.getWidth() / 4.0) && distanceY <= s1.getHeight() / 2.0 && actualDistance <= s1.getWidth() / 2.0 + s2.getWidth() / 4.0;
    }
    private boolean intersectWithTop(AbstractShape s1, AbstractShape s2) {
        double distanceX = (s1.getX() + s1.getWidth())- s2.getX()-s2.getWidth();
        double distanceY = s1.getY() + s1.getHeight() / 2.0 - (s2.getY() + s2.getHeight() / 2.0);
        double actualDistance = Math.sqrt((distanceX) * (distanceX) + distanceY * distanceY);
        return (distanceX >-s1.getWidth() && distanceX <= s2.getWidth()) && distanceY <= s1.getHeight() / 2.0 && actualDistance <= s1.getWidth() / 2.0 + s2.getWidth() / 4.0;

    }
    private boolean clownLeftRange(AbstractShape s1, Clown s2) {
        double distanceX = (s1.getX() + s1.getWidth()/2.0 - (s2.getX() + s2.getWidth()/2.0));
        return (distanceX<0.0&&distanceX <=  s2.getWidth()/2.0+ s2.getWidth());
    }
    private boolean clownRightRange(AbstractShape s1, Clown s2)
    {
        double distanceX = (s1.getX() + s1.getWidth()/2.0 - (s2.getX() + s2.getWidth()/2.0));
        return (distanceX>0.0&&distanceX <=  s2.getWidth()/2.0+ s2.getWidth());
    }
    private boolean intersectClown(AbstractShape s1, Clown s2){
        double distanceX = (s1.getX() + s1.getWidth()/2.0 - (s2.getX() + s2.getWidth()/2.0));
        double distanceY = s1.getY() + s1.getHeight()/2.0 - (s2.getY() + s2.getHeight()/2.0);
        double actualDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        boolean intersectsLeftHand = intersectLeft(s1, s2);
        boolean intersectsRightHand = !intersectsLeftHand && distanceY <=  s1.getHeight()/2.0 && actualDistance <= s1.getWidth()/2.0 + s2.getWidth()/4.0;

        if (intersectsLeftHand) {
            s2.addToLeftHand(s1);
        } else if (intersectsRightHand) {
            s2.addToRightHand(s1);
        }

        return intersectsLeftHand || intersectsRightHand;
    }


    private boolean intersect(AbstractShape s1, Clown s2)
    {
        if(s1.getFallingSpeed()==0)
            return false;
        if(!(s1 instanceof Plate) && !(s1 instanceof Square)) {
            return false;
        }
        if(clownLeftRange(s1,s2))
        {
            if(s2.getLeftHand().isEmpty()) {
                if (intersectClown(s1, s2)) {
                    if(s1 instanceof Knife)
                    {
                        s2.decreaseHearts();
                    }
                    else if(s1 instanceof Heart)
                    {
                        s2.increaseHearts();
                    }
                    else if(s1 instanceof Bomb)
                    {
                        s2.removeHearts();
                    }
                    else {
                        s2.addToLeftHand(s1);
                        s1.setX(s2.getX());
                        s1.setY(s2.getY() - s1.getHeight() / 2);
                    }

                    return true;
                }
                return false;
            }
            if(intersectWithTop(s1,s2.getLeftHand().peek()))
            {
                if(s1 instanceof Knife)
                {
                    s2.decreaseHearts();
                }
                else if(s1 instanceof Heart)
                {
                    s2.increaseHearts();
                }
                else if(s1 instanceof Bomb)
                {
                    s2.removeHearts();
                }
                else{
                s2.addToLeftHand(s1);
                s1.setX(s2.getX());
                s1.setY(s2.getY() - s2.getLeftHand().size()* s1.getHeight()/2);
               }

                return true;
            }
            return false;
        }
        else if(clownRightRange(s1,s2)) {
            if (s2.getRightHand().isEmpty()) {
                if (intersectClown(s1, s2)) {
                    if(s1 instanceof Knife)
                    {
                        s2.decreaseHearts();
                    }
                    else if(s1 instanceof Heart)
                    {
                        s2.increaseHearts();
                    }
                    else if(s1 instanceof Bomb)
                    {
                        s2.removeHearts();
                    }
                    else {
                        s2.addToRightHand(s1);
                        s1.setX(s2.getX() + s2.getWidth());
                        s1.setY(s2.getY() - s1.getHeight() / 2);

                    }
                    return true;
                }
                return false;
            }
            if (intersectWithTop(s1, s2.getRightHand().peek())) {
                if(s1 instanceof Knife)
                {
                    s2.decreaseHearts();
                }
                else if(s1 instanceof Heart)
                {
                    s2.increaseHearts();
                }
                else if(s1 instanceof Bomb)
                {
                    s2.removeHearts();
                }
                else {
                s2.addToRightHand(s1);
                s1.setX(s2.getX()+s2.getWidth());
                s1.setY(s2.getY() - s2.getRightHand().size()* s1.getHeight()/2);
                }
                return true;
            }
            return false;
        }
        return false;

    }
//    private boolean stackedThree(Clown clown)
//    {
//        if(clown.getLeftHand().size()>2)
//        {
//            AbstractShape s1 = clown.getLeftHand().pop();
//            AbstractShape s2 = clown.getLeftHand().pop();
//            AbstractShape s3 = clown.getLeftHand().pop();
//
//
//            if(s1 instanceof Plate && s2 instanceof Plate && s3 instanceof Plate)
//            {
//                clown.getLeftHand().push(s3);
//                clown.getLeftHand().push(s2);
//                clown.getLeftHand().push(s1);
//                return true;
//            }
//            clown.getLeftHand().push(s3);
//            clown.getLeftHand().push(s2);
//            clown.getLeftHand().push(s1);
//        }
//    }
    @Override
    public boolean refresh()
    {
        clock++;
        if (clock == 20)
        {
            clock = 0;
            int posX = (int) (Math.random() * width);
            int posY = -1 * (int) (Math.random() * height);
            AbstractShape randomShape = randomShapeGenerator.createRandomShape(posX, posY);
            moving.add(randomShape);
        }
        List<GameObject> toBeRemoved = new LinkedList<GameObject>();
        boolean timeout = System.currentTimeMillis() - startTime > MAX_Time;
        Clown clown = (Clown) control.get(0);
        for (GameObject gameObject : moving) {
            AbstractShape shape = (AbstractShape) gameObject;
            shape.fall(this);
            if (!timeout &&
            intersect(shape, clown) ){
                score = Math.max(0, score + 1);
                shape.setFallingSpeed(0);

            }
        }
        for (GameObject object : toBeRemoved)
        {
            moving.remove(object);
        }

        // Update the x and y positions of the shapes in the clown's hands
        int i = 0;
        for (AbstractShape shape : clown.getLeftHand()) {
            shape.setX(clown.getX()-shape.getWidth());
            shape.setY(clown.getY() - i * shape.getHeight());
            i++;
        }
        i = 0;
        for (AbstractShape shape : clown.getRightHand()) {
            shape.setX(clown.getX() + clown.getWidth());
            shape.setY(clown.getY() - i * shape.getHeight());
            i++;
        }

        return !timeout;
    }
    @Override
    public int getSpeed() {
        return 7;
    }
    @Override
    public int getControlSpeed() {
        return 7;
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constants;
    }
    @Override
    public List<GameObject> getMovableObjects() {
        return moving;
    }
    @Override
    public List<GameObject> getControlableObjects() {
        return control;
    }
    @Override
    public int getWidth() {
        return width;
    }
    @Override
    public int getHeight() {
        return height;
    }
    @Override
    public String getStatus() {
        return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_Time - (System.currentTimeMillis()-startTime))/1000);
    }
}
