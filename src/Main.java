import FOR_TESTING.BalloonWorld;
import Frontend.mainMenuWindow;
import eg.edu.alexu.csd.oop.game.GameEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new mainMenuWindow();
            }
        });
//        JMenuBar menuBar = new JMenuBar();
//        JMenu menu = new JMenu("File");
//        JMenuItem newMenuItem = new JMenuItem("New");
//        JMenuItem pauseMenuItem = new JMenuItem("Pause");
//        JMenuItem resumeMenuItem = new JMenuItem("Resume");
//        menu.add(newMenuItem);
//        menu.addSeparator();
//        menu.add(pauseMenuItem);
//        menu.add(resumeMenuItem);
//        menuBar.add(menu);
//
//        final GameEngine.GameController gameController = GameEngine.start(
//                "Clown Game", new BalloonWorld(800, 500),menuBar);
//
//        newMenuItem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                gameController.changeWorld(new BalloonWorld(800, 500)); // Adjust the dimensions as needed
//            }
//        });
//
//        pauseMenuItem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                gameController.pause();
//            }
//        });
//
//        resumeMenuItem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                gameController.resume();
//            }
//        });

    }
}