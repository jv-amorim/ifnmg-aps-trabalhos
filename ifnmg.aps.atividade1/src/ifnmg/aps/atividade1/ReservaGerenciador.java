package ifnmg.aps.atividade1;

import java.util.ArrayList;

public class ReservaGerenciador {

	private static ReservaGerenciador instance;
	
	public static ReservaGerenciador getInstance() {
		if (instance == null) {
			instance = new ReservaGerenciador();
		}
		return instance;
	}
	
	private ArrayList<Reserva> reservas;

	public void initData(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}

	public ArrayList<Reserva> listarPorSala(SalaReuniao salaReuniao) {
		ArrayList<Reserva> reservasEncontradas = new ArrayList<Reserva>();		
		
		for (Reserva reserva : reservas) {
			if (reserva.getSalaReuniao() == salaReuniao) {				
				reservasEncontradas.add(reserva);
			}
		}
		
		return reservasEncontradas;
	}

	public ArrayList<ReservaAgrupamento> listarPorDia() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ReservaAgrupamento> listarPorSemana() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ReservaAgrupamento> listarPorMes() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
