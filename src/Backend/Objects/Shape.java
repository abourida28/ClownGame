package Backend.Objects;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

public interface Shape extends GameObject {
    void fall(World balloonWorld);
    int getFallingSpeed();
    //Shape createShape();
    void setFallingSpeed(int speed);
}
