package KeSaude;

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
	private int maxFrames = 332;
	private int curAnimation = 0,maxAnimation = 1;
	
	public Game() {
		sheet = new Spritesheet("/spritesheet.png");
		player = new BufferedImage[1];
		//player[0] = sheet.getSprite(0, 0, 119, 75);
		player[0] = sheet.getSprite(0, 80, 100, 80);
		
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	}
	
	public void initFrame() {
		frame = new JFrame("Ke'Saúde");
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
		g.setColor(Color.orange);
		g.fillRect(0, 0,WIDTH,HEIGHT);
		

		/*Renderização*/
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(player[curAnimation],x,15,null);
		
		g.setColor(Color.DARK_GRAY);
		g.drawString("Menina/Menino dos olhos de Deus", 30, 44);
		g.drawString("Eu amo você.", 30, 56);
		g.drawString("Preciso de você.", 30, 68);
		g.drawString("Você é a parte visível de Deus.", 30, 80);
		g.drawString("Eu creio ainda mais em Deus porque você existe.", 30, 93);
		g.drawString("Deus continue contigo.", 30, 105);
		
		g.setColor(Color.darkGray);
		g.drawString("Girl/Boy of God's Eyes", 30, 142);
		g.drawString("I love you.", 30, 154);
		g.drawString("Need you.", 30, 168);
		g.drawString("You are the visible part of God.", 30, 181);
		g.drawString("I believe in God even more because you exist.", 30, 195);
		g.drawString("God stay with you.", 30, 210);
		
		g.setColor(Color.darkGray);
		g.drawString("Niña/Niño de los ojos de Dios", 210, 116);
		g.drawString("Te amo.", 210, 127);
		g.drawString("Te necesito.", 210, 138);
		g.drawString("Eres la parte visible de Dios.", 210, 150);
		g.drawString("Creo en Dios aún más porque existes.", 210, 162);
		g.drawString("Dios se quede contigo.", 210, 174);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,12));
		g.setColor(Color.DARK_GRAY);
		g.drawString("by Kelly Cunha", 327, 220);
		
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