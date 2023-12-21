package Backend.Levels;

import Backend.Difficulty;
import Backend.WorldBuilder;
import eg.edu.alexu.csd.oop.game.World;

public class Easy implements Difficulty {

    @Override
    public void setShapeSpeed(int shapeSpeed) {

    }

    @Override
    public int getShapeSpeed() {
        return 0;
    }

    @Override
    public World getWorld(int screenWidth, int screenHeight) {
        WorldBuilder worldBuilder = new WorldBuilder(screenWidth, screenHeight);
        return worldBuilder
                .withKnife()
                .withHearts()
                .withPlates()
                .withSquares()
                .build();
    }
}
