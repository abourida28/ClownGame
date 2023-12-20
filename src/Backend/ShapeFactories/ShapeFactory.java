package Backend.ShapeFactories;

import Backend.Objects.Shape;

public interface ShapeFactory {
    Shape createShape(int Posx, int Posy);

    Shape createShape(int Posx,int Posy,int difficulty);

}
