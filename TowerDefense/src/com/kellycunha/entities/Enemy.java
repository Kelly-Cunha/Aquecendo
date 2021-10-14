package com.kellycunha.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.kellycunha.main.Game;
import com.kellycunha.world.AStar;
import com.kellycunha.world.Vector2i;
import com.kellycunha.world.World;

public class Enemy extends Entity{
	
	public boolean right = true, left = false;
	
	public double life = 30;
	
	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		path = AStar.findPath(Game.world,new Vector2i(World.xINITIAL,World.yINITIAL),new Vector2i(World.xFINAL,World.yFINAL));
	}
	
	public void tick() {
		
		followPath(path);
		if(x >= Game.WIDTH) {
			//Perdemos vida aqui
			Game.life-=Entity.rand.nextDouble();
			Game.entities.remove(this);
			return;
		}
		
		if(life <= 0)
		{
			Game.entities.remove(this);
			Game.coins++;
			return;
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.red);
		g.fillRect((int)x,(int)(y-5),30,6);
		
		g.setColor(Color.green);
		g.fillRect((int)x,(int)(y-5),(int)((life / 30) * 30),6);
	}
}

