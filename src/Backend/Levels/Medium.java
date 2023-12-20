package Backend.Levels;

import Backend.Difficulty;
import eg.edu.alexu.csd.oop.game.World;

public class Medium implements Difficulty {
    @Override
    public void setShapeSpeed(int shapeSpeed) {

    }

    @Override
    public int getShapeSpeed() {
        return 0;
    }

    @Override
    public World getWorld() {
        return null;
    }
}
