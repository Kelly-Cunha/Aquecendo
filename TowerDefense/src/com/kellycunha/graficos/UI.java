package com.kellycunha.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.kellycunha.main.Game;


public class UI {

	public static BufferedImage HEART = Game.spritesheet.getSprite(0,16,9,9);
	
	public void render(Graphics g) {
		for(int i = 0; i < (int)(Game.life); i ++) {
			g.drawImage(HEART,5 + (i*12),5,36,36,null);
		}
		
		g.setFont(new Font("arial",Font.BOLD,24));
		g.setColor(Color.yellow);
		g.drawString("$ "+Game.coins,(Game.WIDTH * Game.SCALE) - 120,28);
	
	}
}
