package Backend.Objects;

import eg.edu.alexu.csd.oop.game.World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Square  extends AbstractShape implements Collectable{
    private int color;

    public Square(int posX, int posY,int difficulty) {
        this(posX,posY,getRandomColor(random(difficulty)));
    }
    private Square(int posX, int posY, String imagePath) {
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
            return "images/blue_square.png";
        } else if (rand == 1) {
            return "images/pink_square.png";
        } else if (rand == 2) {
            return "images/purple_square.png";
        } else if (rand == 3) {
            return "images/red_square.png";
        } else if (rand == 4) {
            return "images/yellow_square.png";
        } else if (rand == 5) {
            return "images/green_square.png";
        } else {
            return "images/orange_square.png";
        }
    }
    public int getColorFromPath(String path)
    {
        if(path.equals("images/blue_square.png"))
            return 0;
        else if(path.equals("images/pink_square.png"))
            return 1;
        else if(path.equals("images/purple_square.png"))
            return 2;
        else if(path.equals("images/red_square.png"))
            return 3;
        else if(path.equals("images/yellow_square.png"))
            return 4;
        else if(path.equals("images/green_square.png"))
            return 5;
        else
            return 6;
    }

    public int getColor() {
        return color;
    }

}
