package Backend;
import eg.edu.alexu.csd.oop.game.World;
public interface Difficulty {
    public void setShapeSpeed(int shapeSpeed);
    public int getShapeSpeed();
    public World getWorld(int screenWidth, int screenHeight);
}
