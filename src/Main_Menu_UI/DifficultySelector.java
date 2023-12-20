package Main_Menu_UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class DifficultySelector extends JFrame implements Node {
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;

    private JButton backButton;

    private Node parent;

    public DifficultySelector() {
        setTitle("Difficulty");
        setResizable(false);
        setSize(500, 480);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        ImageIcon backgroundIcon = new ImageIcon("images/circus_background.jpg");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        add(backgroundLabel, BorderLayout.CENTER);
        backgroundLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 50)); // Added gaps
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                getBack(evt);
            }
        });
        easyButton = CommonUsedUtils.createStyledButton("Easy",(Void) -> {
            JOptionPane.showMessageDialog(null, "Easy Level Loaded!");
            return null;
        });

        backgroundLabel.add(easyButton);

        mediumButton = CommonUsedUtils.createStyledButton("Medium",(Void) -> {
            JOptionPane.showMessageDialog(null, "Medium Level Loaded!");
            return null;
        });
        backgroundLabel.add(mediumButton);

        hardButton = CommonUsedUtils.createStyledButton("Hard",(Void) -> {
            JOptionPane.showMessageDialog(null, "Hard Level Loaded!");
            return null;
        });
        backgroundLabel.add(hardButton);

        backButton = CommonUsedUtils.createStyledButton("Back",(Void) -> {
            getBack();
            return null;
        });
        backgroundLabel.add(backButton);
        setVisible(true);
    }

    private void getBack(){
        setVisible(false);
        ((JFrame)this.getParentNode()).setVisible(true);
    }
    private void getBack(java.awt.event.WindowEvent evt){
        setVisible(false);
        ((JFrame)this.getParentNode()).setVisible(true);
    }

    @Override
    public void setParentNode(Node node) {
        this.parent = node;
    }

    @Override
    public Node getParentNode() {
        return parent;
    }
}
