import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Tourist implements Serializable {
    private int x;
    private int y;
    private transient BufferedImage img;

    public Tourist(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            img = ImageIO.read(new File("images/traveler.png"));
        } catch (IOException e) {
            System.out.println("IMAGEFAIL"+e.getMessage());
        }
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void moveLeft(){
        x-=7;
    }
    public void moveRight(){
        x+=7;
    }
    public void moveUp(){
        y-=7;
    }
    public void moveDown(){
        y+=7;
    }
    public void setImage(String filepath){
        try {
            img = ImageIO.read(new File("images/traveler.png"));
        } catch (IOException e) {
            System.out.println("IMAGEFAIL"+e.getMessage());
        }
    }

    public void drawMe(Graphics g) {
            g.drawImage(img, x, y,20,20, null);
    }
}
