/* Github Testing Fetch */
package at.mab.mygames.ClassGame;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class ClassGame extends BasicGame {

	private TankActor tank;
	private Rectangle tankHitbox;
	private ArrayList<SoldierActor> soldiers;
	private Image background;
	private boolean collides;
	private ArrayList<Rectangle> soldHitboxes;
	private Random rand;
	private Rectangle soldierTest;

	public ClassGame() {
		super("ClassGame");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(this.background, 0, 0);
		this.tank.render(g);
		
		for (SoldierActor sold : this.soldiers) {
			sold.render(g);
		}

	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.rand = new Random();
		this.background = new Image("testdata/dirt.jpg");
		this.tank = new TankActor(368, 500);

		this.soldiers = new ArrayList<SoldierActor>();
		this.soldHitboxes = new ArrayList<Rectangle>();

		//this.soldiers.add(new SoldierActor(100, 100));
		this.tankHitbox = tank.getTankHitbox().getRectangle();
		//this.soldierTest = this.soldiers.get(0).getSoldHitbox().getRectangle();
		
		for (int i = 0; i < 3; i++) {
			this.soldiers.add(new SoldierActor(rand.nextInt(750) + 50, rand.nextInt(100) - 100));
		}
		for (SoldierActor solds : soldiers) {
			this.soldHitboxes.add(solds.getSoldHitbox().getRectangle());
		}
		

		for (SoldierActor sold : soldiers) {
			sold.init(gc);
		}

		// Random Soldier
		

	}

	@Override
	public void update(GameContainer gc, int millisSinceLastCall) throws SlickException {
		gc.setVSync(true);
		this.tank.move(millisSinceLastCall, gc);
		for (SoldierActor sold : this.soldiers) {
			sold.move(millisSinceLastCall);
		}

		// Collision detection

		for (Rectangle rec : soldHitboxes) {
			this.collides = tankHitbox.intersects(rec);
			if (collides == true) {
				System.out.println("COLLISION");
			} 
		}
		
		
	}

	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new ClassGame());
			container.setDisplayMode(800, 600, false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
