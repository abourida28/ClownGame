package Backend.ShapeFactories;

import Backend.Objects.AbstractShape;
import Backend.Objects.Heart;

public class HeartFactory implements ShapeFactory{
    @Override
    public AbstractShape createShape(int Posx, int Posy) {
        return new Heart(Posx,Posy);
    }

    @Override
    public AbstractShape createShape(int Posx, int Posy, int difficulty) {
        return createShape(Posx,Posy);
    }
}
