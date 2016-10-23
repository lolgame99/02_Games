package at.mab.mygames.ClassGame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class TankActor {
	
	private int x,y;
	private boolean Up, Down, Left,Right;
	private static final int speed = 5;
	private Animation animation;
	private TankHitbox hitbox;
	
	public TankActor(int x, int y) throws SlickException{
		this.x = x;
		this.y = y;
		
		SpriteSheet tankSprite = new SpriteSheet("testdata/scroller/tank_sprite.png", 32, 32, new Color(255,255,255));
		this.animation = new Animation();
		for (int i=0;i<7;i++) {
			animation.addFrame(tankSprite.getSprite(i,0), 150);
		this.hitbox = new TankHitbox(this.x, this.y);
		}
	}
	
	public void move(int millisSinceLastCall, GameContainer gc){
		
		getInput(gc);
		this.hitbox.update(millisSinceLastCall, gc);
		moveTank(gc);
	}

	private void moveTank(GameContainer gc) {
		if (this.Up==true && this.y>=0) {
			this.y = this.y - speed;
			this.hitbox.setY(this.y);
		}
		if (this.Down==true && this.y<=gc.getHeight()-64) {
			this.y = this.y + speed;
			this.hitbox.setY(this.y);
		}	
		if (this.Left==true && this.x>=0) {
			this.x = this.x - speed;
			this.hitbox.setX(this.x);
		}	
		if (this.Right==true && this.x<=gc.getWidth()-64) {
			this.x = this.x + speed;
			this.hitbox.setX(this.x);
		}
	}

	private void getInput(GameContainer gc) {
		Up = gc.getInput().isKeyDown(Input.KEY_W);
		Down = gc.getInput().isKeyDown(Input.KEY_S);
		Left = gc.getInput().isKeyDown(Input.KEY_A);
		Right = gc.getInput().isKeyDown(Input.KEY_D);
	}

	
	

	public void render(Graphics g) throws SlickException{
		this.hitbox.render(g);
		this.animation.draw(this.x,this.y,32*2,32*2);
	}	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public TankHitbox getTankHitbox() {
		// TODO Auto-generated method stub
		return this.hitbox;
	}
}
