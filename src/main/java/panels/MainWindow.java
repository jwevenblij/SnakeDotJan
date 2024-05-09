package panels;//import panels.CardsPanel;
import utilities.ScreenSettings;
//import utilities.ScreenSettings;
//import utilities.ScreenSettings;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    GameArea gameArea = new GameArea();
    GameOverScreen gameOverScreen = new GameOverScreen();

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

    public void showGameOverScreen() {
        this.remove(gameArea);
        this.add(gameOverScreen);
    }

    private Dimension initialScreenSize() {

        Dimension screenSize = ScreenSettings.getScreenSize();
        double screenScaling = ScreenSettings.getScreenScaling();
        Dimension initialScreenSizeCalculation;
        int initialWidth = 0;
        int initialHeight = 0;

        // Check if main monitor has horizontal or vertical orientation and base initial resolution on orientation
        if (screenSize.getWidth() < screenSize.getHeight()) {
            initialScreenSizeCalculation = new Dimension((int) ((screenSize.getWidth() / screenScaling) / 1.5), (int) ((screenSize.getWidth() / screenScaling) / 1.5));
        } else {
            initialScreenSizeCalculation = new Dimension((int) ((screenSize.getHeight() / screenScaling) / 1.5), (int) ((screenSize.getHeight() / screenScaling) / 1.5));
        }

        initialWidth = (int) initialScreenSizeCalculation.getWidth();
        initialHeight = (int) initialScreenSizeCalculation.getHeight();
        initialWidth = initialWidth - (initialWidth % 32);
        initialHeight = initialHeight - (initialHeight % 32);
        initialScreenSizeCalculation = new Dimension(initialWidth, initialHeight);

        return initialScreenSizeCalculation;

    }
}