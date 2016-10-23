package at.mab.mygames.ClassGame;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;


public class TankHitbox {
	private Rectangle tankHitbox;
	
	
	public TankHitbox(int x, int y) {
		
		this.tankHitbox = new Rectangle(x, y, 55, 60);
	}
	
	public void update(int millisSinceLastCall, GameContainer gc){
			
	}
	
	

	public void setX(int x) {
		
		this.tankHitbox.setX(x);
	}

	public void setY(int y) {
		this.tankHitbox.setY(y);
	}
	
	public Rectangle getRectangle() {
		// TODO Auto-generated method stub
		return this.tankHitbox;
	}
	
	public void render(Graphics g){
		g.draw(tankHitbox);
		//System.out.println(this.x + "-" + this.y);
	}
	
	
}
