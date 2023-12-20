package Backend.ShapeFactories;

import Backend.Objects.Knife;
import Backend.Objects.Shape;

public class KnifeFactory implements ShapeFactory{
    @Override
    public Shape createShape(int Posx, int Posy) {
        return new Knife(Posx,Posy);
    }

    @Override
    public Shape createShape(int Posx, int Posy, int difficulty) {
        return createShape(Posx,Posy);
    }
}
