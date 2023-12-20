package Backend.Objects;

import eg.edu.alexu.csd.oop.game.World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bomb implements Shape{
    private static final int MAX_MSTATE = 1;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private boolean visible;

    private int speed;


    public Bomb(int posX, int posY) {
        this.x = posX;
        this.y = posY;
        this.visible = true;
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        try {
            spriteImages[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("images/bomb.png")));
        } catch (IOException e) {
            e.printStackTrace();
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
    @Override
    public int getFallingSpeed() {
        return this.speed;
    }

//    @Override
//    public Shape createShape() {
//        // Create a new Plate with the same attributes
//        return new Bomb(getX(), getY());
//    }

    @Override
    public void setFallingSpeed(int speed) {
        this.speed = speed;
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
