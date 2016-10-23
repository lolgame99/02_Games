package at.mab.mygames.ClassGame;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class SoldierHitbox {
	private int x,y;
	private Rectangle hitbox;
	
	public SoldierHitbox(int x, int y) {
		this.x = x + 12;
		this.y = y;
		this.hitbox = new Rectangle(this.x, this.y, 44, 60);
	}
	
	public void setX(int x) {
		this.hitbox.setX(x);
	}


	public void setY(int y) {
		this.hitbox.setY(y);
	}

	public Rectangle getRectangle() {
		return this.hitbox;
	}
	
	public void render(Graphics g){
		g.draw(hitbox);
	}
}
