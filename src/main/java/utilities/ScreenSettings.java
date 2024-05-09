package utilities;

import java.awt.*;

public class ScreenSettings {

    private static final GraphicsDevice defaultScreen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    private static final int screenWidth = defaultScreen.getDisplayMode().getWidth();
    private static final int screenHeight = defaultScreen.getDisplayMode().getHeight();
    private static final Dimension screenSize = new Dimension(screenWidth, screenHeight);
    private static final Dimension screenDPI = Toolkit.getDefaultToolkit().getScreenSize();
    private static final double screenScaling = (double) Math.round((screenSize.getHeight() / screenDPI.getHeight() * 10)) / 10;
    private static int currentScreenWidth;
    private static int currentScreenHeight;
    private static Dimension currentScreenSize;

    public ScreenSettings() {

    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static Dimension getScreenSize() {
        return screenSize;
    }

    public static Dimension getScreenDPI() {
        return screenDPI;
    }

    public static double getScreenScaling() {
        return screenScaling;
    }

    public static void setCurrentScreenWidth(int currentScreenWidth) {
        ScreenSettings.currentScreenWidth = currentScreenWidth;
    }

    public static int getCurrentScreenWidth() {
        return currentScreenWidth;
    }

    public static void setCurrentScreenHeight(int currentScreenHeight) {
        ScreenSettings.currentScreenHeight = currentScreenHeight;
    }

    public static int getCurrentScreenHeight() {
        return currentScreenHeight;
    }

    public static void setCurrentScreenSize(int currentScreenWidth, int currentScreenHeight) {
        currentScreenSize = new Dimension(currentScreenWidth, currentScreenHeight);
    }

    public static Dimension getCurrentScreenSize() {
        return currentScreenSize;
    }

}
