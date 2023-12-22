package Backend.Objects;

import eg.edu.alexu.csd.oop.game.GameObject;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Background implements GameObject {
    private BufferedImage image;
    private int x;
    private int y;

    public Background(String imagePath, int x, int y) {
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return new BufferedImage[]{image};
    }
}