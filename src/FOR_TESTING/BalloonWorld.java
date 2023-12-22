package FOR_TESTING;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import Backend.Objects.AbstractShape;
import Backend.Objects.Clown;
import Backend.RandomShapeGenerator;
import Backend.ShapeFactories.PlateFactory;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

import static java.lang.Math.abs;

public class BalloonWorld implements World {

    private static final int MAX_TIME = 1 * 60 * 1000;
    // 1 minute
    private int score = 0;
    private long startTime = System.currentTimeMillis();
    private final int width;
    private final int height;
    private final List<GameObject> constant = new ArrayList<>();
    private final List<GameObject> moving = new ArrayList<>();
    private final List<GameObject> control = new ArrayList<>();




    public BalloonWorld(int screenWidth, int screenHeight) {
        width = screenWidth;
        height = screenHeight;
        for(int i=0; i<20; i++){
//            Bomb bomb = new Bomb((int)(Math.random() * screenWidth), -1 * (int)(Math.random() * screenHeight));
//            Heart heart = new Heart((int)(Math.random() * screenWidth), -1 * (int)(Math.random() * screenHeight));
//            Knife knife = new Knife((int)(Math.random() * screenWidth), -1 * (int)(Math.random() * screenHeight));
//            moving.add(plate2);
//            moving.add(plate3);
            // Example usage
            RandomShapeGenerator randomShapeGenerator = new RandomShapeGenerator();
            randomShapeGenerator.addFactory(new PlateFactory());
            int posX = (int) (Math.random() * screenWidth);
            int posY = -1 * (int) (Math.random() * screenHeight);
            AbstractShape randomShape = randomShapeGenerator.createRandomShape(posX, posY);
            moving.add(randomShape);
        }
        for(int i=0; i<1; i++)
            control.add(new Clown(width * 1/2, height * 17/21,70, true, Color.BLUE));

        // Add other initialization logic as needed
    }

    @Override
    public boolean refresh()
    {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
        Clown clown = (Clown) control.get(0);
        for (GameObject gameObject : moving) {
            AbstractShape shape = (AbstractShape) gameObject;
            shape.fall(this);
            if (!timeout &&
                intersect(shape, clown)) {
                score = Math.max(0, score + 1);
                if(intersectLeft(shape,clown)){
                    clown.addToLeftHand(shape);
                    shape.setFallingSpeed(0);
                    shape.setX(clown.getX());
                    shape.setY(clown.getY() - clown.getLeftHand().size() * shape.getHeight()/2);
                    if(clown.getY()-clown.getLeftHand().size()*shape.getHeight()>height){
                        return false;
                    }
                }
                else{
                    clown.addToRightHand(shape);
                    shape.setFallingSpeed(0);
                    shape.setX(clown.getX()+clown.getWidth());
                    shape.setY(clown.getY() - clown.getRightHand().size() * shape.getHeight());
                    if(clown.getY()-clown.getRightHand().size()*shape.getHeight()>height){
                        return false;
                    }
                }

//                clown.getBalloons().push(shape);
//                shape.setFallingSpeed(0);


            }
        }

        // Update the x position of the balloons in the clown's stack
        for (AbstractShape balloon : clown.getBalloons()) {
            balloon.setX(clown.getX());
        }

        return !timeout;
    }


    private boolean intersectLeft(AbstractShape s1,Clown s2)
    {
        double distanceX = (s1.getX() + s1.getWidth()/2.0 - (s2.getX() + s2.getWidth()/2.0));
        double distanceY = s1.getY() + s1.getHeight()/2.0 - (s2.getY() + s2.getHeight()/2.0);
        double actualDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        return distanceX <= s1.getWidth()/2.0 + s2.getWidth()/4.0 && distanceY <=  s1.getHeight()/2.0 && actualDistance <= s1.getWidth()/2.0 + s2.getWidth()/4.0;
    }

    private boolean intersect(AbstractShape s1, Clown s2){
        double distanceX = (s1.getX() + s1.getWidth()/2.0 - (s2.getX() + s2.getWidth()/2.0));
        double distanceY = s1.getY() + s1.getHeight()/2.0 - (s2.getY() + s2.getHeight()/2.0);
        double actualDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        boolean intersectsLeftHand = distanceX <= s1.getWidth()/2.0 + s2.getWidth()/4.0 && distanceY <=  s1.getHeight()/2.0 && actualDistance <= s1.getWidth()/2.0 + s2.getWidth()/4.0;
        boolean intersectsRightHand = distanceX <= s1.getWidth()/2.0 + 3*s2.getWidth()/4.0 && distanceY <=  s1.getHeight()/2.0 && actualDistance <= s1.getWidth()/2.0 + 3*s2.getWidth()/4.0;

        if (intersectsLeftHand) {
            s2.addToLeftHand(s1);
        } else if (intersectsRightHand) {
            s2.addToRightHand(s1);
        }

        return intersectsLeftHand || intersectsRightHand;
    }
    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
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
        return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000);	// update status
    }

    @Override
    public int getSpeed() {
        // Your logic for getting the game speed goes here
        return 5;  // Modify this based on your game's speed
    }

    @Override
    public int getControlSpeed() {
        // Your logic for getting the control speed goes here
        return 10;  // Modify this based on your control speed
    }

    // Helper method to check if two game objects intersect
//    private boolean intersect(GameObject o1, AbstractShape o2) {
//        return (abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth() / 2 + o2.getWidth() / 2)
//                && (abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight() / 2 + o2.getHeight() / 2);
//    }
}
