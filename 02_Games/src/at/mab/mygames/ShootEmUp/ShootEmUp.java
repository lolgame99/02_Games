package at.mab.mygames.ShootEmUp;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import at.mab.mygames.ShootEmUp.ShootEmUp;

public class ShootEmUp extends BasicGame{
	
	Image tank;
	Image background;
	private int tankX, tankY, tankX2, tankY2;
	private boolean tankUp, tankDown, tankLeft,tankRight;
	
	public ShootEmUp() {
		super("ShootEmUp");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		g.drawImage(this.tank, this.tankX, this.tankY, this.tankX2, this.tankY2, 0, 32, 32, 64);
		
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.background = new Image("/testdata/dirt.jpg");
		this.tank = new Image("/testdata/scroller/sprites.png");
		this.tankX = 384;
		this.tankY = 200;
		this.tankX2 = this.tankX +64;
		this.tankY2 = this.tankY +64;
	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		getInput(gc);
		moveTank(gc);	
	}

	private void moveTank(GameContainer gc) {
		if (this.tankUp==true && this.tankY>=0) {
			this.tankY--;
			this.tankY2--;
		}
		if (this.tankDown==true && this.tankY<=gc.getHeight()-64) {
			this.tankY++;
			this.tankY2++;
		}	
		if (this.tankLeft==true && this.tankX>=0) {
			this.tankX--;
			this.tankX2--;
		}	
		if (this.tankRight==true && this.tankX<=gc.getWidth()-64) {
			this.tankX++;
			this.tankX2++;
		}
	}

	private void getInput(GameContainer gc) {
		tankUp = gc.getInput().isKeyDown(Input.KEY_W);
		tankDown = gc.getInput().isKeyDown(Input.KEY_S);
		tankLeft = gc.getInput().isKeyDown(Input.KEY_A);
		tankRight = gc.getInput().isKeyDown(Input.KEY_D);
	}

	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new ShootEmUp());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
