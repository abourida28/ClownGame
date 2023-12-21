package Backend.ShapeFactories;

import Backend.Objects.AbstractShape;
import Backend.Objects.Knife;

public class KnifeFactory implements ShapeFactory{
    @Override
    public AbstractShape createShape(int Posx, int Posy) {
        return new Knife(Posx,Posy);
    }

    @Override
    public AbstractShape createShape(int Posx, int Posy, int difficulty) {
        return createShape(Posx,Posy);
    }
}
