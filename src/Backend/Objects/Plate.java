package Backend.Objects;

import eg.edu.alexu.csd.oop.game.World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Plate implements Shape{
    private static final int MAX_MSTATE = 1;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int color;
    private int y;
    private boolean visible;
    private int speed;
    private String path;

    public Plate(int posX, int posY,int difficulty) {
        this.x = posX;
        this.y = posY;
        this.visible = true;
        this.color = random(difficulty);
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        try {
            spriteImages[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(getRandomColor(color))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int random(int difficulty)
    {
        switch(difficulty)
        {
            case 1:
                return new Random().nextInt(3);
            case 2:
                return new Random().nextInt(5);
            case 3:
                return new Random().nextInt(7);
            default:
                return 0;
        }
    }
    public static String getRandomColor( int rand) {
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
    public void fall(World balloonWorld) {
        this.setY((this.getY() + getFallingSpeed()));
        this.setX(this.getX() + (Math.random() > 0.5 ? 1 : -1));
        if (this.getY() >= balloonWorld.getHeight()) {
            // Reuse the shape
            this.setY(-1 * (int) (Math.random() * balloonWorld.getHeight()));
            this.setX((int) (Math.random() * balloonWorld.getWidth()));
        }
    }


    public int getFallingSpeed() {
        return this.speed;
    }
    @Override
    public void setFallingSpeed(int speed) {
        this.speed = speed;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Plate plate = (Plate) obj;
        return x == plate.x &&
                y == plate.y &&
                color == plate.color &&
                visible == plate.visible &&
                speed == plate.speed &&
                Objects.equals(path, plate.path);
    }
    public int getColor(){
        return this.color;
    }
//    @Override
//    public Shape createShape() {
//        // Create a new Plate with the same attributes
//        return new Plate(getX(), getY(),0);
//    }

    private String getPath() {
        return path;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int mX) {
        this.x = mX;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY) {
        this.y = mY;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth(){
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }
}
