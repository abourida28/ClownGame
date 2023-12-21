package Backend.Objects;

import eg.edu.alexu.csd.oop.game.World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bomb extends AbstractShape{


    public Bomb(int posX, int posY) {
        super(posX,posY,"images/bomb.png");
    }

    @Override
    public AbstractShape createCopy() {
        return null;
    }
}
