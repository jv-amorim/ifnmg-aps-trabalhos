package ifnmg.aps.atividade1;

import java.util.ArrayList;

public class Predio {
	
	private String nome;
	private ArrayList<SalaReuniao> salas;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<SalaReuniao> getSalas() {
		return salas;
	}
	public void setSalas(ArrayList<SalaReuniao> salas) {
		this.salas = salas;
	}
	
	public ArrayList<SalaReuniao> obterSalasLivres(Intervalo intervalo) throws Exception {
		ArrayList<SalaReuniao> salasLivres = new ArrayList<SalaReuniao>();
		
		for (SalaReuniao sala : this.salas) {
			if (sala.verificarDisponibilidade(intervalo)) {
				salasLivres.add(sala);
			}
		}
		
		return salasLivres;
	}
	
}
