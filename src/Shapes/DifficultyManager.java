package Shapes;

import java.util.ArrayList;
import java.util.List;

public class DifficultyManager {
    public static ArrayList<ShapeFactory> getEasyDifficultyFactories() {
        ArrayList<ShapeFactory> factories = new ArrayList<>();
        factories.add(new PlateFactory());
        factories.add(new SquareFactory());
        return factories;
    }

    public static ArrayList<ShapeFactory> getMediumDifficultyFactories() {
        ArrayList<ShapeFactory> factories = getEasyDifficultyFactories();
        factories.add(new KnifeFactory());
        factories.add(new HeartFactory());
        return factories;
    }

    public static ArrayList<ShapeFactory> getHardDifficultyFactories() {
        ArrayList<ShapeFactory> factories = getMediumDifficultyFactories();
        factories.add(new BombFactory());
        return factories;
    }
}
