import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.*;

public class Level_1 extends JPanel {

    private BufferedImage backgroundImage;
    private int mouseX, mouseY;
    private boolean isDragging = false;
    private NewProduct selectedProduct = null;
    private List<NewProduct> product = new LinkedList<>();

    public Level_1() {
        try {
            backgroundImage = ImageIO.read(new File("src/img/Фон_для_основной_игры.png"));
        } catch (IOException e) {
            System.err.println("Не удалось загрузить фон: " + e.getMessage());
        }




        product.add(new NewProduct("H:/vera_project_2025_26/Кулинарный вызов/мука.png", 50, 450, "flour"));
        product.add(new NewProduct("H:/vera_project_2025_26/Кулинарный вызов/яйца.png", 1050,400, "egg"));
        product.add(new NewProduct("H:/vera_project_2025_26/Кулинарный вызов/молоко.png",250,370, "milk"));
        product.add(new NewProduct("H:/vera_project_2025_26/Кулинарный вызов/сыр.png",420,390, "cheese"));
        product.add(new NewProduct("H:/vera_project_2025_26/Кулинарный вызов/миксер.png", 25, 300, "mixer"));
        product.add(new NewProduct("H:/vera_project_2025_26/Кулинарный вызов/помидоры.png",1150,400, "tomato"));
        product.add(new NewProduct("H:/vera_project_2025_26/Кулинарный вызов/ложка.png",900,600, "spoon"));
        product.add(new NewProduct("H:/vera_project_2025_26/Кулинарный вызов/кетчуп.png",515,310, "ketchup"));
        product.add(new NewProduct("H:/vera_project_2025_26/Кулинарный вызов/миска.png",1030,560, "dish"));
        product.add(new NewProduct("H:/vera_project_2025_26/Кулинарный вызов/оливковое масло.png",600,310, "oil"));
        product.add(new NewProduct("H:/vera_project_2025_26/Кулинарный вызов/сыр 2.png",770,420, "cheese 2"));
        product.add(new NewProduct("H:/vera_project_2025_26/Кулинарный вызов/нож.png",320,550, "kneef"));
        product.add(new NewProduct("H:/vera_project_2025_26/Кулинарный вызов/доска.png",900,450, "board"));





        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (int i = 0; i<product.size(); i++) {
                    if (product.get(i).isInside(e.getX(), e.getY())) {
                        selectedProduct = product.get(i);
                        product.remove(i);
                        product.add(selectedProduct);
                        mouseX = e.getX() - selectedProduct.x;
                        mouseY = e.getY() - selectedProduct.y;
                        isDragging = true;
                        return;
                    }
                }
                isDragging = false;
                selectedProduct = null;
            }

            @Override
            public void  mouseReleased(MouseEvent e) {

                isDragging = false;
                selectedProduct = null;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging && selectedProduct != null) {
                    int newX = e.getX() - mouseX;
                    int newY = e.getY() - mouseY;
                    int panelWidth = getWidth();
                    int panelHeight = getHeight();
                    int productWidth = selectedProduct._image.getWidth();
                    int productHeight = selectedProduct._image.getHeight();
                    if (newX < 0) {
                        newX = 0;
                    }
                    if (newY < 0) {
                        newY = 0;
                    }
                    if (newX + productWidth > panelWidth) {
                        newX = panelWidth - productWidth;
                    }
                    if (newY + productHeight > panelHeight) {
                        newY = panelHeight - productHeight;
                    }
                    selectedProduct.move(newX, newY);
                    repaint();
                }
            }
        });
        setPreferredSize(new Dimension(1370, 770));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        for (int i = 0; i < product.size(); i++) {
            NewProduct currentProduct = product.get(i);
            g.drawImage(currentProduct._image, currentProduct.x, currentProduct.y, this);
        }
    }


    }

