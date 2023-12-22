package Backend.Objects;

import eg.edu.alexu.csd.oop.game.World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Knife extends AbstractShape{
    public Knife(int posX, int posY) {
        super(posX, posY, "images/knife.png");
    }
}
