package ifnmg.aps.atividade1.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ifnmg.aps.atividade1.CampusControlador;
import ifnmg.aps.atividade1.Exceptions;
import ifnmg.aps.atividade1.Intervalo;
import ifnmg.aps.atividade1.MockData;
import ifnmg.aps.atividade1.Reserva;
import ifnmg.aps.atividade1.ReservaGerenciador;
import ifnmg.aps.atividade1.SalaReuniao;

class UseCase3 {
	
	private CampusControlador campusControlador;

	@BeforeEach
	void setUp() throws Exception {
		MockData.instantiateMockData();

		this.campusControlador = new CampusControlador();
		this.campusControlador.initData(MockData.getCampi());
		
		ReservaGerenciador.getInstance().initData(MockData.getReservas());
	}
	

	@Test
	void cenarioPrincipal() throws Exception {
		SalaReuniao salaSelecionada = obterSalaSelecionada();
		Intervalo intervalo = obterIntervalo();
		String assuntoReserva = "Sacro Império Romano-Germânico";
		
		try {
			Reserva reserva = campusControlador.reservarSala(salaSelecionada, intervalo, assuntoReserva);
			
			assertNotNull(reserva, "A reserva criada não deveria ser nula.");
			assertEquals(reserva.getSalaReuniao(), salaSelecionada, "A sala da reserva é diferente da informada.");
			assertEquals(reserva.getDataLocacao(), intervalo.getData(), "A data de locação da reserva é diferente da informada.");
			assertEquals(reserva.getHoraInicio(), intervalo.getHoraInicio(), "A hora de início da reserva é diferente da informada.");
			assertEquals(reserva.getHoraFim(), intervalo.getHoraFim(), "A hora de fim da reserva é diferente da informada.");
			assertEquals(reserva.getAssunto(), assuntoReserva, "O assunto da reserva é diferente do informado.");
			assertNotNull(reserva.getEquipamentos(), "A lista de equipamentos da reserva não deveria ser um objeto nulo.");
			
			assertFalse(
				salaSelecionada.verificarDisponibilidade(intervalo),
				"A sala não deveria mais estar disponível, ou seja, a reserva não foi salva com sucesso."
			);
		}
		catch (Exception ex) {
			fail("Não deveria ter ocorrido uma exceção para esta operação.");
		}
	}

	@Test
	void cenario3A() throws Exception {
		SalaReuniao salaSelecionada = obterSalaSelecionada();
		Intervalo intervalo = null;
		String assuntoReserva = "Sacro Império Romano-Germânico";
		
		try {
			campusControlador.reservarSala(salaSelecionada, intervalo, assuntoReserva);
			fail("Deveria ter ocorrido uma exceção para esta consulta.");
		}
		catch (Exception ex) {
			assertEquals(ex.getMessage(), Exceptions.INTERVALO_INVALIDO.getMessage(), "Deveria ter ocorrido uma exceção de outro tipo.");
		}
	}

	@Test
	void cenario3B() throws Exception {
		SalaReuniao salaSelecionada = obterSalaSelecionada();
		Intervalo intervalo = obterIntervalo();
		String assuntoReserva = null;
		
		try {
			campusControlador.reservarSala(salaSelecionada, intervalo, assuntoReserva);
			fail("Deveria ter ocorrido uma exceção para esta consulta.");
		}
		catch (Exception ex) {
			assertEquals(ex.getMessage(), Exceptions.ASSUNTO_INVALIDO.getMessage(), "Deveria ter ocorrido uma exceção de outro tipo.");
		}
	}

	@Test
	void cenario4A() throws Exception {
		SalaReuniao salaSelecionada = obterSalaSelecionada();
		
		LocalDate data = LocalDate.of(2023, Month.JANUARY, 5);
		LocalTime horaInicio = LocalTime.of(15, 0);
		LocalTime horaFim = LocalTime.of(16, 0);
		Intervalo intervalo = new Intervalo(data, horaInicio, horaFim);
		
		String assuntoReserva = "Sacro Império Romano-Germânico";
		
		try {
			campusControlador.reservarSala(salaSelecionada, intervalo, assuntoReserva);
			fail("Deveria ter ocorrido uma exceção para esta consulta.");
		}
		catch (Exception ex) {
			assertEquals(ex.getMessage(), Exceptions.SALA_INDISPONIVEL.getMessage(), "Deveria ter ocorrido uma exceção de outro tipo.");
		}
	}
	
	private Intervalo obterIntervalo() {
		LocalDate data = LocalDate.of(2023, Month.JANUARY, 1);
		LocalTime horaInicio = LocalTime.of(10, 0);
		LocalTime horaFim = LocalTime.of(11, 0);
		
		Intervalo intervalo = new Intervalo(data, horaInicio, horaFim);
		
		return intervalo;
	}
	
	// O caso de teste presume que o usuário já selecionou com sucesso uma sala livre com base no intervalo.
	private SalaReuniao obterSalaSelecionada() throws Exception {
		Intervalo intervalo = obterIntervalo();
		
		ArrayList<SalaReuniao> salasLivres = campusControlador.consultarSalasLivres(intervalo);
		SalaReuniao salaSelecionada = salasLivres.get(0);
		
		return salaSelecionada;
	}
}
