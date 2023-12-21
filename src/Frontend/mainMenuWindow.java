package Frontend;

import javax.swing.*;
import java.awt.*;

public class mainMenuWindow extends JFrame implements Node{

    public mainMenuWindow() {
        setTitle("Circus Game");
        setResizable(false);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        ImageIcon backgroundIcon = new ImageIcon("images/circus_background.jpg");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        add(backgroundLabel, BorderLayout.CENTER);
        backgroundLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 100)); // Added gaps

        JButton startButton = CommonUsedUtils.createStyledButton("Start", (Void) -> {
            performAction();
            return null;
        });
        backgroundLabel.add(startButton);


        JButton exitButton = CommonUsedUtils.createStyledButton("Exit", (Void) -> {
            System.exit(0);
            return null;
        });
        backgroundLabel.add(exitButton);
        setVisible(true);
    }

    private void performAction(){
        //Eh el function de?
//        JOptionPane.showMessageDialog(null, "Game is starting!");
        setVisible(false);
        DifficultySelector difficultySelector = DifficultySelector.getDifficultySelector();
        difficultySelector.setVisible(true);
        difficultySelector.setParentNode(this);
    }

    @Override
    public void setParentNode(Node node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Node getParentNode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
