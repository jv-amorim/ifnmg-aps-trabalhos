package ifnmg.aps.atividade1.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import ifnmg.aps.atividade1.dtos.Intervalo;
import ifnmg.aps.atividade1.dtos.ReservaAgrupamento;
import ifnmg.aps.atividade1.entities.Equipamento;
import ifnmg.aps.atividade1.entities.Reserva;
import ifnmg.aps.atividade1.entities.SalaReuniao;
import ifnmg.aps.atividade1.enums.Exceptions;
import ifnmg.aps.atividade1.enums.PeriodoTipoEnum;
import ifnmg.aps.atividade1.utils.DateTimeUtils;

public class ConsoleControlador {

	private Scanner inputScanner;
	private CampusControlador campusControlador;
	
	public ConsoleControlador(CampusControlador campusControlador) {
		this.inputScanner = new Scanner(System.in);
		this.campusControlador = campusControlador;
	}
	
	public void iniciar() {		
		escreverTitulo();
		executarLoopPrincipal();
		escreverFinalizacao();
	}
	
	private void escreverTitulo() {
		System.out.println("******************** BookARoom ********************");
	}
	
	private void escreverFinalizacao() {
		System.out.println("\n************** Execução finalizada. ***************");
	}
	
	private void executarLoopPrincipal() {
		while (true) {
			escreverOpcoes();
			String opcao = solicitarOpcaoUsuario();
			
			if (opcao.trim().equals("0")) {
				return;
			}
			
			if (opcao.trim().equals("1")) {
				executarReservaDeSala();
			}
			else if (opcao.trim().equals("2")) {
				executarConsultaDeOcupacao();
			}
			else {
				System.out.println("\n\nNenhuma opção válida escolhida.");
			}
			
			escreverSeparador();
		}
	}
	
	private void escreverOpcoes() {
		System.out.println("\nSelecione uma opção:");
		System.out.println("[1] Reservar sala;");
		System.out.println("[2] Visualizar ocupações;");
		System.out.println("[0] Sair do sistema;");
	}
	
	private String solicitarOpcaoUsuario() {
		System.out.print("\nDigite a opção escolhida: ");
		String opcao = inputScanner.nextLine();
		return opcao;
	}
	
	private void escreverSeparador() {
		System.out.println("\n---------------------------------------------------");
	}
	
	private void executarReservaDeSala() {
		escreverSeparador();
		System.out.print("Reserva de Sala");
		escreverSeparador();
		
		Intervalo intervalo = solicitarIntervalo();
		ArrayList<SalaReuniao> salasLivres;
		
		try {
			salasLivres = campusControlador.consultarSalasLivres(intervalo);
		}
		catch (Exception ex) {
			System.out.println("\n" + ex.getMessage());
			return;
		}
		
		SalaReuniao salaSelecionada = solicitarSalaParaReserva(salasLivres);
		String assunto = solicitarAssuntoDaReserva();
		Reserva reserva;
		
		try {
			reserva = campusControlador.reservarSala(salaSelecionada, intervalo, assunto);
		}
		catch (Exception ex) {
			System.out.println("\n" + ex.getMessage());
			return;
		}
		
		System.out.println("\nReserva criada com sucesso!");
		
		solicitarEquipamentos(reserva);
	}

	private Intervalo solicitarIntervalo() {
		LocalDate data = null;
		LocalTime horaInicio = null;
		LocalTime horaFim = null;
		
		while (data == null) {
			System.out.print("\nDigite a data da reserva (dd/MM/yyyy): ");
			String dataString = inputScanner.nextLine();
			try {
				data = DateTimeUtils.dateFromString(dataString);
			}
			catch (DateTimeParseException ex) {
				System.out.println("\nA data informada é inválida. Utilize o formato informado dd/MM/yyyy.");
			}
		}

		while (horaInicio == null) {
			System.out.print("\nDigite a hora de início da reserva (HH:mm): ");
			String horaInicioString = inputScanner.nextLine();
			try {
				horaInicio = DateTimeUtils.timeFromString(horaInicioString);
			}
			catch (DateTimeParseException ex) {
				System.out.println("\nA hora informada é inválida. Utilize o formato informado HH/mm.");
			}
		}

		while (horaFim == null) {
			System.out.print("\nDigite a hora de término da reserva (HH:mm): ");
			String horaFimString = inputScanner.nextLine();
			try {
				horaFim = DateTimeUtils.timeFromString(horaFimString);
			}
			catch (DateTimeParseException ex) {
				System.out.println("\nA hora informada é inválida. Utilize o formato informado HH/mm.");
			}
		}
		
		Intervalo intervalo = new Intervalo(data, horaInicio, horaFim);
		
		if (!intervalo.isValid()) {
			System.out.print("\nOs dados informados são inválidos. ");
			System.out.println("Verifique se a hora de término não é menor que a de início e tente novamente.");
		}
		
		return intervalo;
	}

	private SalaReuniao solicitarSalaParaReserva(ArrayList<SalaReuniao> salasLivres) {
		exibirSalasLivres(salasLivres);
		
		Optional<SalaReuniao> salaSelecionada = null;
		
		while (salaSelecionada == null) {
			System.out.print("\nDigite o Nº da sala que deseja selecionar: ");
			String salaSelecionadaString = inputScanner.nextLine();		
			
			try {
				final Integer numeroSalaSelecionada = Integer.parseInt(salaSelecionadaString);
				
				salaSelecionada = salasLivres.stream().filter(s -> s.getNumero() == numeroSalaSelecionada).findFirst();
				
				if (salaSelecionada == null || !salaSelecionada.isPresent()) {
					System.out.println("\nO Nº digitado não corresponde a nenhuma sala da lista. Tente novamente.");
					salaSelecionada = null;
				}
			}
			catch (NumberFormatException ex) {
				System.out.println("\nNenhuma sala válida foi selecionada. Tente novamente.");
				continue;
			}
		}
		
		System.out.println("\nVocê selecionou a sala " + salaSelecionada.get().getNumero() + ".");

		return salaSelecionada.get();
	}

