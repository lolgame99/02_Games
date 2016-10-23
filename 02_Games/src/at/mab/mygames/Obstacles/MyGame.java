package at.mab.mygames.Obstacles;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import java.util.Random;


public class MyGame extends BasicGame {

	private ArrayList<Obstacle> obstacles;
	private Random rand;
	private Player player;
	public MyGame() {
		super("MyGame");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for (Obstacle o: obstacles) {
			o.render(g);
		}
		this.player.render(g);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.rand = new Random();
		this.obstacles = new ArrayList<>();
		this.player = new Player(400,500);
		for (int i = 0; i < 100; i++) {
			int x = rand.nextInt(800);
			int y = rand.nextInt(600)-600;
			int r = rand.nextInt(5)+5;
			float speed = rand.nextFloat();
			this.obstacles.add(new Obstacle(x, y, r, speed));
		}
		
	}

	@Override
	public void update(GameContainer gc, int millisSinceLastCall) throws SlickException {
		for (Obstacle o: obstacles) {
			o.move(millisSinceLastCall);
		}
		this.player.move(millisSinceLastCall, gc);
		
	}
	
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new MyGame());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
