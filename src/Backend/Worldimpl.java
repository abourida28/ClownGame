package Backend;
import Backend.Objects.*;
import eg.edu.alexu.csd.oop.game.World;
import java.util.Stack;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import eg.edu.alexu.csd.oop.game.GameObject;
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
            constants.add(new Background("/circus_background2.jpg", -630, 0));
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
        if(clownLeftRange(s1,s2))
        {
            if(s2.getLeftHand().isEmpty()) {
                if (intersectClown(s1, s2)) {
                    if(s1 instanceof Knife)
                    {
                        s2.decreaseHearts();
                        s1.setVisible(false);
                    }
                    else if(s1 instanceof Heart)
                    {
                        if(s2.getHearts()<3)
                            s2.increaseHearts();
                        s1.setVisible(false);
                    }
                    else if(s1 instanceof Bomb)
                    {
                        s2.removeHearts();
                        s1.setVisible(false);
                    }

                        s2.addToLeftHand(s1);
                        s1.setX(s2.getX());
                        s1.setY(s2.getY() - s1.getHeight() / 2);


                    return true;
                }
                return false;
            }
            if(intersectWithTop(s1,s2.getLeftHand().peek()))
            {
                if(s1 instanceof Knife)
                {
                    s2.decreaseHearts();
                    s1.setVisible(false);
                }
                else if(s1 instanceof Heart)
                {
                    if(s2.getHearts()<3)
                        s2.increaseHearts();
                    s1.setVisible(false);
                }
                else if(s1 instanceof Bomb)
                {
                    s2.removeHearts();
                    s1.setVisible(false);
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
                        s1.setVisible(false);
                    }
                    else if(s1 instanceof Heart)
                    {
                        if(s2.getHearts()<3)
                            s2.increaseHearts();
                        s1.setVisible(false);
                    }
                    else if(s1 instanceof Bomb)
                    {
                        s2.removeHearts();
                        s1.setVisible(false);
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
                    s1.setVisible(false);
                }
                else if(s1 instanceof Heart)
                {
                    if(s2.getHearts()<3)
                        s2.increaseHearts();
                    s1.setVisible(false);
                }
                else if(s1 instanceof Bomb)
                {
                    s2.removeHearts();
                    s1.setVisible(false);
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
    private void stackedThree(Clown clown) {
        Stack<AbstractShape> leftHand = clown.getLeftHand();
        Stack<AbstractShape> rightHand = clown.getRightHand();
        if (leftHand.size() >= 3) {
            AbstractShape shape1 = leftHand.get(leftHand.size() - 1);
            AbstractShape shape2 = leftHand.get(leftHand.size() - 2);
            AbstractShape shape3 = leftHand.get(leftHand.size() - 3);
            if(shape1 instanceof Bomb||shape2 instanceof Bomb||shape3 instanceof Bomb)
            {
                return;
            }
            if(shape1 instanceof Knife||shape2 instanceof Knife||shape3 instanceof Knife)
            {
                return;
            }
            if(shape1 instanceof Heart||shape2 instanceof Heart||shape3 instanceof Heart)
            {
                return;
            }
            Collectable s1 = (Collectable) shape1;
            Collectable s2 = (Collectable) shape2;
            Collectable s3 = (Collectable) shape3;
            if (s1.getColor() == s2.getColor() && s2.getColor() == s3.getColor()) {
                leftHand.pop();
                leftHand.pop();
                leftHand.pop();
                shape1.setVisible(false);
                shape2.setVisible(false);
                shape3.setVisible(false);
                score += 3;
            }
        }
        if (rightHand.size() >= 3) {
            AbstractShape shape1 = rightHand.get(rightHand.size() - 1);
            AbstractShape shape2 = rightHand.get(rightHand.size() - 2);
            AbstractShape shape3 = rightHand.get(rightHand.size() - 3);
            if(shape1 instanceof Bomb||shape2 instanceof Bomb||shape3 instanceof Bomb)
            {
                return;
            }
            if(shape1 instanceof Knife||shape2 instanceof Knife||shape3 instanceof Knife)
            {
                return;
            }
            if(shape1 instanceof Heart||shape2 instanceof Heart||shape3 instanceof Heart)
            {
                return;
            }
            Collectable s1 = (Collectable) shape1;
            Collectable s2 = (Collectable) shape2;
            Collectable s3 = (Collectable) shape3;
            if (s1.getColor() == s2.getColor() && s2.getColor() == s3.getColor()) {
                rightHand.pop();
                rightHand.pop();
                rightHand.pop();
                shape1.setVisible(false);
                shape2.setVisible(false);
                shape3.setVisible(false);
                score += 3;
            }
        }
    }
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
            if (!timeout && intersect(shape, clown)){
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
        if(clown.getHearts()<=0) {
            startTime = 0;
            //moving.clear();
            return false;
        }
        stackedThree(clown);
        if(!clown.getRightHand().empty()&&clown.getRightHand().peek().getY()<0||!clown.getLeftHand().empty()&&clown.getLeftHand().peek().getY()<0)
        {
            startTime = 0;
            return false;
        }
        return !timeout;
    }
    @Override
    public int getSpeed() {
        return 12;
    }
    @Override
    public int getControlSpeed() {
        return 20;
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
        return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_Time - (System.currentTimeMillis()-startTime))/1000)+ " | Lives = " + ((Clown) getControlableObjects().get(0)).getHearts();
    }
}
