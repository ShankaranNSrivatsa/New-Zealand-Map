import java.awt.Graphics;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class GridObject{
    private String name;
    private BufferedImage img;
    public GridObject(String name){
        this.name=name;

        
    }
    public void drawMe(Graphics g,int x, int y){
        
        if(name.equals("water")){
            g.setColor(new Color(22,189,245));
            g.fillRect(x,y,7,7);
        }else if(name.equals("road")){
            g.setColor(new Color(78,75,82));
            g.fillRect(x,y,7,7);
        }else if(name.equals("snow")){
            g.setColor(new Color(232,241,255));
            g.fillRect(x,y,7,7);
        }else if(name.equals("grass")){
            g.setColor(new Color(18, 138, 36));
            g.fillRect(x,y,7,7);
        }else if(name.equals("mountain")){
            int[] xPoints = {x,x+3,x+6};
            int[] yPoints = {y+6,y+1,y+6};
            g.setColor(Color.GRAY);
            g.fillPolygon(xPoints,yPoints,3);
        }else if(name.equals("rock")){
            g.setColor(new Color(198,201,207));
            g.fillOval(x+1,y+1,5,5);
        }else if (name.equals("tree")){
            g.setColor(Color.BLACK);
            g.fillOval(x+1,y+1,5,5);
        }else if(name.equals("skytree")){
            g.setColor(new Color(252,186,3));
            g.fillOval(x+1,y+1,5,5);
        }else if(name.equals("springs")){
            g.setColor(new Color(252,186,3));
            g.fillOval(x+1,y+1,5,5);
        }
        
    }
    public String getName(){
        return name;
    }
}