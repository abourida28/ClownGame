package Main_Menu_UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Function;

public class CommonUsedUtils {
    public static JButton createStyledButton(String text, Function<Void, Void> buttonFunction) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150,40));
        button.setBackground(new Color(255, 140, 0)); // Orange color
        button.setFocusPainted(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonFunction.apply(null);
            }
        });
        return button;
    }
}
