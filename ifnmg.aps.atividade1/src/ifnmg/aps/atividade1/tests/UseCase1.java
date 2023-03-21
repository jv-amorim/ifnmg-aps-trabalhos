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
import ifnmg.aps.atividade1.ReservaGerenciador;
import ifnmg.aps.atividade1.SalaReuniao;

class UseCase1 {
	
	private CampusControlador campusControlador;

	@BeforeEach
	void setUp() throws Exception {
		MockData.instantiateMockData();

		this.campusControlador = new CampusControlador();
		this.campusControlador.initData(MockData.getCampi());
		
		ReservaGerenciador.getInstance().initData(MockData.getReservas());
	}

	@Test
	void cenarioPrincipal() {
		LocalDate data = LocalDate.of(2023, Month.JANUARY, 1);
		LocalTime horaInicio = LocalTime.of(10, 0);
		LocalTime horaFim = LocalTime.of(11, 0);
		Intervalo intervalo = new Intervalo(data, horaInicio, horaFim);
		
		try {
			ArrayList<SalaReuniao> salasLivres = campusControlador.consultarSalasLivres(intervalo);
			
			assertNotNull(salasLivres, "Deveria haver ao menos uma sala livre para esta consulta.");
			assertFalse(salasLivres.isEmpty(), "Deveria haver ao menos uma sala livre para esta consulta.");
			
			SalaReuniao salaSelecionada = salasLivres.get(0);
			
			assertTrue(salaSelecionada != null, "A sala selecionada não deveria ser nula.");
		}
		catch (Exception ex) {
			fail("Não deveria ter ocorrido uma exceção para esta consulta.");
		}
	}

	@Test
	void cenarioAlt2A() {
		LocalDate data = LocalDate.of(2023, Month.JANUARY, 1);
		LocalTime horaInicio = LocalTime.of(11, 0);
		LocalTime horaFim = LocalTime.of(10, 0);
		Intervalo intervalo = new Intervalo(data, horaInicio, horaFim);
		
		try {
			campusControlador.consultarSalasLivres(intervalo);
			fail("Deveria ter ocorrido uma exceção para esta consulta.");
		}
		catch (Exception ex) {
			assertEquals(ex.getMessage(), Exceptions.INTERVALO_INVALIDO.getMessage(), "Deveria ter ocorrido uma exceção de outro tipo.");
		}
	}

	@Test
	void cenarioAlt6A() {
		LocalDate data = LocalDate.of(2023, Month.JANUARY, 5);
		LocalTime horaInicio = LocalTime.of(15, 0);
		LocalTime horaFim = LocalTime.of(16, 0);
		Intervalo intervalo = new Intervalo(data, horaInicio, horaFim);
		
		try {
			campusControlador.consultarSalasLivres(intervalo);
			fail("Deveria ter ocorrido uma exceção para esta consulta.");
		}
		catch (Exception ex) {
			assertEquals(ex.getMessage(), Exceptions.NENHUMA_SALA.getMessage(), "Deveria ter ocorrido uma exceção de outro tipo.");
		}
	}
	
}
