package Backend.ShapeFactories;

import Backend.Objects.Shape;
import Backend.Objects.Square;
import Backend.ShapeFactories.ShapeFactory;

public class SquareFactory implements ShapeFactory {
    @Override
    public Shape createShape(int Posx, int Posy) {
        return null;
    }

    @Override
    public Shape createShape(int Posx, int Posy, int difficulty) {
        return new Square(Posx,Posy,difficulty);
    }
}
