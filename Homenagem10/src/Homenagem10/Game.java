package Homenagem10;

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
		frame = new JFrame("2022");
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
		g.setColor(Color.lightGray);
		g.fillRect(18, 20,WIDTH,HEIGHT);
		g.setColor(Color.white);
		g.fillRect(20, 22,WIDTH,HEIGHT);
		
		/*
		//Renderização/ //rotação
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(player[curAnimation], 238, 125, null);
		g2.rotate(Math.toRadians(3),90+8,90+8);
		g2.setColor(Color.white);
		g2.fillRect(6, 4,WIDTH,HEIGHT);
		*/
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,18));
		g.setColor(Color.gray);
		g.drawString("Abençoado 2022", 98,17);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Abençoado 2022", 97,17);		
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,12));
		g.setColor(Color.gray);
		g.drawString("Que a cada milésimo de segundo o Espírito Santo nutra", 31,37);
		g.drawString("em você a esperança da volta de Jesus, assim", 53,53);
		g.drawString("receberemos a vida que foi preparada para nós:", 48,71);
		g.setColor(Color.DARK_GRAY); 
		g.drawString("E será que desde uma lua nova até à outra, e desde", 38,86);
		g.drawString("um sábado até ao outro, virá toda a carne a adorar", 42,101);
		g.drawString("perante mim, diz o Senhor. Isaías 66:23", 70,117);
		
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Breve Jesus voltará: Prepare-se!", 90,131);
		g.drawString("Falta só um pouco mais, um pouquinho mais.", 55,143);
		g.drawString("FeLiz AnO NovO!!!", 130,156);
		g.setColor(Color.gray);
		g.drawString("Breve Jesus voltará: Prepare-se!", 89,131);
		g.drawString("Falta só um pouco mais, um pouquinho mais.", 54,143);
		g.drawString("FeLiz AnO NovO!!!", 129,156);
		
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,8));
		g.setColor(Color.lightGray);
		g.drawString("Janeiro/2022", 280,169);
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,10));
		g.setColor(Color.lightGray);
		g.drawString("Kelly Cunha", 148,169);
		g.setColor(Color.gray);
		g.drawString("Kelly Cunha", 147,169);
		
		
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