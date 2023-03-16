package ifnmg.aps.atividade1;

import java.util.ArrayList;

public class Predio {
	private int id;
	private String nome;
	private ArrayList<SalaReuniao> salas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
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
	
	public ArrayList<SalaReuniao> obterSalasLivres(Intervalo intervalo) {
		ArrayList<SalaReuniao> salasLivres = new ArrayList<SalaReuniao>();
		
		for (SalaReuniao sala : this.salas) {
			if (sala.verificarDisponibilidade(intervalo)) {
				salasLivres.add(sala);
			}
		}
		
		return salasLivres;
	}
}
