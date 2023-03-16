package ifnmg.aps.atividade1;

public class SalaReuniao {
	private int id;
	private int numero;
	private int qtdLugares;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public int getQtdLugares() {
		return qtdLugares;
	}
	public void setQtdLugares(int qtdLugares) {
		this.qtdLugares = qtdLugares;
	}
	
	public boolean verificarDisponibilidade(Intervalo intervalo) {
		return false;
	}
}
