package Backend.Objects;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import eg.edu.alexu.csd.oop.game.GameObject;

import java.util.Objects;
import java.util.Stack;
public class Clown implements GameObject {
    private int x;
    private int y;
    private Stack<AbstractShape> balloons;
    private Stack<AbstractShape> leftHand;
    private Stack<AbstractShape> rightHand;
    private int width;
    private int height;
    private boolean isVisible;
    private Color color;
    private BufferedImage[] spriteImages;
    private int hearts;

    public Clown(int posX,int posY,int width,  boolean isVisible, Color color) {
        this.x = posX;
        this.y = posY;
        this.width = width;
        this.isVisible = isVisible;
        this.color = color;
        this.hearts = 3;
        this.balloons = new Stack<>();
        this.spriteImages = new BufferedImage[1];
        this.height = width;
        this.leftHand = new Stack<>();
        this.rightHand = new Stack<>();
        try {
            spriteImages[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("images/robot.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
        public Stack<AbstractShape> getLeftHand() {
        return leftHand;
    }

    public Stack<AbstractShape> getRightHand() {
        return rightHand;
    }

    public void addToLeftHand(AbstractShape shape) {
        this.leftHand.push(shape);
    }

    public void addToRightHand(AbstractShape shape) {
        this.rightHand.push(shape);
    }

    public void decreaseHearts(){
        if(this.hearts>0)
            this.hearts--;
        if(this.hearts==0){
            invisibleToggle();
        }
    }
    private void invisibleToggle(){
        this.isVisible = false;
        for(AbstractShape s:getLeftHand()){
            s.setVisible(false);
        }
        for(AbstractShape s:getRightHand()){
            s.setVisible(false);
        }
    }
    public Stack<AbstractShape> getBalloons() {
        return balloons;
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
        invisibleToggle();
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
        if(isVisible)
            return;
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