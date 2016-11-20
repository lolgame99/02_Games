package at.mab.mygames.RPGTest;




import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;




public class Starter extends BasicGame{
	
	//Window Size
	private static final int WIDTH = 1600;
	private static final int HEIGHT = 900;
	
	private TiledMap map;
	private float posX, posY;
	private int mapX, mapY;
	private boolean blocked[][];
	private ArrayList<Rectangle> blocks;
	private boolean isInCollision;
	public String direction;
	private Rectangle hitbox;
	private SpriteSheet move;
	private Animation moveUp;
	
	public Starter() {
		super("FirstGame");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		this.map.render((int)this.posX, (int)this.posY, mapX, mapY, mapX+48, mapY+27);
		g.drawString("Direction: "+this.direction, 10, 20);
		
		for (Rectangle bs : this.blocks) {
			g.draw(bs);
		}
		
		this.moveUp.draw();
		g.draw(hitbox);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		tileHitbox();
		
		this.hitbox = new Rectangle(800, 450, 40, 60);
		this.move = new SpriteSheet("testdata/player/player_walk.png", 64, 64);
		this.moveUp = new Animation();
		for (int i=1;i<9;i++) {
			moveUp.addFrame(move.getSprite(i,0), 150);
		}
	}

	
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		collisionDetection(delta);
		controlMovement(gc, delta);
	}
	
	private void tileHitbox() throws SlickException {
		this.map = new TiledMap("C:/Users/Benjamin/git/02_Games/02_Games/testdata/fickdich.tmx","C:/Users/Benjamin/git/02_Games/02_Games/testdata/");
		this.blocks = new ArrayList<Rectangle>();
		blocked = new boolean[this.map.getWidth()][this.map.getHeight()];
		int layer = 0; 
		
		for(int i = 0; i < this.map.getWidth(); i++) {
		    for(int j = 0; j < this.map.getHeight(); j++) {
		        int tileID = this.map.getTileId(i, j, layer);
		        String value = this.map.getTileProperty(tileID, "blocked", "false");
		        if(value.equals("true")) {
		            blocked[i][j] = true;
		            blocks.add(new Rectangle((float)i * 64, (float)j * 64, 64, 64));
		        }
		    }
		}
	}

	private void controlMovement(GameContainer gc, int delta) {
		if (gc.getInput().isKeyDown(Input.KEY_W)) {
			moveUp(delta);
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_S)) {
			moveDown(delta);
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_A)) {
			moveLeft(delta);
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_D)) {
			moveRight(delta);
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_W)&&gc.getInput().isKeyDown(Input.KEY_A)) {
			this.direction="ul";
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_W)&&gc.getInput().isKeyDown(Input.KEY_D)) {
			this.direction="ur";
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_S)&&gc.getInput().isKeyDown(Input.KEY_A)) {
			this.direction="dl";
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_S)&&gc.getInput().isKeyDown(Input.KEY_D)) {
			this.direction="dr";
		}
		
	}

	private void collisionDetection(int delta) {
		for (Rectangle bs : blocks) {
			if (this.hitbox.intersects(bs)) {
				switch (this.direction) {
				case "u":
					moveDown(delta);
					break;
				
				case "d":
					moveUp(delta);
					break;
				
				case "l":
					moveRight(delta);
					break;
					
				case "r":
					moveLeft(delta);
					break;
				
				case "ul":
					moveDown(delta);
					moveRight(delta);
					break;
					
				case "ur":
					moveDown(delta);
					moveLeft(delta);
					break;
					
				case "dl":
					moveUp(delta);
					moveRight(delta);
					break;
					
				case "dr":
					moveUp(delta);
					moveLeft(delta);
					break;
				
				default:
					break;
				}
			}
		}
	}

	private void moveRight(int delta) {
		this.direction="r";
		this.posX -= delta/5f;
		for (Rectangle bs : blocks) {
			float centerX = bs.getCenterX();
			bs.setCenterX(centerX-=delta/5f);
		}
	}

	private void moveLeft(int delta) {
		this.direction="l";
		this.posX += delta/5f;
		for (Rectangle bs : blocks) {
			float centerX = bs.getCenterX();
			bs.setCenterX(centerX+=delta/5f);
		}
	}

	private void moveDown(int delta) {
		this.direction="d";
		this.posY -= delta/5f;
		for (Rectangle bs : blocks) {
			float centerY = bs.getCenterY();
			bs.setCenterY(centerY-=delta/5f);
		}
	}

	private void moveUp(int delta) {
		this.direction="u";
		this.posY += delta/5f;
		for (Rectangle bs : blocks) {
			float centerY = bs.getCenterY();
			bs.setCenterY(centerY+=delta/5f);
		}
	}

	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new Starter());
			container.setDisplayMode(WIDTH,HEIGHT,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	

	}

