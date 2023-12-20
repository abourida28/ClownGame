package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class DifficultySelector extends JFrame implements Node {

    static DifficultySelector difficultySelector = null;

    private Node parent;

    private DifficultySelector() {
        setTitle("Difficulty");
        setResizable(false);
        setSize(500, 480);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        ImageIcon backgroundIcon = new ImageIcon("images/circus_background.jpg");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        add(backgroundLabel, BorderLayout.CENTER);
        backgroundLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 50)); // Added gaps

        JButton easyButton = CommonUsedUtils.createStyledButton("Easy", (Void) -> {
            JOptionPane.showMessageDialog(null, "Easy Level Loaded!");
            return null;
        });

        backgroundLabel.add(easyButton);

        JButton mediumButton = CommonUsedUtils.createStyledButton("Medium", (Void) -> {
            JOptionPane.showMessageDialog(null, "Medium Level Loaded!");
            return null;
        });
        backgroundLabel.add(mediumButton);

        JButton hardButton = CommonUsedUtils.createStyledButton("Hard", (Void) -> {
            JOptionPane.showMessageDialog(null, "Hard Level Loaded!");
            return null;
        });
        backgroundLabel.add(hardButton);

        JButton backButton = CommonUsedUtils.createStyledButton("Back", (Void) -> {
            getBack();
            return null;
        });
        backgroundLabel.add(backButton);
        setVisible(true);
    }

    public static DifficultySelector getDifficultySelector(){
        if (difficultySelector == null)
            difficultySelector =  new DifficultySelector();
        return difficultySelector;
    }

    private void getBack(){
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