package Backend;

import Backend.Objects.AbstractShape;
import eg.edu.alexu.csd.oop.game.World;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import Backend.Objects.Clown;
import eg.edu.alexu.csd.oop.game.GameObject;

public class Worldimpl implements World {

    private int score = 0;
    private static int MAX_Time = 60*1000;
    private final int width;
    private final int height;
    private long endTime, startTime = System.currentTimeMillis();
    private final List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();
    private RandomShapeGenerator randomShapeGenerator;

    public Worldimpl(int screenWidth,int screenHeight, RandomShapeGenerator randomShapeGenerator)
    {
        this.randomShapeGenerator = randomShapeGenerator;
        width = screenWidth;
        height = screenHeight;
        for(int i=0; i<2; i++) {
            control.add(new Clown(width*5/11, height*i/8,90,  true, Color.BLUE));
        }
        for(int i=0;i<10;i++) {
//            RandomShapeGenerator randomShapeGenerator = new RandomShapeGenerator(DifficultyManager.getEasyDifficultyFactories());
            int posX = (int) (Math.random() * width);
            int posY = -1 * (int) (Math.random() * height);
            AbstractShape randomShape = randomShapeGenerator.createRandomShape(posX, posY,1);
            moving.add(randomShape);
        }

    }
    private boolean intersect(AbstractShape s1, Clown s2){
        double distanceX = (s1.getX() + s1.getWidth()/2.0 - (s2.getX() + s2.getWidth()/2.0));
        double distanceY = s1.getY() + s1.getHeight()/2.0 - (s2.getY() + s2.getHeight()/2.0);
        double actualDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        return distanceX <= s1.getWidth()/2.0 + s2.getWidth()/2.0 && distanceY <=  s1.getHeight()/2.0 && actualDistance <= s1.getWidth()/2.0 + s2.getWidth()/2.0;    }
    @Override
    public boolean refresh()
    {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_Time;
        Clown clown = (Clown) control.get(0);
        for (GameObject gameObject : moving) {
            AbstractShape shape = (AbstractShape) gameObject;
            shape.fall(this);
            if (!timeout && intersect(shape, clown)) {
                score = Math.max(0, score + 1);
                shape.setX(clown.getX());
                clown.getBalloons().push(shape);
                shape.setFallingSpeed(0);
                shape.setY(clown.getY() - clown.getBalloons().size() * shape.getHeight()/2);

                if(clown.getY()-clown.getBalloons().size()*shape.getHeight()>height){
                    return false;
                }
            }
        }

        // Update the x position of the balloons in the clown's stack
        for (AbstractShape balloon : clown.getBalloons()) {
            balloon.setX(clown.getX());
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
        return null;
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
