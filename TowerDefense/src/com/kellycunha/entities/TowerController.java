package com.kellycunha.entities;

import java.awt.image.BufferedImage;

import com.kellycunha.main.Game;
import com.kellycunha.world.World;

public class TowerController extends Entity{

	public boolean isPressed = false;
	public int xTarget,yTarget;
	
	public TowerController(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
	}
	
	public void tick() {
		if(isPressed) {
			isPressed = false;
			boolean liberado = true;
			//Criar tower no map
			int xx = (xTarget/16) * 16;
			int yy = (yTarget/16) * 16;
			Player player = new Player(xx,yy,16,16,0,Game.spritesheet.getSprite(17, 17, 14, 16));
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Player) {
					if(Entity.isColidding(e, player)) {
						liberado = false;
						System.out.println("Tower exist, don't other");
					}
				}
			}
			
			if(World.isFree(xx, yy)) {
				liberado = false;
				System.out.println("Tower here not");
			}
			
			if(liberado)
				if(Game.coins >= 2) {
			Game.entities.add(player);
			Game.coins-=2;
			
				}else {
					System.out.println("Don't have coins");
				}
		}
		if(Game.life <= 0)
		{
			System.exit(1);
		}
	}
}
