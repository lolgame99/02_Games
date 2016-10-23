package at.mab.mygames.ClassGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class SoldierActor {
	private int x, y;
	private Image soldier;
	private SoldierHitbox hitbox;
	
	public SoldierActor(int x, int y) {
		this.x = x;
		this.y = y;
		this.hitbox = new SoldierHitbox(this.x, this.y);
	}
	public void render(Graphics g){
		g.drawImage(this.soldier, x, y);
		this.hitbox.render(g);
	}
	
	public void move(int millisSinceLastCall){
		this.y++;
		this.hitbox.setY(this.y);
	}
	
	public void init(GameContainer gc) throws SlickException{
		this.soldier = new Image("/testdata/soldier.png");
	}
	
	public SoldierHitbox getSoldHitbox() {
		// TODO Auto-generated method stub
		return this.hitbox;
	}
}
