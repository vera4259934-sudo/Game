import javax.swing.*;

public class Main {

    public static void main(String...args) {
        Icon icon = new ImageIcon("C:/Users/gavsp/OneDrive/Документы/Кулинарный вызов/фон для заставки.png");
        JFrame f = new JFrame("Test");
        f.getContentPane().add(new JLabel(icon));
        f.setSize(800, 100);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}