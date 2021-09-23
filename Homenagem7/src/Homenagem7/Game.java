package Homenagem7;

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
	private int maxFrames = 45;
	private int curAnimation = 0,maxAnimation = 2;
	
	public Game(){
		sob = new Spritesheet("/sob.png");
		player = new BufferedImage[2];
		player[0] = sob.getSprite(0,0,16,31);
		player[1] = sob.getSprite(18,0,16,31);
		
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	}
	
	public void initFrame() {
		frame = new JFrame("Hannele");
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
		g.setColor(Color.white);
		g.fillRect(0, 0,WIDTH,HEIGHT);
		
		
		/*Renderização*/ //rotação
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(player[curAnimation], 238, 125, null);
		g2.rotate(Math.toRadians(3),90+8,90+8);
		g2.setColor(Color.blue);
		g2.fillRect(5, 3, WIDTH, HEIGHT);
		/*
		g2.setColor(Color.green);
		g2.fillRect(7, 5, WIDTH, HEIGHT);
		g2.setColor(Color.orange);
		g2.fillRect(8, 6,WIDTH,HEIGHT);
		*/
		g2.setColor(Color.white);
		g2.fillRect(6, 4,WIDTH,HEIGHT);
		
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,18));
		g.setColor(Color.blue);
		g.drawString("Hannele", 75,21);
		g.setColor(Color.green);
		g.drawString("Hannele", 74,21);		

		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,12));
		g.setColor(Color.blue);
		g.drawString("Comemorar a sua existência me alegra, me motiva,", 48,40);
		g.drawString("me inspira, me fortalece,", 7,59);
		g.drawString("faz meu coração pulsar vigorosamente", 122,74);
		g.drawString("todos os dias;", 163,90);
		g.drawString("agradeço a Deus pelo presente que Ele me deu: minha filha.", 7,102);
		
		g.setColor(Color.green);
		g.drawString("Te amo,", 123,117);
		g.drawString("FeLiz AniVerSáriO!!!", 95,131);
		g.drawString("Parabéns por quem você é.", 106,150);
		g.setColor(Color.blue);
		g.drawString("Parabéns por quem você é.", 105,150);
		g.drawString("FeLiz AniVerSáriO!!!", 94,131);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,8));
		g.setColor(Color.lightGray);
		g.drawString("Novembro/2021", 282,1);
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,10));
		g.setColor(Color.blue);
		g.drawString("Kelly Cunha", 146,167);
		g.setColor(Color.green);
		g.drawString("Kelly Cunha", 145,167);
		
		
		/**/
			
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