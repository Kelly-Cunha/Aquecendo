package Homenagem6;

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
		g.setColor(Color.pink);
		g.fillRect(1, 1,WIDTH,HEIGHT);
		g.setColor(Color.white);
		g.fillRect(3, 3,WIDTH,HEIGHT);
		
		/*Renderização*/ //rotação
		//Graphics2D g2 = (Graphics2D) g;
		//g2.drawImage(player[curAnimation], 238, 125, null);
		//g2.rotate(Math.toRadians(10),90+8,90+8);
		//g2.setColor(Color.pink);
		//g2.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,18));
		g.setColor(Color.darkGray);
		g.drawString("Meidson Júnior & Ediaynne Kelly", 35,19);
		g.setColor(Color.magenta);
		g.drawString("Meidson Júnior & Ediaynne Kelly", 34,20);		

		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,12));
		g.setColor(Color.magenta);
		g.drawString("Pais Brilhantes dão algo incomparavelmente", 48,34);
		g.drawString("valioso aos filhos que todo o dinheiro do mundo NÃO pode", 11,49);
		g.drawString("comprar: o seu ser, a sua história, as suas experiências,", 19,62);
		g.drawString("as suas lágrimas, o seu tempo.", 163,75);
		g.drawString("Lembre-se que a imagem que a ANA BEATRIZ constrói ", 19,89);
		g.drawString("de você NÃO pode ser apagada,", 8,104);
		g.drawString("portanto esforce-se para dar bons exemplos.", 91,116);
		
		g.setColor(Color.darkGray);
		g.drawString("Parabéns pela Aninha!!!", 106,148);
		g.setColor(Color.magenta);
		g.drawString("Parabéns pela Aninha!!!", 105,148);
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,8));
		g.setColor(Color.lightGray);
		g.drawString("Outubro/2021", 293,171);
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,10));
		g.setColor(Color.darkGray);
		g.drawString("Kelly Cunha", 146,171);
		g.setColor(Color.magenta);
		g.drawString("Augusto Cury", 273,129);
		g.drawString("Carinhosamente,", 135,161);
		g.drawString("Kelly Cunha", 145,171);
		
		
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
