package Backend.ShapeFactories;

import Backend.Objects.AbstractShape;
import Backend.Objects.Square;
import Backend.ShapeFactories.ShapeFactory;

public class SquareFactory implements ShapeFactory {
    @Override
    public AbstractShape createShape(int Posx, int Posy) {
        return null;
    }

    @Override
    public AbstractShape createShape(int Posx, int Posy, int difficulty) {
        return new Square(Posx,Posy,difficulty);
    }
}
