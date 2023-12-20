package Shapes;

public class PlateFactory implements ShapeFactory {
    //private final String imagePath;


    @Override
    public Shape createShape(int Posx, int Posy) {
        return null;
    }

    @Override
    public Shape createShape(int Posx,int Posy,int difficulty) {
        return new Plate(Posx,Posy,difficulty);
    }
}