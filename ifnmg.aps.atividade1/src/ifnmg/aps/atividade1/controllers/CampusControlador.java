package ifnmg.aps.atividade1.controllers;

import java.util.ArrayList;

import ifnmg.aps.atividade1.dtos.Intervalo;
import ifnmg.aps.atividade1.dtos.ReservaAgrupamento;
import ifnmg.aps.atividade1.entities.Campus;
import ifnmg.aps.atividade1.entities.Equipamento;
import ifnmg.aps.atividade1.entities.Predio;
import ifnmg.aps.atividade1.entities.Reserva;
import ifnmg.aps.atividade1.entities.SalaReuniao;
import ifnmg.aps.atividade1.enums.Exceptions;
import ifnmg.aps.atividade1.enums.PeriodoTipoEnum;
import ifnmg.aps.atividade1.services.ReservaGerenciador;

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
	
	public ArrayList<SalaReuniao> consultarSalasLivres(Intervalo intervalo) throws Exception {
		if (intervalo == null || !intervalo.isValid()) {
			throw new Exception(Exceptions.INTERVALO_INVALIDO.getMessage());
		}
		
		ArrayList<SalaReuniao> salasLivres = new ArrayList<SalaReuniao>();
		for (Predio predio : campusAtual.getPredios()) {
			salasLivres.addAll(predio.obterSalasLivres(intervalo));
		}
		
		if (salasLivres.isEmpty()) {
			throw new Exception(Exceptions.NENHUMA_SALA.getMessage());
		}
		
		return salasLivres;
	}
	
	public ArrayList<ReservaAgrupamento> obterListaOcupacao(PeriodoTipoEnum periodoTipo) throws Exception {
		if (periodoTipo == null) {
			throw new Exception(Exceptions.PERIODO_INVALIDO.getMessage());
		}
		
		ArrayList<ReservaAgrupamento> resultado;
		
		switch (periodoTipo) {
			case Dia:
				resultado = reservaGerenciador.listarPorDia();
				break;
			case Semana:
				resultado = reservaGerenciador.listarPorSemana();
				break;
			case Mes:
				resultado = reservaGerenciador.listarPorMes();
				break;
			default:
				throw new Exception(Exceptions.PERIODO_INVALIDO.getMessage());
		}
		
		if (resultado == null) {
			throw new Exception(Exceptions.NENHUMA_RESERVA.getMessage());
		}
		
		return resultado;
	}
	
	public Reserva reservarSala(SalaReuniao sala, Intervalo intervalo, String assunto) throws Exception {
		if (intervalo == null || !intervalo.isValid()) {
			throw new Exception(Exceptions.INTERVALO_INVALIDO.getMessage());
		}
		if (assunto == null || assunto.isBlank()) {
			throw new Exception(Exceptions.ASSUNTO_INVALIDO.getMessage());
		}
		
		return reservaGerenciador.criarReserva(sala, intervalo, assunto);
	}
	
	public ArrayList<Equipamento> obterEquipamentos() throws Exception {
		ArrayList<Equipamento> equipamentos = campusAtual.getEquipamentos();
		
		if (equipamentos == null || equipamentos.isEmpty()) {
			throw new Exception(Exceptions.NENHUM_EQUIPAMENTO.getMessage());
		}
		
		return equipamentos;
	}
	
	public boolean adicionarEquipamento(Reserva reserva, Equipamento equipamento) throws Exception {
		if (equipamento == null) {
			throw new Exception(Exceptions.EQUIPAMENTO_INVALIDO.getMessage());
		}
		if (reservaGerenciador.verificarUsoEquipamento(equipamento)) {
			throw new Exception(Exceptions.EQUIPAMENTO_INDISPONIVEL.getMessage());
		}
		
		reserva.adicionarEquipamento(equipamento);
		
		return true;
	}
	
}
