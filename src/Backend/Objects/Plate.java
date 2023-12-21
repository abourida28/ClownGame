package Backend.Objects;

import eg.edu.alexu.csd.oop.game.World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Plate extends AbstractShape{
    private int color;

    public Plate(int posX, int posY,int difficulty) {
        super(posX,posY,getRandomColor(random(difficulty)));
    }

    public static int random(int difficulty)
    {
        switch(difficulty)
        {
            case 2:
                return new Random().nextInt(3);
            case 4:
                return new Random().nextInt(5);
            case 6:
                return new Random().nextInt(7);
            default:
                return 0;
        }
    }
    public static String getRandomColor(int rand) {
        // Hard difficulty (7 colors)
        if (rand == 0) {
            return "images/blue_balloon.png";
        } else if (rand == 1) {
            return "images/pink_balloon.png";
        } else if (rand == 2) {
            return "images/purple_balloon.png";
        } else if (rand == 3) {
            return "images/red_balloon.png";
        } else if (rand == 4) {
            return "images/yellow_balloon.png";
        } else if (rand == 5) {
            return "images/green_balloon.png";
        } else {
            return "images/orange_balloon.png";
        }
    }



    @Override
    public AbstractShape createCopy() {
        AbstractShape copy = new Plate(getX(), getY(), 3);  // Pass appropriate parameters
        // Copy other properties as needed
        return copy;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
