package Zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {

	public static List<Block> blocos = new ArrayList<Block>();
	
	public World() {
		for(int xx = 0; xx < 42; xx++) {
			blocos.add(new Block(xx*32,0));
		}
		for(int xx = 0; xx < 42; xx++) {
			blocos.add(new Block(xx*32,696-32));
		}
		for(int yy = 0; yy < 42; yy++) {
			blocos.add(new Block(0,yy*32));
		}
		for(int yy = 0; yy < 42; yy++) {
			blocos.add(new Block(1370-32,yy*32));
		}
		
		blocos.add(new Block(180,132));
	}
		
	public static boolean isFree(int x, int y) {
		for(int i = 0; i< blocos.size(); i++) {
			Block blocoAtual = blocos.get(i);
			if(blocoAtual.intersects(new Rectangle(x,y,32,32))) {
				return false;
			}
		}
		return true;
	}
	
	public void render(Graphics g) {
		for(int i = 0; i< blocos.size(); i++) {
			blocos.get(i).render(g);
		}
	}
	
}