	private void exibirSalasLivres(ArrayList<SalaReuniao> salasLivres) {
		System.out.println("\nSalas livres para a data e intervalo de hora informados:");
		
		for (SalaReuniao salaLivre : salasLivres) {
			System.out.println(String.format("- Sala Nº %s (%s lugares);", salaLivre.getNumero(), salaLivre.getQtdLugares()));
		}
	}
	
	private String solicitarAssuntoDaReserva() {
		String assunto = null;
		
		while (assunto == null) {
			System.out.println("\nDigite o assunto da reserva: ");
			assunto = inputScanner.nextLine();

			if (assunto == null || assunto.isBlank()) {
				System.out.println("O assunto da reserva é obrigatório.");
				assunto = null;
			}
		}
		
		return assunto;
	}
	
	private void solicitarEquipamentos(Reserva reserva) {
		System.out.print("\nDeseja adicionar equipamentos à reserva? [S/N] ");
		
		while (true) {
			String resposta = inputScanner.nextLine();
			resposta = resposta.toLowerCase().trim();
			
			if (resposta.equals("n")) {
				break;
			}
			
			ArrayList<Equipamento> equipamentos;
			
			try {
				equipamentos = campusControlador.obterEquipamentos();
			}
			catch (Exception ex) {
				System.out.println("\n" + ex.getMessage());
				break;
			}
			
			Equipamento equipamentoSelecionado = solicitarEquipamento(equipamentos);
			
			if (equipamentoSelecionado != null) {
				try {
					campusControlador.adicionarEquipamento(reserva, equipamentoSelecionado);
					System.out.println("\nEquipamento adicionado com sucesso!");
				}
				catch (Exception ex) {
					System.out.println("\n" + ex.getMessage());
				}
			}
			
			System.out.print("\nDeseja adicionar mais equipamentos à reserva? [S/N] ");
		}
	}
	
	private Equipamento solicitarEquipamento(ArrayList<Equipamento> equipamentos) {
		exibirEquipamentos(equipamentos);
		
		Equipamento equipamentoSelecionado = null;
		
		while (equipamentoSelecionado == null) {
			System.out.print("\nDigite a opção selecionada: ");
			String opcaoSelecionada = inputScanner.nextLine();		
			
			try {
				Integer opcaoSelecionadaInteger = Integer.parseInt(opcaoSelecionada);
				if (opcaoSelecionadaInteger == 0) {
					return null;
				}
				
				if (opcaoSelecionadaInteger < 0 || opcaoSelecionadaInteger > equipamentos.size()) {
					System.out.println("\nA opção selecionada é inválida. Tente novamente.");
					continue;
				}
				
				equipamentoSelecionado = equipamentos.get(--opcaoSelecionadaInteger);
			}
			catch (NumberFormatException ex) {
				System.out.println("\nA opção selecionada é inválida. Tente novamente.");
				continue;
			}
		}
		
		return equipamentoSelecionado;
	}

	private void exibirEquipamentos(ArrayList<Equipamento> equipamentos) {
		System.out.println("\nEquipamentos existentes:");
		
		for (int i = 0; i < equipamentos.size(); i++) {
			Equipamento equipamento = equipamentos.get(i);
			System.out.println(String.format("[%s] %s;", i + 1, equipamento.getNome()));
		}
		
		System.out.println("[0] Cancelar adição de equipamento;");
	}
	
	private void executarConsultaDeOcupacao() {
		escreverSeparador();
		System.out.print("Consulta de Ocupação");
		escreverSeparador();
		
		PeriodoTipoEnum periodoTipoSelecionado = solicitarPeriodoTipo();
		ArrayList<ReservaAgrupamento> listaDeOcupacao;
		
		try {
			listaDeOcupacao = campusControlador.obterListaOcupacao(periodoTipoSelecionado);
		}
		catch (Exception ex) {
			System.out.println("\n" + ex.getMessage());
			return;
		}
		
		System.out.println();
		
		for (ReservaAgrupamento agrupamento : listaDeOcupacao) {
			escreverSeparador();
			System.out.print(agrupamento.toString());
		}
	}
	
	private PeriodoTipoEnum solicitarPeriodoTipo() {
		System.out.println("\nSelecione o tipo de período para a consulta:");
		
		PeriodoTipoEnum[] periodoTipoValues = PeriodoTipoEnum.values();
		
		for (int i = 0; i < periodoTipoValues.length; i++) {
			PeriodoTipoEnum periodoTipo = periodoTipoValues[i];
			System.out.println(String.format("[%s] %s;", i + 1, periodoTipo.toString()));
		}
		
		PeriodoTipoEnum tipoSelecionado = null;
		
		while (tipoSelecionado == null) {
			System.out.print("\nDigite a opção selecionada: ");
			String opcaoSelecionada = inputScanner.nextLine();		
			
			try {
				Integer opcaoSelecionadaInteger = Integer.parseInt(opcaoSelecionada);
				
				if (opcaoSelecionadaInteger < 1 || opcaoSelecionadaInteger > periodoTipoValues.length) {
					System.out.println("\nA opção selecionada é inválida. Tente novamente.");
					continue;
				}
				
				tipoSelecionado = periodoTipoValues[--opcaoSelecionadaInteger];
			}
			catch (NumberFormatException ex) {
				System.out.println("\nA opção selecionada é inválida. Tente novamente.");
			}
		}
		
		return tipoSelecionado;
	}
}
