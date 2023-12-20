package Shapes;

public interface ShapeFactory {
    Shape createShape(int Posx, int Posy);

    Shape createShape(int Posx,int Posy,int difficulty);

}
