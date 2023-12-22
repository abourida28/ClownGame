package Backend.Objects;

import eg.edu.alexu.csd.oop.game.World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Heart extends AbstractShape{
    public Heart(int posX, int posY) {
        super(posX,posY,"images/heart.png");
    }
}
