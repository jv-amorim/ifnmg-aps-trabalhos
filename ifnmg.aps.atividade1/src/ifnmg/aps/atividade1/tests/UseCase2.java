package ifnmg.aps.atividade1.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ifnmg.aps.atividade1.CampusControlador;
import ifnmg.aps.atividade1.Exceptions;
import ifnmg.aps.atividade1.MockData;
import ifnmg.aps.atividade1.PeriodoTipoEnum;
import ifnmg.aps.atividade1.Reserva;
import ifnmg.aps.atividade1.ReservaAgrupamento;
import ifnmg.aps.atividade1.ReservaGerenciador;

class UseCase2 {
	
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
		try {
			ArrayList<ReservaAgrupamento> listaPorDia = campusControlador.obterListaOcupacao(PeriodoTipoEnum.Dia);
			
			assertNotNull(
				listaPorDia,
				"Deveria haver ao menos uma ReservaAgrupamento para a consulta por dia."
			);
			assertFalse(
				listaPorDia.isEmpty(),
				"Deveria haver ao menos uma ReservaAgrupamento para a consulta por dia."
			);
			assertFalse(
				listaPorDia.stream().anyMatch(i -> i.getReservas() == null || i.getReservas().size() == 0),
				"Não deveria haver uma instância de ReservaAgrupamento sem reservas atribuídas na consulta por dia."
			);
			assertFalse(
				listaPorDia.stream().anyMatch(i -> i.getPeriodoTipo() != PeriodoTipoEnum.Dia),
				"A consulta por dia não deveria retornar um ReservaAgrupamento que não é do tipo dia."
			);
			assertFalse(
				listaPorDia.stream().anyMatch(i -> i.getData() == null || i.getSemana() != null || i.getMes() != null),
				"Nenhuma ReservaAgrupamento deveria possuir a data nula ou a semana ou mês não nulos na consulta por dia."
			);
			
			ArrayList<ReservaAgrupamento> listaPorSemana = campusControlador.obterListaOcupacao(PeriodoTipoEnum.Semana);
			
			assertNotNull(
				listaPorSemana,
				"Deveria haver ao menos uma ReservaAgrupamento para a consulta por semana."
			);
			assertFalse(
				listaPorSemana.isEmpty(),
				"Deveria haver ao menos uma ReservaAgrupamento para a consulta por semana."
			);
			assertFalse(
				listaPorSemana.stream().anyMatch(i -> i.getReservas() == null || i.getReservas().size() == 0),
				"Não deveria haver uma instância de ReservaAgrupamento sem reservas atribuídas na consulta por semana."
			);
			assertFalse(
				listaPorSemana.stream().anyMatch(i -> i.getPeriodoTipo() != PeriodoTipoEnum.Semana),
				"A consulta por semana não deveria retornar um ReservaAgrupamento que não é do tipo semana."
			);
			assertFalse(
				listaPorSemana.stream().anyMatch(i -> i.getSemana() == null || i.getData() != null || i.getMes() != null),
				"Nenhuma ReservaAgrupamento deveria possuir a semana nula ou a data ou mês não nulos na consulta por semana."
			);
			
			ArrayList<ReservaAgrupamento> listaPorMes = campusControlador.obterListaOcupacao(PeriodoTipoEnum.Mes);
			
			assertNotNull(
				listaPorMes,
				"Deveria haver ao menos uma ReservaAgrupamento para a consulta por mês."
			);
			assertFalse(
				listaPorMes.isEmpty(),
				"Deveria haver ao menos uma ReservaAgrupamento para a consulta por mês."
			);
			assertFalse(
				listaPorMes.stream().anyMatch(i -> i.getReservas() == null || i.getReservas().size() == 0),
				"Não deveria haver uma instância de ReservaAgrupamento sem reservas atribuídas na consulta por mês."
			);
			assertFalse(
				listaPorMes.stream().anyMatch(i -> i.getPeriodoTipo() != PeriodoTipoEnum.Mes),
				"A consulta por mês não deveria retornar um ReservaAgrupamento que não é do tipo mês."
			);
			assertFalse(
				listaPorMes.stream().anyMatch(i -> i.getMes() == null || i.getData() != null || i.getSemana() != null),
				"Nenhuma ReservaAgrupamento deveria possuir o mês nulo ou a data ou semana não nulos na consulta por mês."
			);
		}
		catch (Exception ex) {
			fail("Não deveria ter ocorrido uma exceção.");
		}
	}

	@Test
	void cenario2A() {
		try {
			campusControlador.obterListaOcupacao(null);
			fail("Deveria ter ocorrido uma exceção para esta consulta.");
		}
		catch (Exception ex) {
			assertEquals(ex.getMessage(), Exceptions.PERIODO_INVALIDO.getMessage(), "Deveria ter ocorrido uma exceção de outro tipo.");
		}
	}

	@Test
	void cenario4A() {
		try {
			ReservaGerenciador.getInstance().initData(new ArrayList<Reserva>());
			
			campusControlador.obterListaOcupacao(PeriodoTipoEnum.Dia);
			fail("Deveria ter ocorrido uma exceção para esta consulta.");
		}
		catch (Exception ex) {
			assertEquals(ex.getMessage(), Exceptions.NENHUMA_RESERVA.getMessage(), "Deveria ter ocorrido uma exceção de outro tipo.");
		}
	}
	
}
