package Main_Menu_UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Backend.Difficulty;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import Backend.Worldimpl;

public class clownGameScreen implements Node{
    private Node parent;
    private GameController gameController;
    private Difficulty difficulty;//TODO difficulty should be passed to world impl not be stored in clownGameScreen
    public clownGameScreen(Difficulty difficulty) {
        this.difficulty = difficulty;
        JMenuBar menuBar = constructMenu();
        //TODO make world using builder
        this.gameController = GameEngine.start("Very Simple Game in 99 Line of Code", new Worldimpl(500, 600), menuBar, Color.BLACK);

    }
    private JMenuBar constructMenu()
    {
        JMenuBar  menuBar = new JMenuBar();;
        JMenu menu = new JMenu("File");
        JMenuItem pauseMenuItem = new JMenuItem("Pause");
        JMenuItem resumeMenuItem = new JMenuItem("Resume");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        menu.addSeparator();
        menu.add(pauseMenuItem);
        menu.add(resumeMenuItem);
        pauseMenuItem.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                gameController.pause();
            }
        });
        resumeMenuItem.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                gameController.resume();
            }
        });
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        return menuBar;
    }

    @Override
    public void setParentNode(Node node) {

    }

    @Override
    public Node getParentNode() {
        return parent;
    }
}
