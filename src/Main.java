import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String...args) {
        Icon icon = new ImageIcon("src/img/фон для заставки.png");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sc_w = (int) screenSize.getWidth();
        int sc_h = (int) screenSize.getHeight();
        JFrame levelFrame = new JFrame("Test");
        levelFrame.setSize(sc_w, sc_h);
        levelFrame.setUndecorated(true);
        levelFrame.setExtendedState(levelFrame.MAXIMIZED_BOTH);
        levelFrame.setLocationRelativeTo(null);
        levelFrame.setVisible(true);
        levelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        levelFrame.getContentPane().add(new JLabel(icon));
    }
}