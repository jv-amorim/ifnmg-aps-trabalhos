package ifnmg.aps.atividade1;

import java.util.ArrayList;

public class ReservaGerenciador {

	private static ReservaGerenciador instance;
	
	public static ReservaGerenciador getInstance() {
		if (instance == null) {
			instance = new ReservaGerenciador();
		}
		return null;
	}
	
	private ArrayList<Reserva> reservas;

	public void initData(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}
	
}
