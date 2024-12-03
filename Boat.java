
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.Thread;
import java.awt.Color;
public class Boat implements Serializable,Runnable{
    private int x;
    private int y;
    private transient BufferedImage img;
    private int direction;
    public Boat(int x,int y){
        this.x=x;
        this.y=y;
        try {
            img = ImageIO.read(new File("images/boat.png"));
        } catch (IOException e) {
            System.out.println("IMAGEFAIL"+e.getMessage());
        }

    }
    public void setImage(){
        try {
            img = ImageIO.read(new File("images/boat.png"));
        } catch (IOException e) {
            System.out.println("IMAGEFAIL"+e.getMessage());
        }
    }
    public void setDirection(int direction){
        this.direction=direction;
    }
    public int getDirection(){
        return direction;
    }
    public void randomizeDirection(){
        double randomnum = Math.random();
        if(randomnum<0.25){
            direction=0;
        }else if(randomnum<0.50){
            direction=90;
        }else if(randomnum<0.75){
            direction=180;
        }else{
            direction=270;
        }
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void drawMe(Graphics g){
        g.drawImage(img,x,y,7,7,null);
        //System.out.println("BOAT DRAWN");

    }

    public void run(){
        while(true){
            try{
                if(direction==0){
                    x-=7;
                }else if(direction==90){
                    y-=7;
                }else if(direction==180){
                    x+=7;
                }else if(direction==270){
                    y+=7;
                }
                if(Math.random()<0.1){
                    randomizeDirection();
                }
                Thread.sleep(100);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
    
}