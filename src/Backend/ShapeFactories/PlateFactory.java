package Backend.ShapeFactories;

import Backend.Objects.AbstractShape;
import Backend.Objects.Plate;

public class PlateFactory implements ShapeFactory {
    //private final String imagePath;


    @Override
    public AbstractShape createShape(int Posx, int Posy) {
        return null;
    }

    @Override
    public AbstractShape createShape(int Posx,int Posy,int difficulty) {
        return new Plate(Posx,Posy,difficulty);
    }
}