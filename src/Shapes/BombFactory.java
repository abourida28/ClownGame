package Shapes;

public class BombFactory implements ShapeFactory{
    @Override
    public Shape createShape(int Posx,int Posy) {
        return new Bomb(Posx,Posy);
    }

    @Override
    public Shape createShape(int Posx, int Posy, int difficulty) {
        return createShape(Posx,Posy);
    }
}
