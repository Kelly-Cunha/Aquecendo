package com.kellycunha.world;

import com.kellycunha.entities.Entity;
import com.kellycunha.entities.Tubo;
import com.kellycunha.main.Game;

public class TuboGenerator {

	public int time = 0;
	public int targetTime = 60;
	
	public void tick() {
		time++;
		if(time == 60) {
			//Aqui criamos nosso tubo novo e resetamos nosso contador.
			int altura1 = Entity.rand.nextInt(50 - 30) + 30;
			Tubo tubo1 = new Tubo(Game.WIDTH,0,20,altura1,1,Game.spritesheet.getSprite(32, 32, 16, 16));
			
			int altura2 = Entity.rand.nextInt(50 - 30) + 30;
			Tubo tubo2 = new Tubo(Game.WIDTH,Game.HEIGHT - altura2,20,altura2,1,Game.spritesheet.getSprite(32+16, 32, 16, 16));
			
			Game.entities.add(tubo1);
			Game.entities.add(tubo2);
			time = 0;
		}
		
	}
	
}
