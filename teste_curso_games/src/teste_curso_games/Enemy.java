package teste_curso_games;

public class Enemy {

	public int vida = 100;
	public String enimyTipo = "tipo1";
	public int ataqueDano = 10;
	
	public Enemy(int vida) {
		this.vida = vida;
		
	}
	
	public void tomarDano() {
		vida--;
	}
	
	
	public int getVida() {
		return vida;
	}
	
}
