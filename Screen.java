import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;  
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.MouseListener;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
public class Screen extends JPanel implements KeyListener{
    private MyHashTable<Location, GridObject> myGridTable;
    private Tourist t;
    private boolean upPressed,downPressed,leftPressed,rightPressed,skytree,springs,mountCook;
    private BufferedImage skytower,springimage,mountCookImage;
    public Screen(){
        this.setLayout(null);
        myGridTable = new MyHashTable<Location, GridObject>();
        addKeyListener(this);
        setFocusable(true);
        upPressed=false;
        downPressed=false;
        leftPressed=false;
        rightPressed=false;
        skytree=false;
        springs=false;
        mountCook=false;
        try {
			Scanner scan = new Scanner(new FileReader("MapExportFile.txt"));			
			
			System.out.println();
			t= new Tourist(203,203);
			//reads one line at a time
			int col=0;
            while (scan.hasNextLine()){
			   //System.out.println( scan.nextLine() );
               String line = scan.nextLine();
               String[] array = line.split(" ");
               for(int row=0;row<array.length;row++){
                
                Location key = new Location(row,col);
                GridObject g = null;
                GridObject c = null;
                if(array[row].equals("9")){
                    g = new GridObject("water");
                }else if(array[row].equals("5")){
                    g = new GridObject("grass");
                    if(((int)(Math.random()*15))==1){
                        c= new GridObject("rock");
                    }else if(((int)(Math.random()*15))==1){
                        c= new GridObject("tree");
                    }

                }else if(array[row].equals("6")){
                    g = new GridObject("road");
                }else if(array[row].equals("10")){
                    g = new GridObject("snow");
                    if(((int)(Math.random()*2))==1){
                    c = new GridObject("mountain");}
                }else{
                    g = new GridObject("water");
                }
                myGridTable.put(key,g);
                if(c!=null){
                    myGridTable.put(key,c);
                }
               }

               col++;
			}
		}
		catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            skytower = ImageIO.read(new File("images/skytower.jpg"));
            springimage = ImageIO.read(new File("images/springs.jpg"));
            mountCookImage = ImageIO.read(new File("images/mountcook.jpg"));
        } catch (IOException e) {
            System.out.println("IMAGEFAIL"+e.getMessage());
        }
        

