package Backend.Objects;

import eg.edu.alexu.csd.oop.game.World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Knife extends AbstractShape implements Collectable{
    public Knife(int posX, int posY) {
        super(posX, posY, "images/knife.png");
    }

    @Override
    public int getColor() {
        return -1;
    }

    @Override
    public int getColorFromPath(String path) {
        return 0;
    }
}
