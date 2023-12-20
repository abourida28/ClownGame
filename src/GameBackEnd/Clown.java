package GameBackEnd;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import eg.edu.alexu.csd.oop.game.GameObject;

public class Clown implements GameObject {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isVisible;
    private Color color;
    private BufferedImage[] spriteImages;
    private int hearts;

    public Clown(int width, int height, boolean isVisible, Color color) {
        this.width = width;
        this.height = height;
        this.isVisible = isVisible;
        this.color = color;
        this.hearts = 3;
        this.spriteImages = new BufferedImage[1];
        try {
            spriteImages[0] = ImageIO.read(new File("Clown.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void decreaseHearts(){
        if(this.hearts>0)
            this.hearts--;
        if(this.hearts==0)
            this.isVisible = false;

    }
    public int getHearts(){
        return this.hearts;
    }
    public void increaseHearts(){
        if(this.hearts<3)
            this.hearts++;
    }
    public void removeHearts(){
        this.hearts = 0;
        this.isVisible = false;
    }
    public boolean isCollision(GameObject o){
        return o.getY() + o.getHeight() >= this.getY() && o.getY() <= this.getY() + this.getHeight() && o.getX() + o.getWidth() >= this.getX() && o.getX() <= this.getX() + this.getWidth();
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
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

}