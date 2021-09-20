package Homenagem5;

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
		frame = new JFrame("Ana Beatriz");
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
		
		/*Renderização*/
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(player[curAnimation], 238, 125, null);
		g2.rotate(Math.toRadians(10),90+8,90+8);
		g2.setColor(Color.pink);
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.white);
		g.fillOval(5, 12, 61, 61);
		g.setColor(Color.pink);
		g.fillOval(0, 11, 60, 60);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,10));
		g.setColor(Color.white);
		g.drawString("out/2021", 18, 42);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,25));
		g.setColor(Color.pink);
		g.drawString("Ana Beatriz", 193,0);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,16));
		g.setColor(Color.white);
		g.drawString("Bem-vinda,", 85,22);
		g.drawString("sobrinha linda e abençoada.", 125,37);
		g.drawString("A notícia da sua existência me trouxe", 70,67);
		g.drawString("alegria a cada pulsar do meu coração.", 65,82);
		g.drawString("Sei que você é Menina dos olhos do Senhor", 20,108);
		g.drawString("Deus cuidou e sempre cuidará de você", 3,123);
		g.drawString("para a eternidade.", 172,136);
		

		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,12));
		g.setColor(Color.white);
		g.drawString("Te amo.", 95,148);
		g.drawString("tia Kelly", 115,160);
		
		
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
