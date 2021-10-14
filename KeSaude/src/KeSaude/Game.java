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
	private BufferedImage[] playerx;
	private BufferedImage[] playery;
	private int x = 0;
	private int y = 445;
	private int frames = 0;
	private int maxFrames = 85;
	private int curAnimation = 0,maxAnimation = 5;
	
	public Game() {
		sheet = new Spritesheet("/spritesheet.png");
		playerx = new BufferedImage[5];
		playerx[0] = sheet.getSprite(0, 0, 53, 16);
		playerx[1] = sheet.getSprite(0, 0, 53, 16);
		playerx[2] = sheet.getSprite(0, 0, 53, 16);
		playerx[3] = sheet.getSprite(100, 0, 22, 19);
		playerx[4] = sheet.getSprite(123, 0, 22, 19);
		
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
		y--;
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
		g.setColor(Color.green);
		g.fillRect(0, 0,WIDTH,HEIGHT);
		

		/*Renderização*/
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(playerx[curAnimation],x,49,null);
		g2.drawImage(playerx[curAnimation],y,3,null);
		g2.drawImage(playerx[curAnimation],x,144,null);
		g2.drawImage(playerx[curAnimation],y,182,null);
		
		g.setColor(Color.DARK_GRAY);
		g.drawString("Ao longo da vida visitei pacientes hospitalizados", 115, 34);
		g.drawString("com o propósito de orar com eles e animá-los.", 133, 47);
		g.drawString("Décadas se passaram e os números de hospitais aumentaram", 55, 58);
		g.drawString("conforme aumenta a quantidade de pessoas doentes,", 5, 71);
		g.drawString("além de pessoas acidentadas.", 205, 82);
		g.drawString("Quero te apresentar um modo diferente de se viver", 85, 94);
		g.drawString("independente da faixa etária e que muitos desfrutem da qualidade de vida.", 20, 105);
		g.drawString("Assim, vamos resolver questões como queda de cabelo, descontrole hormonal,", 5, 116);
		g.drawString("acne, pressão, varizes e muito mais até que o seu corpo esteja equilibrado:", 19, 128);
		g.drawString("sua pele, seu humor, SEU CORPO transmitirá esta novidade de vida.", 40, 140);
		g.drawString("Isso é possível com medicamentos naturais e com economia.", 70, 152);
		g.drawString("Para quem se encontra tomando remédio inicialmente você NÃO", 55, 163);
		g.drawString("vai tirar o seu medicamento até que VOCÊ chegue ao ponto de \r\n"
				+ "		escolher:", 35, 175);
		g.drawString("1. Ou você desmama do medicamento", 120, 186);
		g.drawString("2. Ou para com o tratamento natural", 120, 196);
		g.drawString("Porque a sua saúde vai começar a normalizar gradativamente.", 65, 207);
		g.drawString("Aguardo o seu contato", 175, 222);
		g.drawString("Desejo a você vida longa e saudável", 135, 236);
	
		g.setColor(Color.red);
		g.drawString("Ke'Saúde", 369, 240);
		
		
		g.setFont(new Font("Arial",Font.LAYOUT_NO_START_CONTEXT,14));
		g.setColor(Color.yellow);
		g.drawString("Kelly Cunha", 357, 229);
		
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