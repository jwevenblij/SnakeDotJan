import com.formdev.flatlaf.FlatDarkLaf;
import panels.MainWindow;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                create();
            }
        });

    }

    public static void create() {
        FlatDarkLaf.setup();
        MainWindow mainWindow = new MainWindow();
    }

}
