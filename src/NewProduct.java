import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NewProduct {
    public BufferedImage _image;
    public int x, y;
    public String name;
    int startX, startY;

    public NewProduct(String imagePath, int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.name = name;
        try {
            _image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.err.println("Не удалось загрузить изображение " + name + ": " + e.getMessage());
        }
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isInside(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + _image.getWidth() &&
                mouseY >= y && mouseY <= y + _image.getHeight();
    }
}