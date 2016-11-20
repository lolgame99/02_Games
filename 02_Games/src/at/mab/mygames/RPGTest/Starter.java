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
	private Animation moveUpA;
	private Animation moveDownA;
	private Animation moveLeftA;
	private Animation moveRightA;
	private boolean giveDirection;
	
	public Starter() {
		super("FirstGame");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		this.map.render((int)this.posX, (int)this.posY, mapX, mapY, mapX+48, mapY+27);
		g.drawString("Direction: "+this.direction, 10, 30);
		
		for (Rectangle bs : this.blocks) {
			g.draw(bs);
		}
		
		g.draw(hitbox);
		
		if (this.direction=="u"&!gc.getInput().isKeyDown(Input.KEY_W)) {
			this.move.draw(768, 418, 832, 482, 0, 0, 64, 64);
		}
		
		if (this.direction=="d"&!gc.getInput().isKeyDown(Input.KEY_S)) {
			this.move.draw(768, 418, 832, 482, 0, 128, 64, 192);
		}
		
		if (this.direction=="l"&!gc.getInput().isKeyDown(Input.KEY_A)) {
			this.move.draw(768, 418, 832, 482, 0, 64, 64, 128);
		}
		
		if (this.direction=="r"&!gc.getInput().isKeyDown(Input.KEY_D)) {
			this.move.draw(768, 418, 832, 482, 0, 192, 64, 256);
		}
		
		if (this.direction=="u"&&gc.getInput().isKeyDown(Input.KEY_W)) {
			this.moveUpA.draw(768, 418);
		}
		
		if (this.direction=="d"&&gc.getInput().isKeyDown(Input.KEY_S)) {
			this.moveDownA.draw(768, 418);
		}
		
		if (this.direction=="l"&&gc.getInput().isKeyDown(Input.KEY_A)) {
			this.moveLeftA.draw(768, 418);
		}
		
		if (this.direction=="r"&&gc.getInput().isKeyDown(Input.KEY_D)) {
			this.moveRightA.draw(768, 418);
		}
		
		if (this.direction=="ur"&&gc.getInput().isKeyDown(Input.KEY_D)) {
			this.moveRightA.draw(768, 418);
		}
		
		if (this.direction=="dr"&&gc.getInput().isKeyDown(Input.KEY_D)) {
			this.moveRightA.draw(768, 418);
		}
		
		if (this.direction=="ul"&&gc.getInput().isKeyDown(Input.KEY_A)) {
			this.moveLeftA.draw(768, 418);
		}
		
		if (this.direction=="dl"&&gc.getInput().isKeyDown(Input.KEY_A)) {
			this.moveLeftA.draw(768, 418);
		}
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		tileHitbox();
		
		this.direction = "d";
		this.hitbox = new Rectangle(800, 450, 30, 46);
		this.move = new SpriteSheet("testdata/player/player_walk.png", 64, 64);
		this.moveUpA = new Animation();
		this.moveDownA = new Animation();
		this.moveLeftA = new Animation();
		this.moveRightA = new Animation();
		this.hitbox.setCenterX(800);
		this.hitbox.setCenterY(456);
		for (int i=1;i<9;i++) {
			moveUpA.addFrame(move.getSprite(i,0), 150);
			moveDownA.addFrame(move.getSprite(i,2), 150);
			moveLeftA.addFrame(move.getSprite(i,1), 150);
			moveRightA.addFrame(move.getSprite(i,3), 150);
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
					moveDown(delta);
					break;
				
				case "d":
					moveUp(delta);
					moveUp(delta);
					break;
				
				case "l":
					moveRight(delta);
					moveRight(delta);
					break;
					
				case "r":
					moveLeft(delta);
					moveLeft(delta);
					break;
				
				case "ul":
					moveDown(delta);
					moveRight(delta);
					moveDown(delta);
					moveRight(delta);
					break;
					
				case "ur":
					moveDown(delta);
					moveLeft(delta);
					moveDown(delta);
					moveLeft(delta);
					break;
					
				case "dl":
					moveUp(delta);
					moveRight(delta);
					moveUp(delta);
					moveRight(delta);
					break;
					
				case "dr":
					moveUp(delta);
					moveLeft(delta);
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

