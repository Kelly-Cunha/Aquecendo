package Homenagem13;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	private final int WIDTH = 456;
	private final int HEIGHT = 245;
	private final int SCALE = 3;
		
	private BufferedImage image;
	
	private Spritesheet sheet;
	private BufferedImage[] playerx;
	private BufferedImage[] playery;
	private int x = 0;
	private int y = 0;
	private int frames = 0;
	private int maxFrames = 82;
	private int curAnimation = 0,maxAnimation = 10;
	
	public Game() {
		sheet = new Spritesheet("/spritesheet.png");
		playerx = new BufferedImage[5];
		playery = new BufferedImage[5];
		playerx[0] = sheet.getSprite(0, 0, 100, 35);
		playerx[1] = sheet.getSprite(0, 35, 158, 32);
		playerx[2] = sheet.getSprite(0, 70, 129, 13);
		playerx[3] = sheet.getSprite(100, 0, 19, 19);
		playerx[4] = sheet.getSprite(123, 0, 19, 19);
		
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	}
	
	public void initFrame() {
		frame = new JFrame("Aniversário");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		game.start();		
	}
	
	public void tick() {
		x++;
		frames++;
		if(frames > maxFrames) {
			frames = 0;
			curAnimation++;
			if(curAnimation > maxAnimation) {
				curAnimation = 0;
			}
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(4);
			return;
		}
		
		Graphics g = image.getGraphics();
		g.setColor(Color.gray);
		g.fillRect(0, 0,WIDTH,HEIGHT);
		

		/*Renderização*/
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(playerx[curAnimation],x,49,null);
		g2.drawImage(playerx[curAnimation],y,10,null);
		g2.drawImage(playerx[curAnimation],x,130,null);
		g2.drawImage(playerx[curAnimation],y,162,null);
		
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,18));
		g.setColor(Color.red);
		g.drawString("HAPPY BIRTHDAY!!!", 230, 32);
		g.setColor(Color.blue);
		g.drawString("¡¡¡FELIZ CUMPLEAÑOS!!!", 146, 118);
		g.setColor(Color.orange);
		g.drawString("FELIZ ANIVERSÁRIO!!!", 105, 222);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,14));
		g.setColor(Color.yellow);
		g.drawString("by Kelly Cunha", 320, 232);
		
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta>= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: "+ frames);
				frames = 0;
				timer+=1000;
			}
		}
		
		stop();
	}
}