package ifnmg.aps.atividade1;

import java.time.LocalTime;
import java.util.ArrayList;

public class SalaReuniao {
	
	private int numero;
	private int qtdLugares;
	private ReservaGerenciador reservaGerenciador;
	
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
	
	public SalaReuniao() {
		this.reservaGerenciador = ReservaGerenciador.getInstance();
	}
	
	public boolean verificarDisponibilidade(Intervalo intervalo) {
		ArrayList<Reserva> reservas = reservaGerenciador.listarPorSala(this);
		
		LocalTime intervaloInicio = intervalo.getHoraInicio();
		LocalTime intervaloFim = intervalo.getHoraFim();
		
		for (Reserva reserva : reservas) {
			if (reserva.getDataLocacao().compareTo(intervalo.getData()) != 0) {
				continue;
			}

			LocalTime reservaInicio = reserva.getHoraInicio();
			LocalTime reservaFim = reserva.getHoraFim();
			
			if (reservaInicio.isBefore(intervaloInicio) && reservaFim.isAfter(intervaloInicio)) {
				return false;
			}
			
			if (reservaInicio.isAfter(intervaloInicio) && reservaInicio.isBefore(intervaloFim)) {
				return false;
			}
		}
		
		return true;
	}
	
}
