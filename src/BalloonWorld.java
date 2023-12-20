import java.util.ArrayList;
import java.util.List;

import Shapes.*;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

public class BalloonWorld implements World {

    private static int MAX_TIME = 1 * 60 * 1000;
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
            RandomShapeGenerator randomShapeGenerator = new RandomShapeGenerator(DifficultyManager.getEasyDifficultyFactories());
            int posX = (int) (Math.random() * screenWidth);
            int posY = -1 * (int) (Math.random() * screenHeight);
            Shape randomShape = randomShapeGenerator.createRandomShape(posX, posY,1);
            moving.add(randomShape);
        }
        // Add other initialization logic as needed
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
        for (GameObject object : moving) {
            Shape shape = (Shape) object;
            shape.fall(this);
//            if(!timeout & intersect(object, clown)){
//                score = Math.max(0, score+1);
//            }
        }
        return !timeout;  // Continue the game
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
    private boolean intersect(GameObject o1, Shape o2) {
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth() / 2 + o2.getWidth() / 2)
                && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight() / 2 + o2.getHeight() / 2);
    }
}
