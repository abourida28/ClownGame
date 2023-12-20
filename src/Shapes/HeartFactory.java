package Shapes;

public class HeartFactory implements ShapeFactory{
    @Override
    public Shape createShape(int Posx,int Posy) {
        return new Heart(Posx,Posy);
    }

    @Override
    public Shape createShape(int Posx, int Posy, int difficulty) {
        return createShape(Posx,Posy);
    }
}
