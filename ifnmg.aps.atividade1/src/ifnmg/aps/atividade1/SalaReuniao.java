package ifnmg.aps.atividade1;

import java.time.LocalTime;
import java.util.ArrayList;

public class SalaReuniao {
	private int id;
	private int numero;
	private int qtdLugares;
	private ArrayList<Reserva> reservas;
	
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
	
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	public boolean verificarDisponibilidade(Intervalo intervalo) throws Exception {
		LocalTime intervaloInicio = intervalo.getHoraInicio();
		LocalTime intervaloFim = intervalo.getHoraFim();
		
		if (intervaloInicio.isAfter(intervaloFim)) {
			throw new Exception("A hora de inicio nao pode ser maior que a hora de termino da reserva.");
		}
		
		for (Reserva reserva : this.reservas) {
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
