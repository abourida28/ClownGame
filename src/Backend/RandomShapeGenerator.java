package Backend;

import Backend.Objects.Shape;
import Backend.ShapeFactories.ShapeFactory;

import java.util.ArrayList;
import java.util.Random;

public class RandomShapeGenerator {
    ArrayList<ShapeFactory> shapeFactoryArrayList;
    public RandomShapeGenerator(ArrayList<ShapeFactory> arrayList){
        shapeFactoryArrayList = arrayList;
    }
    public Shape createRandomShape(int posX, int posY, int difficulty) {
        // Select a random factory and create a shape
        int randomIndex = new Random().nextInt(shapeFactoryArrayList.size());
        ShapeFactory selectedFactory = shapeFactoryArrayList.get(randomIndex);
        Shape shape = selectedFactory.createShape(posX,posY,difficulty);
        shape.setFallingSpeed(difficulty);
        return shape;
    }
}
