package Homenagem3;

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
		player[0] = sob.getSprite(0,0,34,31);
		player[1] = sob.getSprite(35,0,35,31);
		
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	}
	
	public void initFrame() {
		frame = new JFrame("Batismo");
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
		//g.setColor(Color.green);
		//g.fillRect(0, 167, 342, 178);
		
		/*Renderização*/
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(player[curAnimation], 270, 92, null);
		/**/
		/*		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,25));
		g.setColor(Color.pink);
		g.drawString("Tatiana", 216,21);
		*/
		
		g.setColor(Color.yellow);
		g.fillOval(45, 38, 45, 45);
		g.setColor(Color.orange);
		g.fillOval(46, 39, 41, 41);
		
		/*
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,10));
		g.setColor(Color.lightGray);
		g.drawString("ago/2021", 2, 8);
		*/
		
		g.setColor(Color.white);
		g.fillOval(70, 40, 38, 38);
		g.setColor(Color.white);
		g.fillOval(92, 40, 38, 38);
		g.setColor(Color.white);
		g.fillOval(67, 41, 40, 38);
		g.setColor(Color.white);
		g.fillOval(89, 41, 52, 45);
		
		g.setColor(Color.white);
		g.fillOval(33, 48, 38, 38);
		g.setColor(Color.white);
		g.fillOval(55, 48, 38, 38);
		g.setColor(Color.white);
		g.fillOval(30, 49, 40, 38);
		g.setColor(Color.white);
		g.fillOval(55, 49, 45, 38);
		
		g.setColor(Color.white);
		g.fillOval(120, 41, 38, 38);
		g.setColor(Color.white);
		g.fillOval(142, 41, 38, 38);
		g.setColor(Color.white);
		g.fillOval(117, 41, 40, 38);
		g.setColor(Color.white);
		g.fillOval(139, 41, 52, 45);
		
		g.setColor(Color.white);
		g.fillOval(110, 48, 38, 38);
		g.setColor(Color.white);
		g.fillOval(132, 48, 38, 38);
		g.setColor(Color.white);
		g.fillOval(107, 48, 40, 38);
		g.setColor(Color.white);
		g.fillOval(129, 48, 52, 45);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,14));
		g.setColor(Color.blue);
		g.drawString("Filhinhos,", 50, 31);
		g.drawString("agora permaneçam Nele para que,", 70,64);
		g.drawString("quando Ele voltar,", 46,79);
		g.drawString("estejamos confiantes", 92,94);
		g.drawString("e não nos afastemos Dele,", 18,110);
		g.drawString("envergonhados.", 156,126);
		
		g.setColor(Color.gray);
		g.drawString("Filhinhos,", 51, 32);
		g.drawString("agora permaneçam Nele para que,", 71,65);
		g.drawString("quando Ele voltar,", 47,80);
		g.drawString("estejamos confiantes", 93,95);
		g.drawString("e não nos afastemos Dele,", 19,111);
		g.drawString("envergonhados.", 157,127);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,10));
		g.setColor(Color.gray);
		g.drawString("I João 2:28", 218,145);
		g.drawString("Kelly Cunha", 270,171);
		
		g.setColor(Color.blue);
		g.drawString("I João 2:28", 217,144);
		g.drawString("Kelly Cunha", 269,170);
		
		
			
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
