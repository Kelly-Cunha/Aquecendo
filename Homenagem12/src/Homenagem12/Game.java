package Homenagem12;

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
	private BufferedImage[] player;
	private int x = 0;
	private int frames = 0;
	private int maxFrames = 80;
	private int curAnimation = 0,maxAnimation = 8;
	
	public Game() {
		sheet = new Spritesheet("/spritesheet.png");
		player = new BufferedImage[8];
		player[0] = sheet.getSprite(0, 28, 117, 15);
		player[1] = sheet.getSprite(0, 42, 118, 15);
		player[2] = sheet.getSprite(0, 0, 61, 14);
		player[3] = sheet.getSprite(61, 0, 14, 14);
		player[4] = sheet.getSprite(75, 0, 33, 14);
		player[5] = sheet.getSprite(0, 14, 77, 14);
		player[6] = sheet.getSprite(0, 42, 118, 15);
		player[7] = sheet.getSprite(0, 28, 117, 15);
		
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
		g.setColor(Color.pink);
		g.fillRect(0, 0,WIDTH,HEIGHT);
		g.setColor(Color.black);
		g.fillRect(0, 203, WIDTH, HEIGHT);
		g.setColor(Color.pink);
		g.fillRect(0, 206, WIDTH, HEIGHT);
		
		g.setColor(Color.magenta);
		g.fillOval(322, 30, 22, 22);
		g.fillOval(305, 30, 22, 22);
		g.setColor(Color.pink);
		g.fillOval(323, 31, 23, 22);
		g.fillOval(303, 31, 23, 22);
				
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,20));
		g.setColor(Color.white);
		g.drawString("Luciana", 109, 32);
		g.setColor(Color.magenta);
		g.drawString("Luciana", 110, 30);
		

		/*Renderização*/
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(player[curAnimation],x,189,null);
		
		/**/
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,18));
		g.setColor(Color.white);
		g.drawString("O Senhor te abençoe e te guarde;", 10, 80);
		g.drawString("O Senhor faça resplandecer o Seu rosto sobre ti...", 20, 112);
		g.drawString("O Senhor sobre ti levante o Seu rosto e te dê a paz.", 30, 142);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,14));
		g.drawString("Números 6:24-26", 295, 158);
		g.drawString("Kelly Cunha", 300, 232);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,10));
		g.setColor(Color.white);
		g.drawString("Out/2021", 400, 235);
		
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