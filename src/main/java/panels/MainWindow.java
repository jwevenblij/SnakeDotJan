package panels;

import utilities.ScreenSettings;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    GameArea gameArea = new GameArea();

    public MainWindow() {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("SnakeDotJan");

        ScreenSettings.setCurrentScreenHeight(this.getHeight());
        ScreenSettings.setCurrentScreenWidth(this.getWidth());
        ScreenSettings.setCurrentScreenSize(
                ScreenSettings.getCurrentScreenWidth(),
                ScreenSettings.getCurrentScreenHeight()
        );

        this.add(gameArea);
        this.pack();
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

}