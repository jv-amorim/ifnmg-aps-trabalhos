package ifnmg.aps.atividade1;

import java.util.ArrayList;

public class CampusControlador {
	
	private ArrayList<Campus> campi;
	private Campus campusAtual;
	private ReservaGerenciador reservaGerenciador;
	
	public CampusControlador() {
		this.reservaGerenciador = ReservaGerenciador.getInstance();
	}
	
	public void initData(ArrayList<Campus> campi) {
		this.campi = campi;
		this.campusAtual = campi.get(0);
	}
	
}
