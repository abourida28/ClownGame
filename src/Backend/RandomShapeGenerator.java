package Backend;

import Backend.Objects.AbstractShape;
import Backend.ShapeFactories.ShapeFactory;

import java.util.ArrayList;
import java.util.Random;

public class RandomShapeGenerator {
    ArrayList<ShapeFactory> shapeFactoryArrayList;
    public RandomShapeGenerator(){
        shapeFactoryArrayList = new ArrayList<>();
    }
    public void addFactory(ShapeFactory factory){
        shapeFactoryArrayList.add(factory);
    }

    public AbstractShape createRandomShape(int posX, int posY, int difficulty) {
        // Select a random factory and create a shape
        int randomIndex = new Random().nextInt(shapeFactoryArrayList.size());
        ShapeFactory selectedFactory = shapeFactoryArrayList.get(randomIndex);
        AbstractShape shape = selectedFactory.createShape(posX,posY,difficulty);
        shape.setFallingSpeed(difficulty);
        return shape;
    }
}
