package Backend.Objects;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

public interface Shape extends GameObject {
    void fall(World Worldimpl);
    int getFallingSpeed();
    //Shape createShape();
    void setFallingSpeed(int speed);

}
