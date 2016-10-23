package at.mab.mygames.Obstacles;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import java.util.Random;

public class Obstacle {
	private float x, y, radius, speed;
	private Random rand;

	public Obstacle(float x, float y, float radius, float speed) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.speed = speed;
		this.rand = new Random();
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void move(int millisSinceLastCall){
		this.y += millisSinceLastCall * this.speed;		
		if (this.y>600) {
			this.y = rand.nextInt(600)-600;
			this.speed = rand.nextFloat();
			this.x= rand.nextInt(800);
			
		}
	}
	
	public void render(Graphics g){
		g.fillOval(this.x, this.y, this.radius*2, this.radius*2);
	}
	

	
}
