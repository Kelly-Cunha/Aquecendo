package Homenagem2;

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

	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	private final int WIDTH = 343;
	private final int HEIGHT = 180;
	private final int SCALE = 4;
	
	private BufferedImage image;
	
	private Spritesheet sob;
	private BufferedImage[] player;
	private int frames = 0;
	private int maxFrames = 19;
	private int curAnimation = 0,maxAnimation = 2;
	
	public Game(){
		sob = new Spritesheet("/sob.png");
		player = new BufferedImage[2];
		player[0] = sob.getSprite(0,0,24,32);
		player[1] = sob.getSprite(24,0,24,32);
		
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	}
	
	public void initFrame() {
		frame = new JFrame("Tatiana");
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
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		game.start();
	}
	
	public void tick() {
		frames++;
		if(frames > maxFrames) {
			frames = 0;
			curAnimation++;
			if(curAnimation >= maxAnimation) {
				curAnimation = 0;
			}
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0,WIDTH,HEIGHT);
		g.setColor(Color.green);
		g.fillRect(0, 167, 342, 178);
		
		/*Renderização*/
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(player[curAnimation], 25, 145, null);
		/**/
				
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,25));
		g.setColor(Color.pink);
		g.drawString("Tatiana", 216,21);
		
		
		g.setColor(Color.white);
		g.fillOval(55, 5, 45, 45);
		//g.setColor(Color.white);
		//g.fillOval(257, 16, 41, 41);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,10));
		g.setColor(Color.lightGray);
		g.drawString("ago/2021", 2, 8);
		
		g.setColor(Color.white);
		g.fillOval(40, 60, 38, 38);
		g.setColor(Color.white);
		g.fillOval(62, 60, 38, 38);
		g.setColor(Color.black);
		g.fillOval(37, 61, 40, 38);
		g.setColor(Color.black);
		g.fillOval(59, 61, 52, 45);
		
		g.setColor(Color.white);
		g.fillOval(3, 68, 38, 38);
		g.setColor(Color.white);
		g.fillOval(25, 68, 38, 38);
		g.setColor(Color.black);
		g.fillOval(0, 69, 40, 38);
		g.setColor(Color.black);
		g.fillOval(25, 69, 45, 38);
		
		g.setColor(Color.white);
		g.fillOval(90, 61, 38, 38);
		g.setColor(Color.white);
		g.fillOval(112, 61, 38, 38);
		g.setColor(Color.black);
		g.fillOval(87, 61, 40, 38);
		g.setColor(Color.black);
		g.fillOval(109, 61, 52, 45);
		
		g.setColor(Color.white);
		g.fillOval(80, 68, 38, 38);
		g.setColor(Color.white);
		g.fillOval(102, 68, 38, 38);
		g.setColor(Color.black);
		g.fillOval(77, 68, 40, 38);
		g.setColor(Color.black);
		g.fillOval(99, 68, 52, 45);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,14));
		g.setColor(Color.lightGray);
		g.drawString("Ser feliz é reconhecer", 170, 51);
		g.drawString("que vale a pena viver", 172,66);
		g.drawString("apesar de todos os desafios,", 148,81);
		g.drawString("incompreensões e períodos de crise.", 94,96);
		g.drawString("Ser feliz é se tornar autora da própria história,", 20,112);
		g.drawString("líder de si mesma.", 158,128);
		
		g.setColor(Color.pink);
		g.drawString("Ser feliz é reconhecer", 170, 50);
		g.drawString("que vale a pena viver", 172,65);
		g.drawString("apesar de todos os desafios,", 148,80);
		g.drawString("incompreensões e períodos de crise.", 94,95);
		g.drawString("Ser feliz é se tornar autora da própria história,", 20,111);
		g.drawString("líder de si mesma.", 158,127);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,12));
		g.setColor(Color.lightGray);
		g.drawString("(Parafraseando Augusto Cury) ", 118,145);
		g.drawString("Kelly Cunha", 154,168);
		
		g.setColor(Color.pink);
		g.drawString("(Parafraseando Augusto Cury) ", 118,144);
		g.drawString("Kelly Cunha", 154,167);
		
		
			
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
			if (delta >= 1) {
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
