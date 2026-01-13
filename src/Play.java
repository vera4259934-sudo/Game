import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;

public class Play extends JFrame {


    public static void main(String[] args) {
        Play game = new Play();
    }

    Image fon = Toolkit.getDefaultToolkit().createImage("src/img/фон для заставки.png");
    Ch knopkaPlay = new Ch("src/img/кнопка Play.png",480,230);

    Play()
    {
        getContentPane().add(new JLabel(new ImageIcon("src/img/фон для заставки.png")));
        pack();
        setVisible(true);
        setTitle("The best game");
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
    }

    public void paint(Graphics g)
    {
        g.drawImage(fon,0,0,this);

        g.drawImage(knopkaPlay._image, knopkaPlay.x, knopkaPlay.y,this);

    }

    private KeyListener keyListener = new KeyAdapter() {
    };

    private MouseListener mouseListener = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent qwerty) {
            if (qwerty.getX() >= knopkaPlay.x && qwerty.getX() <= (knopkaPlay.x + 420) && qwerty.getY() >= knopkaPlay.y && qwerty.getY() <= (knopkaPlay.y + 220)) {
                System.out.println("Интересный факт про страну, блюдо, которой ты будешь готовить");
                dispose();
                JFrame levelFrame = new JFrame("To the recipe");
                levelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                levelFrame.add(new Screen_fact());
                levelFrame.pack();
                levelFrame.setVisible(true);
            }
        }
    };
}