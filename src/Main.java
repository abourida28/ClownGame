import Main_Menu_UI.CircusGameWindow;

import javax.swing.*;
//3ash ya mash3al
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CircusGameWindow();
            }
        });
    }
}