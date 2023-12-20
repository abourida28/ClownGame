import eg.edu.alexu.csd.oop.game.World;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class Worldimpl implements World {

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
            control.add(new Clown(screenWidth*5/11, screenHeight*i/8, 90, true, Color.BLUE));
        }
        for(int i=0;i<10;i++) {
            moving.add(new Shape());
        }

    }

}
