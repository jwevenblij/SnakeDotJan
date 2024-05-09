import com.formdev.flatlaf.FlatDarkLaf;
import panels.MainWindow;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(Main::create);

    }

    public static void create() {
        FlatDarkLaf.setup();
        new MainWindow();
    }

}
