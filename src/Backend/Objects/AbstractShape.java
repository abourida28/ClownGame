package Backend.Objects;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class AbstractShape implements GameObject{
    private static final int MAX_MSTATE = 1;
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private boolean visible;
    private int speed;

    private boolean remove;

    public boolean isRemove() {
        return remove;
    }

    public AbstractShape(int posX, int posY, String imagePath) {
        this.x = posX;
        this.y = posY;
        this.visible = true;
        remove = false;

        try {
            spriteImages[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fall(World balloonWorld) {
        this.setY((this.getY() + getFallingSpeed()));
        this.setX(this.getX() + (Math.random() > 0.5 ? 1 : -1));
        if (this.getY() >= balloonWorld.getHeight()) {
            // Reuse the shape
//            this.setY(-1 * (int) (Math.random() * balloonWorld.getHeight()));
//            this.setX((int) (Math.random() * balloonWorld.getWidth()));
            remove = true;
        }
    }


    public int getFallingSpeed() {
        return this.speed;
    }


    public void setFallingSpeed(int speed) {
        this.speed = speed;
    }


    public int getX() {
        return x;
    }


    public void setX(int mX) {
        this.x = mX;
    }


    public int getY() {
        return y;
    }


    public void setY(int mY) {
        this.y = mY;
    }


    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }


    public int getWidth() {
        return spriteImages[0].getWidth();
    }


    public int getHeight() {
        return spriteImages[0].getHeight();
    }


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public abstract AbstractShape createCopy();

}

