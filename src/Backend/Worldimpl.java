package Backend;

import eg.edu.alexu.csd.oop.game.World;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import Backend.Objects.Clown;
import eg.edu.alexu.csd.oop.game.GameObject;

public class Worldimpl implements World {

    private int score = 0;
    private static int MAX_Time = 60*1000;
    private final int width;
    private final int height;
    private long endTime, startTime = System.currentTimeMillis();
    private final List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();

    public Worldimpl(int screenWidth,int screenHeight)
    {
        width = screenWidth;
        height = screenHeight;
        for(int i=0; i<2; i++) {
            //Mesh fahem eh el 90 dy
            control.add(new Clown(screenWidth*5/11, screenHeight*i/8,  true, Color.BLUE));
        }
        for(int i=0;i<10;i++) {
            //dh new Shape() interface
//            moving.add(new Shape());
        }

    }

    @Override
    public List<GameObject> getConstantObjects() {
        return null;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return null;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return null;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public boolean refresh() {
        return false;
    }

    @Override
    public String getStatus() {
        return null;
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public int getControlSpeed() {
        return 0;
    }
}
