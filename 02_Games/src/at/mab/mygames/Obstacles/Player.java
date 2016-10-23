package at.mab.mygames.Obstacles;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Player {
	
	private float x,y;
	private boolean Left,Right;
	private static final int speed = 2;
	private Rectangle hitbox;
	private Image soldier;
	
	
	public Player(float x, float y) throws SlickException {
		this.x = x;
		this.y = y;
		this.soldier = new Image("/testdata/soldier.png");
		this.hitbox = new Rectangle(this.x, this.y, 55, 60);
	}

	public void move(int millisSinceLastCall, GameContainer gc){
		
		Left = gc.getInput().isKeyDown(Input.KEY_A);
		Right = gc.getInput().isKeyDown(Input.KEY_D);
		
		if (this.Left==true && this.x>=0) {
			this.x = this.x - speed;
			this.hitbox.setX(this.x);
		}	
		if (this.Right==true && this.x<=gc.getWidth()-64) {
			this.x = this.x + speed;
			this.hitbox.setX(this.x);
		}
	}
	
	public void render(Graphics g){
		g.drawImage(this.soldier, this.x, this.y);
		g.draw(hitbox);
	}
}
