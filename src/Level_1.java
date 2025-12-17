import sun.plugin.javascript.navig.Array;

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
    private List<NewProduct> product = new ArrayList<>();
    private NewProduct dish = null;
    private boolean milkAdded = false;
    private boolean flourAdded = false;
    private boolean eggAdded = false;

    public Level_1() {
        try {
            backgroundImage = ImageIO.read(new File("src/img/Фон_для_основной_игры.png"));
        } catch (IOException e) {
            System.err.println("Не удалось загрузить фон: " + e.getMessage());
        }




        product.add(new NewProduct("src/img/мука.png", 50, 450, "flour"));
        product.add(new NewProduct("src/img/яйца.png", 1050,400, "egg"));
        product.add(new NewProduct("src/img/молоко.png",250,370, "milk"));
        product.add(new NewProduct("src/img/сыр.png",420,390, "cheese"));
        product.add(new NewProduct("src/img/миксер.png", 25, 300, "mixer"));
        product.add(new NewProduct("src/img/помидоры.png",1150,400, "tomato"));
        product.add(new NewProduct("src/img/ложка.png",900,600, "spoon"));
        product.add(new NewProduct("src/img/кетчуп.png",515,310, "ketchup"));
        product.add(new NewProduct("src/img/миска.png",1030,560, "dish"));
        product.add(new NewProduct("src/img/оливковое масло.png",600,310, "oil"));
        product.add(new NewProduct("src/img/сыр 2.png",770,420, "cheese 2"));
        product.add(new NewProduct("src/img/нож.png",320,550, "kneef"));
        product.add(new NewProduct("src/img/доска.png",900,450, "board"));

        for (int i = 0; i < product.size(); i++) {
            NewProduct p = product.get(i);
            if (p.name.equals("dish")) {
                dish = p;
                break;
            }
        }



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


                if (selectedProduct != null) {
                    if (dish.isInside(selectedProduct.x, selectedProduct.y)) {
                        if (selectedProduct.name.equals("milk") && !milkAdded) {
                            try {
                                selectedProduct._image = ImageIO.read(new File("src/img/молоко1s3.png"));
                                repaint();
                            } catch (IOException err) {
                                System.err.println("Не удалось загрузить новое изображение молока: " + err.getMessage());
                            }

                            milkAdded = true;
                        } else if (selectedProduct.name.equals("flour") && !flourAdded) {
                            product.remove(selectedProduct);
                            flourAdded = true;
                        }
                        else if (selectedProduct.name.equals("egg") && !eggAdded) {
                            product.remove(selectedProduct);
                            eggAdded = true;
                        }

                        selectedProduct = null;
                        if (milkAdded && flourAdded&& eggAdded) {
                            try {
                                dish._image = ImageIO.read(new File("src/img/тесто_в_миске.png"));
                            } catch (IOException oops) {
                                System.err.println("Не удалось загрузить изображение теста: " + oops.getMessage());
                            }
                        }
                        repaint();
                    }
                }

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

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        for (int i = 0; i < product.size(); i++) {
            NewProduct currentProduct = product.get(i);
            g.drawImage(currentProduct._image, currentProduct.x, currentProduct.y, this);
        }
    }


    }

