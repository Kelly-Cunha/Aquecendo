package com.kellycunha.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.kellycunha.main.Game;

public class UI {

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,18));
		g.drawString("Apples: "+Game.fruits_atual+"/"+Game.fruits_contagem, 30,30);
	}
	
}
