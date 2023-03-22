package ifnmg.aps.atividade1.entities;

import java.time.LocalTime;
import java.util.ArrayList;

import ifnmg.aps.atividade1.dtos.Intervalo;
import ifnmg.aps.atividade1.services.ReservaGerenciador;

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
			
			boolean result =
				(reservaInicio.isBefore(intervaloInicio) || reservaInicio.equals(intervaloInicio))
				&& (reservaFim.isAfter(intervaloInicio) || reservaFim.equals(intervaloInicio));
			
			if (result) {
				return false;
			}
			
			result =
				(reservaInicio.isAfter(intervaloInicio) || reservaInicio.equals(intervaloInicio))
				&& (reservaInicio.isBefore(intervaloFim) || reservaInicio.equals(intervaloFim));
			
			if (result) {
				return false;
			}
		}
		
		return true;
	}
	
}
