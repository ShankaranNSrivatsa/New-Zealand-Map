public class Animate implements Runnable {
	
	private Screen sc;
	
	public Animate(Screen sc){
		this.sc = sc;
	}
	
	public void run(){
		while( true ){
			
			try{
				Thread.sleep(100); //millisecond
			} catch(InterruptedException ex){
				Thread.currentThread().interrupt();
			}
			
			//redraw the screen
			sc.repaint();
		}
	}
}