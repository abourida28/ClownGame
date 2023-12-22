package Backend.Objects;

import eg.edu.alexu.csd.oop.game.World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Plate extends AbstractShape implements Collectable{
    private int color;

    public Plate(int posX, int posY,int difficulty) {
        this(posX,posY,getRandomColor(random(difficulty)));
    }
    private Plate(int posX, int posY, String imagePath) {
        super(posX, posY, imagePath);
        this.color = getColorFromPath(imagePath);
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
    public int getColorFromPath(String path)
    {
        if(path.equals("images/blue_balloon.png"))
            return 0;
        else if(path.equals("images/pink_balloon.png"))
            return 1;
        else if(path.equals("images/purple_balloon.png"))
            return 2;
        else if(path.equals("images/red_balloon.png"))
            return 3;
        else if(path.equals("images/yellow_balloon.png"))
            return 4;
        else if(path.equals("images/green_balloon.png"))
            return 5;
        else
            return 6;
    }

    public int getColor() {
        return color;
    }

}