        if(myGridTable.get(new Location(28,14)).size()>1){
            myGridTable.remove(new Location(28,14),myGridTable.get(new Location(28,14)).get(1));
            myGridTable.put(new Location(28,14),new GridObject("skytree"));
        }else{
            myGridTable.put(new Location(28,14),new GridObject("skytree"));
        }
        if(myGridTable.get(new Location(37,26)).size()>1){
            myGridTable.remove(new Location(37,26),myGridTable.get(new Location(37,26)).get(1));
            myGridTable.put(new Location(37,26),new GridObject("springs"));
        }else{
            myGridTable.put(new Location(37,26),new GridObject("springs"));
        }
        if(myGridTable.get(new Location(17,75)).size()>1){
            myGridTable.remove(new Location(17,75),myGridTable.get(new Location(17,75)).get(1));
            myGridTable.put(new Location(17,75),new GridObject("mountcook"));
        }else{
            myGridTable.put(new Location(17,75),new GridObject("mountcook"));
        }
        myGridTable.remove(new Location(31,57),myGridTable.get(new Location(31,57)).get(0));
        myGridTable.put(new Location(31,57),new GridObject("road"));
        myGridTable.remove(new Location(30,57),myGridTable.get(new Location(30,57)).get(0));
        myGridTable.put(new Location(30,57),new GridObject("road"));
        myGridTable.remove(new Location(30,58),myGridTable.get(new Location(30,58)).get(0));
        myGridTable.put(new Location(30,58),new GridObject("road"));
        myGridTable.remove(new Location(29,58),myGridTable.get(new Location(29,58)).get(0));
        myGridTable.put(new Location(29,58),new GridObject("road"));
        myGridTable.remove(new Location(29,59),myGridTable.get(new Location(29,59)).get(0));
        myGridTable.put(new Location(29,59),new GridObject("road"));





    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1200,700);
        
        
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        DLList<Location> keylist = myGridTable.keySet().toDLList();
   
        for(int i=0;i<keylist.size();i++){
            for(int j=0;j<myGridTable.get(keylist.get(i)).size();j++){
            myGridTable.get(keylist.get(i)).get(j).drawMe(g,keylist.get(i).getRow()*7,keylist.get(i).getColumn()*7);}
            
        }
        t.drawMe(g);
        if(skytree){
            g.drawImage(skytower,700,0,500,350,null);
            g.setFont(new Font("default", Font.BOLD, 30));
            g.setColor(Color.BLACK);
            g.drawString("SkyTower",890,375);
            g.setFont(new Font("default", Font.BOLD, 15));
            g.drawString("Auckland",928,395);
            g.setFont(new Font("default", Font.PLAIN, 14));
            g.drawString("The Sky Tower is a telecommunications and observation tower in ", 715,410);
            g.drawString("Auckland, New Zealand. It is 328 metres (1,076 ft) tall, as ",715,425);
            g.drawString("measured from ground level to the top of the mast, making it the ",715,440);
            g.drawString("second tallest freestanding structure in the Southern Hemisphere.",715,455);
            g.drawString("The tower contains three observation decks allowing the public to",715,470);
            g.drawString("enjoy an incredible view of the largest city in New Zealand.",715,485);
        }
        if(springs){
            g.drawImage(springimage,700,0,500,350,null);
            g.setFont(new Font("default", Font.BOLD, 30));
            g.setColor(Color.BLACK);
            g.drawString("Te Puia",890,375);
            g.setFont(new Font("default", Font.BOLD, 15));
            g.drawString("Rotorua",920,395);
            g.setFont(new Font("default", Font.PLAIN, 14));
            g.drawString("Te Puia is a cultural complex and geothermal park in Rotorua, ", 715, 410);
            g.drawString("New Zealand. It is home to the famous Pohutu Geyser, which ", 715, 425);
            g.drawString("erupts up to 30 meters high, making it the largest active geyser ", 715, 440);
            g.drawString("in the Southern Hemisphere. The park also features boiling mud ", 715, 455);
            g.drawString("pools, hot springs, and an extensive Maori cultural experience.", 715, 470);
            g.drawString("Visitors can enjoy traditional performances, explore the Maori ", 715, 485);
            g.drawString("art and crafts, and learn about the significance of Te Puia to ", 715, 500);
            g.drawString("the indigenous Maori people.", 715, 515);
        }
        if(mountCook){
            g.drawImage(mountCookImage,700,0,500,350,null);
            g.setFont(new Font("default", Font.BOLD, 30));
            g.setColor(Color.BLACK);
            g.drawString("Mount Cook", 850, 375);
            g.setFont(new Font("default", Font.BOLD, 15));
            g.drawString("Twizel", 920, 395);
            g.setFont(new Font("default", Font.PLAIN, 14));
            g.drawString("Mount Cook, or Aoraki, is the highest mountain in New Zealand, ", 715, 410);
            g.drawString("standing at 3,724 meters (12,218 feet). It is located in the ", 715, 425);
            g.drawString("Southern Alps, within the Aoraki / Mount Cook National Park.", 715, 440);
            g.drawString("The mountain is a popular destination for climbers, hikers, and ", 715, 455);
            g.drawString("tourists, offering breathtaking views and a variety of outdoor ", 715, 470);
            g.drawString("activities. Visitors can explore the Hooker Valley Track or ", 715, 485);
            g.drawString("take scenic flights to see glaciers, alpine lakes, and rugged ", 715, 500);
            g.drawString("mountain landscapes.", 715, 515);

        }
    }
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        //System.out.println("YO");
        if(e.getKeyCode()==KeyEvent.VK_UP&&!upPressed){
            DLList<GridObject> upGrid = myGridTable.get(new Location((((int)t.getX()/7)+1),((int)t.getY()/7)+1));
            if(!upGrid.get(0).getName().equals("water")){
                if(upGrid.size()<=1){
                    t.moveUp();
                }
            }
            //System.out.println(myGridTable.get(new Location((int)t.getX()/7,((int)t.getY()/7)-1)).get(0).getName());
            //System.out.println("X:"+t.getX()/7+ " Y:" + t.getY()/7);           
            upPressed=true;
        }else if(e.getKeyCode()==KeyEvent.VK_DOWN&&!downPressed){
            DLList<GridObject> downGrid =myGridTable.get(new Location((((int)t.getX()/7)+1),((int)t.getY()/7)+3));
            if(!downGrid.get(0).getName().equals("water")){
                if(downGrid.size()<=1){
                        t.moveDown();
                }
            }
            downPressed=true;
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT&&!leftPressed){
            DLList<GridObject> leftGrid = myGridTable.get(new Location((((int)t.getX()/7)),((int)t.getY()/7)+2));
            if(!leftGrid.get(0).getName().equals("water")){
                if(leftGrid.size()<=1){
                    t.moveLeft();
                }
            }
            leftPressed=true;
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT&&!rightPressed){
            DLList<GridObject> rightGrid = myGridTable.get(new Location((((int)t.getX()/7)+2),((int)t.getY()/7)+2));
            if(!rightGrid.get(0).getName().equals("water")){
                if(rightGrid.size()<=1){
                    t.moveRight(); 
                }
            }
            rightPressed=true;
        }
    if(checkForObject(new Location((((int)t.getX()/7)+2),((int)t.getY()/7)+2),"skytree")){
            skytree=true;
            //System.out.println("A");
            //directly left of sky tower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)),((int)t.getY()/7)+2),"skytree")){
            skytree=true;
            //System.out.println("B");
            //directly right of skytower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)+1),((int)t.getY()/7)+1),"skytree")){
            skytree=true;
            //System.out.println("C");
            //directly down of skytower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)+1),((int)t.getY()/7)+3),"skytree")){
            skytree=true;
            //System.out.println("D");
            //directly up of skytower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)),((int)t.getY()/7)+3),"skytree")){
            skytree=true;
            //top right diagonal of skytower
           
    }else if(checkForObject(new Location((((int)t.getX()/7)+2),((int)t.getY()/7)+3),"skytree")){
        
            skytree=true;
            //top left diagonal of skytower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)+2),((int)t.getY()/7)+1),"skytree")){
            skytree=true;
            //bottom left diagonal of skytower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)),((int)t.getY()/7)+1),"skytree")){
        
            skytree=true;
            //bottom right diagonal of skytower
        
        
    }else{
        skytree=false;
        //System.out.println("BYE");
    }

    
    if(checkForObject(new Location((((int)t.getX()/7)+2),((int)t.getY()/7)+2),"springs")){
            springs=true;
            //System.out.println("A");
            //directly left of sky tower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)),((int)t.getY()/7)+2),"springs")){
            springs=true;
            //System.out.println("B");
            //directly right of skytower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)+1),((int)t.getY()/7)+1),"springs")){
            springs=true;
            //System.out.println("C");
            //directly down of skytower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)+1),((int)t.getY()/7)+3),"springs")){
            springs=true;
            //System.out.println("D");
            //directly up of skytower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)),((int)t.getY()/7)+3),"springs")){
            springs=true;
            //top right diagonal of skytower
           
    }else if(checkForObject(new Location((((int)t.getX()/7)+2),((int)t.getY()/7)+3),"springs")){
            springs=true;
            //top left diagonal of skytower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)+2),((int)t.getY()/7)+1),"springs")){
            springs=true;
            //bottom left diagonal of skytower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)),((int)t.getY()/7)+1),"springs")){
        
            springs=true;
            //bottom right diagonal of skytower
        
        
    }else{
        springs=false;
        //System.out.println("BYE");
    }

    if(checkForObject(new Location((((int)t.getX()/7)+2),((int)t.getY()/7)+2),"mountcook")){
            mountCook=true;
            //System.out.println("A");
            //directly left of sky tower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)),((int)t.getY()/7)+2),"mountcook")){
            mountCook=true;
            //System.out.println("B");
            //directly right of skytower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)+1),((int)t.getY()/7)+1),"mountcook")){
            mountCook=true;
            //System.out.println("C");
            //directly down of skytower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)+1),((int)t.getY()/7)+3),"mountcook")){
            mountCook=true;
            //System.out.println("D");
            //directly up of skytower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)),((int)t.getY()/7)+3),"mountcook")){
            mountCook=true;
            //top right diagonal of skytower
           
    }else if(checkForObject(new Location((((int)t.getX()/7)+2),((int)t.getY()/7)+3),"mountcook")){
            mountCook=true;
            //top left diagonal of skytower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)+2),((int)t.getY()/7)+1),"mountcook")){
            mountCook=true;
            //bottom left diagonal of skytower
        
    }else if(checkForObject(new Location((((int)t.getX()/7)),((int)t.getY()/7)+1),"mountcook")){
        
            mountCook=true;
            //bottom right diagonal of skytower
        
        
    }else{
        mountCook=false;
        //System.out.println("BYE");
    }
        repaint();
    }

    

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

    }
    public boolean checkForObject(Location l1,String name){
        DLList<GridObject> list = myGridTable.get(l1);
        if(list.size()>1){
            for(int i=0;i<list.size();i++){
                if(list.get(i).getName().equals(name)){
                    return true;
                }
            }
            
        }
        return false;
    }

}