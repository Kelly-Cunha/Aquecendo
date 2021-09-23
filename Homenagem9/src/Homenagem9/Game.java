package Homenagem9;

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
		frame = new JFrame("Christmas");
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
		g.setColor(Color.red);
		g.fillRect(0, 0,WIDTH,HEIGHT);
		
		
		/*Renderização*/ //rotação
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(player[curAnimation], 238, 125, null);
		g2.rotate(Math.toRadians(3),90+8,90+8);
		
		g2.setColor(Color.white);
		g2.fillRect(6, 4,WIDTH,HEIGHT);
		
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,18));
		g.setColor(Color.green);
		g.drawString("JESUS", 138,21);
		g.setColor(Color.red);
		g.drawString("JESUS", 137,21);		

		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,12));
		g.setColor(Color.green);
		g.drawString("Obrigada pela esperança no porvir resgatada com", 31,37);
		g.setColor(Color.red);
		g.drawString("Teu sangue vertido na cruz do Calvário.", 58,53);
		g.setColor(Color.orange);
		g.drawString("Obrigada por enviar o Consolador para me inspirar,", 29,71);
		g.drawString("motivar, fortalecer, transformar e", 68,86);
		g.drawString("fazer meu coração pulsar vigorosamente", 50,101);
		g.drawString("todos os dias: Hosana!", 98,117);
		
		g.setColor(Color.green);
		g.drawString("O Teu nascimento NUNCA foi em vão:", 55,131);
		g.drawString("Quero te amar cada dia mais.", 85,143);
		g.drawString("FeLiz AniVerSáriO!!!", 107,156);
		g.setColor(Color.red);
		g.drawString("O Teu nascimento NUNCA foi em vão:", 54,131);
		g.drawString("Quero te amar cada dia mais.", 84,143);
		g.drawString("FeLiz AniVerSáriO!!!", 106,156);
		
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,8));
		g.setColor(Color.lightGray);
		g.drawString("Dezembro/2021", 280,0);
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,10));
		g.setColor(Color.green);
		g.drawString("Kelly Cunha", 128,167);
		g.setColor(Color.red);
		g.drawString("Kelly Cunha", 127,167);
		
		
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