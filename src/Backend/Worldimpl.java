package Backend;

import Backend.Objects.*;
import Backend.Objects.Knife;
import eg.edu.alexu.csd.oop.game.World;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import Backend.Objects.Clown;
import eg.edu.alexu.csd.oop.game.GameObject;

import static java.lang.Math.abs;

public class Worldimpl implements World {

    private int score = 0;
    private static int MAX_Time = 60*1000;
    private final int width;
    private final int height;
    private long endTime, startTime = System.currentTimeMillis();
    private final List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();
    private final List<GameObject> constants = new LinkedList<GameObject>();
    private final RandomShapeGenerator randomShapeGenerator;
    private int clock;

    public Worldimpl(int screenWidth,int screenHeight, RandomShapeGenerator randomShapeGenerator)
    {
        clock = 0;
        this.randomShapeGenerator = randomShapeGenerator;
        width = screenWidth;
        height = screenHeight;
        control.add(new Clown(width /2, height - 79,64, true, Color.BLUE));
        for(int i=0;i<10;i++) {
            int posX = (int) (Math.random() * width);
            int posY = -1 * (int) (Math.random() * height);
            AbstractShape randomShape = randomShapeGenerator.createRandomShape(posX, posY);
            moving.add(randomShape);
        }

    }
    private boolean intersect(GameObject s1, GameObject s2){
        double distanceX = (s1.getX() + s1.getWidth()/2.0 - (s2.getX() + s2.getWidth()/2.0));
        double distanceY = s1.getY() + s1.getHeight()/2.0 - (s2.getY() + s2.getHeight()/2.0);
        double actualDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        return distanceX <= s1.getWidth()/2.0 + s2.getWidth()/2.0 && distanceY <=  s1.getHeight()/2.0 && actualDistance <= s1.getWidth()/2.0 + s2.getWidth()/2.0;    }

    @Override
    public boolean refresh()
    {
//        Knife knife;
//        Bomb bomb;
//        Heart heart;
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
            if (shape.isRemove()) {
                toBeRemoved.add(shape);
            }
            if (!timeout && intersect(shape, clown)) {
                if(shape instanceof Knife) {
                    clown.decreaseHearts();
                }
                else if (shape instanceof Bomb){
                    clown.removeHearts();
                } else if (shape instanceof Heart) {
                    clown.increaseHearts();
                }
                else{score = score + 1;
                shape.setX(clown.getX());
                clown.getBalloons().push(shape);
                shape.setFallingSpeed(0);
                shape.setY(shape.getY() - shape.getHeight() / 2);}

                if(clown.getY()-clown.getBalloons().size()*shape.getHeight()>height){
                    return false;
                }
            }
        }

        for (GameObject object : toBeRemoved)
        {
            moving.remove(object);
        }

        // Update the x position of the balloons in the clown's stack
        for (AbstractShape balloon : clown.getBalloons()) {
            balloon.setX(clown.getX());
        }
        if (!clown.isVisible())
            timeout = true;
        return (!timeout);
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
