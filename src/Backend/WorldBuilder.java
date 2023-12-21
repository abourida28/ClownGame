package Backend;

import Backend.ShapeFactories.*;
import eg.edu.alexu.csd.oop.game.World;

public class WorldBuilder {

    World world;
    RandomShapeGenerator randomShapeGenerator;
    int screenWidth;
    int screenHeight;
    public WorldBuilder(int screenWidth,int screenHeight) {
        randomShapeGenerator = new RandomShapeGenerator();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public WorldBuilder setSpeed(int speed){
        randomShapeGenerator.setSpeed(speed);
        return this;
    }

    public WorldBuilder withBombs() {
        randomShapeGenerator.addFactory(new BombFactory());
        return this;
    }

    public WorldBuilder withHearts() {
        randomShapeGenerator.addFactory(new HeartFactory());
        return this;
    }
    public WorldBuilder withKnife() {
        randomShapeGenerator.addFactory(new KnifeFactory());
        return this;
    }
    public WorldBuilder withPlates() {
        randomShapeGenerator.addFactory(new PlateFactory());
        return this;
    }
    public WorldBuilder withSquares() {
        randomShapeGenerator.addFactory(new SquareFactory());
        return this;
    }
    public World build()
    {
        return new Worldimpl(screenWidth, screenHeight, randomShapeGenerator);
    }
}
