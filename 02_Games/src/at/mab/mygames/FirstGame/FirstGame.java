package at.mab.mygames.FirstGame;



import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tests.ParticleTest;
import org.newdawn.slick.tools.packulike.Sprite;


public class FirstGame extends BasicGame{
	
	private int rectX, rectY;
	private int gcWidth, gcHeight;
	private int directionRect; // 0 = rechts, 1 = unten, 2 = links, 3 = oben
	private int circleX, circleY;
	private int directionCircle; // 0 = recht, 1= links
	private int ovalX, ovalY;
	//Image geisi; 
	private SpriteSheet sheet;
	private Animation animation;
	
	
	
	public FirstGame() {
		super("FirstGame");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		/*g.setColor(new Color(0,112,0));
		g.fillRect(this.rectX, this.rectY, 100, 100);
		g.setColor(new Color(100,100,255));
		g.fillOval(this.circleX, this.circleY, 100, 100);
		g.setColor(new Color(255,100,0));
		g.fillOval(this.ovalX, this.ovalY, 30, 150);
		*/
		//g.drawImage(geisi, rectX, rectY);
		animation.draw(200, 200,16*6,32*6);
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.gcWidth = gc.getWidth();
		this.gcHeight = gc.getHeight();
		//gc.setVSync(true);
		this.rectX = 0;
		this.rectY = 0;
		this.directionRect = 0;
		
		this.circleX = 0;
		this.circleY = 250;
		this.directionCircle = 0;
		
		this.ovalX=250;
		this.ovalY=-150;
		//geisi = new Image("C:/Users/bmaye/Desktop/geisi.png");
		
		//this.sheet = new SpriteSheet("C:/Users/Benjamin/Dropbox/Team-Ordner „FunGroup.Inc“/Graphiken/Player/Character_walking2.png", 16, 32);
		this.animation = new Animation();
		for (int i=0;i<7;i++) {
			animation.addFrame(sheet.getSprite(i,0), 150);
		}
		
	}

	@Override
	public void update(GameContainer gc, int milliSecondsLastCall) throws SlickException {
		
		//Viereck bewegen
		
		if (this.directionRect==0) {
			this.rectX++;
			if (this.rectX==700) {
				this.directionRect=1;
			}
		}
		if (this.directionRect==1) {
			this.rectY++;
			if (this.rectY==500) { //normal 500 , f�r geisi this.gcHeight-this.geisi.getHeight()
				this.directionRect=2;
			}
		}
		if (this.directionRect==2) {
			this.rectX--;
			if (this.rectX==0) {
				this.directionRect=3;	
			}
		}
		
		if (this.directionRect==3) {
			this.rectY--;
			if (this.rectY==0) {
				this.directionRect=0;
			}
		}
		
		//-----------------------------------------------------
		
		//Kreis bewegen
		if (this.directionCircle==0) {
			this.circleX++;
			if (this.circleX==700) {
				this.directionCircle=1;
			}
		}
		if (this.directionCircle==1) {
			this.circleX--;
			if (this.circleX==0) {
				this.directionCircle=0;
			}
		}
		//------------------------------------------------------
		
		//Oval bewegen
		
		this.ovalY++;
		
		if (this.ovalY==this.gcHeight+1) {
			this.ovalY=-150;
		}
		//------------------------------------------------------
	}	
	
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new FirstGame());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	

	}

