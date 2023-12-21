package Backend.ShapeFactories;

import Backend.Objects.AbstractShape;
import Backend.Objects.Bomb;

public class BombFactory implements ShapeFactory{
    @Override
    public AbstractShape createShape(int Posx, int Posy) {
        return new Bomb(Posx,Posy);
    }

    @Override
    public AbstractShape createShape(int Posx, int Posy, int difficulty) {
        return createShape(Posx,Posy);
    }
}
