package Homenagem1;

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
	private int maxFrames = 15;
	private int curAnimation = 0,maxAnimation = 3;
	
	public Game(){
		sob = new Spritesheet("/sob.png");
		player = new BufferedImage[3];
		player[0] = sob.getSprite(0,0,27,47);
		player[1] = sob.getSprite(28,0,27,47);
		player[2] = sob.getSprite(54,0,27,47);
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	}
	
	public void initFrame() {
		frame = new JFrame("Sofia 15 anos");
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
		g.setColor(Color.cyan);
		g.fillRect(0, 0,WIDTH,HEIGHT);
		g.setColor(Color.green);
		g.fillRect(0, 167, 342, 178);
		
		/*Renderização*/
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(player[curAnimation], 298, 120, null);
		/**/
				
		g.setFont(new Font("Arial",Font.BOLD,25));
		g.setColor(Color.magenta);
		g.drawString("S", 256,21);
		g.drawString("f", 304,70);
		g.drawString("i", 313,87);
		g.drawString("a", 321,102);
		
		g.setColor(Color.orange);
		g.fillOval(265, 14, 45, 45);
		g.setColor(Color.yellow);
		g.fillOval(267, 16, 41, 41);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,10));
		g.setColor(Color.black);
		g.drawString("ago/2021", 2, 8);
		
		g.setColor(Color.black);
		g.fillOval(40, 10, 38, 38);
		g.setColor(Color.black);
		g.fillOval(62, 10, 38, 38);
		g.setColor(Color.white);
		g.fillOval(37, 11, 40, 38);
		g.setColor(Color.white);
		g.fillOval(59, 11, 52, 45);
		
		g.setColor(Color.black);
		g.fillOval(3, 20, 38, 38);
		g.setColor(Color.black);
		g.fillOval(25, 20, 38, 38);
		g.setColor(Color.white);
		g.fillOval(0, 21, 40, 38);
		g.setColor(Color.white);
		g.fillOval(25, 21, 45, 38);
		
		g.setColor(Color.black);
		g.fillOval(90, 10, 38, 38);
		g.setColor(Color.black);
		g.fillOval(112, 10, 38, 38);
		g.setColor(Color.white);
		g.fillOval(87, 11, 40, 38);
		g.setColor(Color.white);
		g.fillOval(109, 11, 52, 45);
		
		g.setColor(Color.black);
		g.fillOval(80, 20, 38, 38);
		g.setColor(Color.black);
		g.fillOval(102, 20, 38, 38);
		g.setColor(Color.white);
		g.fillOval(77, 21, 40, 38);
		g.setColor(Color.white);
		g.fillOval(99, 21, 52, 45);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,14));
		g.setColor(Color.pink);
		g.drawString("Desejo que você esteja sempre ", 20, 51);
		g.drawString("no palco de sua vida brilhando na", 12,66);
		g.drawString("sua inteligência, alegrando-se com suas", 8,81);
		g.drawString("vitórias, aprendendo com suas derrotas e", 4,96);
		g.drawString("treinando para ser cada dia", 8,112);
		g.drawString("autora da sua história, líder de si mesma.", 8,128);
		
		g.setColor(Color.black);
		g.drawString("Desejo que você esteja sempre ", 20, 50);
		g.drawString("no palco de sua vida brilhando na", 12,65);
		g.drawString("sua inteligência, alegrando-se com suas", 8,80);
		g.drawString("vitórias, aprendendo com suas derrotas e", 4,95);
		g.drawString("treinando para ser cada dia", 8,111);
		g.drawString("autora da sua história, líder de si mesma.", 8,127);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,12));
		g.setColor(Color.pink);
		g.drawString("(Parafraseando Augusto Cury) ", 118,145);
		g.drawString("Kelly Cunha", 154,168);
		
		g.setColor(Color.black);
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
