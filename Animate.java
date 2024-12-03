public class Animate implements Runnable {
	
	private Screen sc;
    private Boat b1;
    private MyHashTable<Location,GridObject> myGridTable;
	
	public Animate(Screen sc,Boat b1){
		this.sc = sc;
        this.b1=b1;
        myGridTable=sc.getMap();
	}
	
	public void run(){
		while( true ){
			
			try{
				Thread.sleep(100); //millisecond
			} catch(InterruptedException ex){
				Thread.currentThread().interrupt();
			}
            if(b1.getDirection()==0){
                if(!myGridTable.get(new Location((((int)b1.getX()/7)),((int)b1.getY()/7)+2)).get(0).getName().equals("water")){
                    b1.setDirection(180);
                    System.out.println("Left Hit");
                }
            }else if(b1.getDirection()==90){
                if(!myGridTable.get(new Location((((int)b1.getX()/7)+1),((int)b1.getY()/7)+1)).get(0).getName().equals("water")){
                    b1.setDirection(270);
                    System.out.println("Up Hit");

                }
            }else if(b1.getDirection()==180){
                if(!myGridTable.get(new Location((((int)b1.getX()/7)+2),((int)b1.getY()/7)+2)).get(0).getName().equals("water")){
                    b1.setDirection(0);
                    System.out.println("Right Hit");

                }
            }else if(b1.getDirection()==270){
                if(!myGridTable.get(new Location((((int)b1.getX()/7)+1),((int)b1.getY()/7)+3)).get(0).getName().equals("water")){
                    b1.setDirection(90);
                    System.out.println("Down hit");
                }
            }
            if(b1.getX()<=14&&b1.getDirection()==0){
                b1.setDirection(180);
            }else if(b1.getX()>=686&&b1.getDirection()==180){
                b1.setDirection(0);
            }if(b1.getY()<=14&&b1.getDirection()==90){
                b1.setDirection(270);
            }if(b1.getY()>=686&&b1.getDirection()==270){
                b1.setDirection(90);
            }

			
			//redraw the screen
			sc.repaint();
		
	}
}
}