package Backend;

import Backend.Objects.Shape;
import FOR_TESTING.DifficultyManager;
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

    public Worldimpl(int screenWidth,int screenHeight)
    {
        width = screenWidth;
        height = screenHeight;
        for(int i=0; i<2; i++) {
            control.add(new Clown(width*5/11, height*i/8,  true, Color.BLUE));
        }
        for(int i=0;i<10;i++) {
            RandomShapeGenerator randomShapeGenerator = new RandomShapeGenerator(DifficultyManager.getEasyDifficultyFactories());
            int posX = (int) (Math.random() * width);
            int posY = -1 * (int) (Math.random() * height);
            Shape randomShape = randomShapeGenerator.createRandomShape(posX, posY,1);
            moving.add(randomShape);
        }

    }
    private boolean intersect(Shape s1, Clown s2){
        return (Math.abs((s1.getX()+s1.getWidth()/2) - (s2.getX()+s2.getWidth()/2)) <= s1.getWidth()) && (Math.abs((s1.getY()+s1.getHeight()/2) - (s2.getY()+s2.getHeight()/2)) <= s1.getHeight());
    }
    @Override
    public boolean refresh()
    {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_Time;
        Clown Clown = (Backend.Objects.Clown) control.get(0);
        for (GameObject fallObj : moving) {
            Shape shape = (Shape) fallObj;
            shape.fall(this);
            if(!timeout && intersect((Shape) fallObj, Clown)){
                score = Math.max(0, score+1);
    }
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
}
