package Backend.ShapeFactories;

import Backend.Objects.Bomb;
import Backend.Objects.Shape;

public class BombFactory implements ShapeFactory{
    @Override
    public Shape createShape(int Posx, int Posy) {
        return new Bomb(Posx,Posy);
    }

    @Override
    public Shape createShape(int Posx, int Posy, int difficulty) {
        return createShape(Posx,Posy);
    }
}
