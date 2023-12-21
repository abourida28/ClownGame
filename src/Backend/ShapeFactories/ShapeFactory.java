package Backend.ShapeFactories;

import Backend.Objects.AbstractShape;

public interface ShapeFactory {
    AbstractShape createShape(int Posx, int Posy);

    AbstractShape createShape(int Posx,int Posy,int difficulty);

}
