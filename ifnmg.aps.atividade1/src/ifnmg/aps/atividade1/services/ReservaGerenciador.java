package ifnmg.aps.atividade1.services;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ifnmg.aps.atividade1.dtos.Intervalo;
import ifnmg.aps.atividade1.dtos.ReservaAgrupamento;
import ifnmg.aps.atividade1.entities.Equipamento;
import ifnmg.aps.atividade1.entities.Reserva;
import ifnmg.aps.atividade1.entities.SalaReuniao;
import ifnmg.aps.atividade1.enums.Exceptions;
import ifnmg.aps.atividade1.enums.PeriodoTipoEnum;

public class ReservaGerenciador {

	private static ReservaGerenciador instance;
	
	public static ReservaGerenciador getInstance() {
		if (instance == null) {
			instance = new ReservaGerenciador();
		}
		return instance;
	}
	
	private ArrayList<Reserva> reservas = new ArrayList<Reserva>();

	public void initData(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}

	public ArrayList<Reserva> listarPorSala(SalaReuniao salaReuniao) {
		if (reservas.isEmpty()) {
			return new ArrayList<Reserva>();
		}
		
		List<Reserva> reservasEncontradas = reservas
			.stream()
			.filter(r -> r.getSalaReuniao() == salaReuniao)
			.collect(Collectors.toList());
		
		return new ArrayList<Reserva>(reservasEncontradas);
	}

	public ArrayList<ReservaAgrupamento> listarPorDia() {
		if (reservas.isEmpty()) {
			return null;
		}
		
		List<LocalDate> diasDistintos = reservas
			.stream()
			.map(r -> r.getDataLocacao())
			.distinct()
			.sorted()
			.collect(Collectors.toList());
		
		ArrayList<ReservaAgrupamento> agrupamentos = new ArrayList<ReservaAgrupamento>();
		
		for (LocalDate dia : diasDistintos) {
			List<Reserva> reservasDoDia = reservas
				.stream()
				.filter(r -> r.getDataLocacao() == dia)
				.collect(Collectors.toList());
			
			ReservaAgrupamento agrupamento = new ReservaAgrupamento();
			agrupamento.setPeriodoTipo(PeriodoTipoEnum.Dia);
			agrupamento.setData(dia);
			agrupamento.setReservas(new ArrayList<Reserva>(reservasDoDia));
			
			agrupamentos.add(agrupamento);
		}
		
		return agrupamentos;
	}

	public ArrayList<ReservaAgrupamento> listarPorSemana() {
		if (reservas.isEmpty()) {
			return null;
		}
		
		List<Integer> semanasDistintas = reservas
			.stream()
			.map(r -> r.getDataLocacao().get(WeekFields.SUNDAY_START.weekOfWeekBasedYear()))
			.distinct()
			.sorted()
			.collect(Collectors.toList());
		
		ArrayList<ReservaAgrupamento> agrupamentos = new ArrayList<ReservaAgrupamento>();
		
		for (Integer semana : semanasDistintas) {
			List<Reserva> reservasDaSemana = reservas
				.stream()
				.filter(r -> r.getDataLocacao().get(WeekFields.SUNDAY_START.weekOfWeekBasedYear()) == semana)
				.collect(Collectors.toList());
			
			ReservaAgrupamento agrupamento = new ReservaAgrupamento();
			agrupamento.setPeriodoTipo(PeriodoTipoEnum.Semana);
			agrupamento.setSemana(semana);
			agrupamento.setReservas(new ArrayList<Reserva>(reservasDaSemana));
			
			agrupamentos.add(agrupamento);
		}
		
		return agrupamentos;
	}

	public ArrayList<ReservaAgrupamento> listarPorMes() {
		if (reservas.isEmpty()) {
			return null;
		}
		
		List<Month> mesesDistintos = reservas
			.stream()
			.map(r -> r.getDataLocacao().getMonth())
			.distinct()
			.sorted()
			.collect(Collectors.toList());
		
		ArrayList<ReservaAgrupamento> agrupamentos = new ArrayList<ReservaAgrupamento>();
		
		for (Month mes : mesesDistintos) {
			List<Reserva> reservasDoMes = reservas
				.stream()
				.filter(r -> r.getDataLocacao().getMonth() == mes)
				.collect(Collectors.toList());
			
			ReservaAgrupamento agrupamento = new ReservaAgrupamento();
			agrupamento.setPeriodoTipo(PeriodoTipoEnum.Mes);
			agrupamento.setMes(mes);
			agrupamento.setReservas(new ArrayList<Reserva>(reservasDoMes));
			
			agrupamentos.add(agrupamento);
		}
		
		return agrupamentos;
	}
	
	public Reserva criarReserva(SalaReuniao sala, Intervalo intervalo, String assunto) throws Exception {
		if (!sala.verificarDisponibilidade(intervalo)) {
			throw new Exception(Exceptions.SALA_INDISPONIVEL.getMessage());
		}
		
		Reserva reserva = new Reserva();
		reserva.setSalaReuniao(sala);
		reserva.setDataLocacao(intervalo.getData());
		reserva.setHoraInicio(intervalo.getHoraInicio());
		reserva.setHoraFim(intervalo.getHoraFim());
		reserva.setAssunto(assunto);
		reserva.setAtiva(true);
		reserva.setEquipamentos(new ArrayList<Equipamento>());

		if (reservas == null) {
			reservas = new ArrayList<Reserva>();
		}
		reservas.add(reserva);
		
		return reserva;
	}
	
	public boolean verificarUsoEquipamento(Equipamento equipamento) {
		List<Equipamento> equipamentosSendoUtilizados = reservas
			.stream()
			.map(r -> r.getEquipamentos())
			.flatMap(ArrayList::stream)
			.collect(Collectors.toList());
		
		return equipamentosSendoUtilizados
			.stream()
			.anyMatch(e -> e.equals(equipamento));
	}
	
}
