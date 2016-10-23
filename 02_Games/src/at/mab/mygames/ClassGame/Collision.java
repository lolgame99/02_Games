package at.mab.mygames.ClassGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Collision {

	private boolean collision;
	private Image blood;
	private int timer;
	private boolean timerGo;
	private static final int time = 100;
	
	public Collision(boolean c) throws SlickException{
		this.collision = c;
		this.blood = new Image("testdata/blood.png");
		this.timer = time; 
	}
	
	public void update(int millisSinceLastCall, GameContainer gc){
		if (this.collision) {
			this.timerGo = true;
		}
		
		if (this.timerGo) {
			this.timer--;
			if (this.timer<=0) {
				this.timerGo=false;
				this.timer=time;
				
			}
			
		}
		System.out.println("Timer:" +timerGo);
	}
	
	public void render(Graphics g) throws SlickException{
		if (timerGo&&timer>0) {
			this.blood.draw(0, 0, new Color(191, 15, 23, 0.9f));
		}
		
		
		
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}
}
