package teste_curso_games;

public class Player {

	public int vida = 10;
	public String nome = "Floquinho";
	
	
	public void perderVida() {
		vida--;
	}
	
	public void ganharVida() {
		vida+=1;
	}
	
}
