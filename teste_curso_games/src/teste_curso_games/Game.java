package teste_curso_games;

import java.util.ArrayList;

public class Game {

	public static void main(String[] args) {
		
		Player player = new Player();
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		
		
		enemies.add(new ElfEnemy(100));
		enemies.add(new ElfEnemy(100));
		enemies.add(new ElfEnemy(100));
		enemies.add(new ElfEnemy(100));
		enemies.add(new ElfEnemy(100));
		
		for(int i = 0; i < enemies.size(); i++) {
			Enemy enemyLocal = enemies.get(i);
			if(i == 1) {
				enemyLocal.tomarDano();
				System.out.println(enemyLocal.vida);
			}else {
				System.out.println(enemyLocal.vida);
			}
		}
	}
}
