package homenagem;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Play extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	private final int WIDTH = 456;
	private final int HEIGHT = 245;
	private final int SCALE = 3; 
	
	private BufferedImage image;
					
	public Play() {
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	}
	
	public void initFrame() {
		frame = new JFrame("Nenebeth & Charlestown");
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
		Play play = new Play();
		play.start();
	}
	
	public void tick() {
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.white);
		g.fillOval(322, 16, 50, 50);
		g.setColor(Color.black);
		g.fillOval(317, 16, 48, 48);	
		
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,20));
		g.setColor(Color.green);
		g.drawString("Nenebeth & Charlestown", 14, 25);
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,20));
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,18));
		g.setColor(Color.green);
		g.drawString("Revesti-vos de amor,", 30, 80);
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,18));
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,18));
		g.setColor(Color.green);
		g.drawString("que é o vínculo da perfeição.", 80, 112);
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,18));
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,18));
		g.setColor(Color.green);
		g.drawString("Colossenses 3:14", 250, 144);
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,18));
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,14));
		g.setColor(Color.green);
		g.drawString("Nossos votos de felicidades com a direção de Deus", 64, 190);
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,14));
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,14));
		g.setColor(Color.green);
		g.drawString("e abundantes bênçãos aos noivos", 119, 210);
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,14));
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,14));
		g.setColor(Color.green);
		g.drawString("Ascânio, Meire, Hannele, Raphael e Kelly", 90, 230);
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,14));
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,10));
		g.setColor(Color.green);
		g.drawString("jun/2021", 400, 235);
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,10));
		
		//g.dispose();
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
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: "+ frames);
				frames = 0;
				timer+= 1000;
			}
		}
		
		stop();
	}

	
}
