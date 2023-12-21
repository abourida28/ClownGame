package Backend;

import Backend.Objects.AbstractShape;
import Backend.ShapeFactories.ShapeFactory;

import java.util.ArrayList;
import java.util.Random;

public class RandomShapeGenerator {
    private int speed;
    ArrayList<ShapeFactory> shapeFactoryArrayList;
    public RandomShapeGenerator(){
        shapeFactoryArrayList = new ArrayList<>();
    }
    public void addFactory(ShapeFactory factory){
        shapeFactoryArrayList.add(factory);
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public AbstractShape createRandomShape(int posX, int posY) {
        // Select a random factory and create a shape
        int randomIndex = new Random().nextInt(shapeFactoryArrayList.size());
        ShapeFactory selectedFactory = shapeFactoryArrayList.get(randomIndex);
        AbstractShape shape = selectedFactory.createShape(posX,posY,speed);
        shape.setFallingSpeed(speed);
        return shape;
    }
}
