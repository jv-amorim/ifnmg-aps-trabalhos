package ifnmg.aps.atividade1.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ifnmg.aps.atividade1.CampusControlador;
import ifnmg.aps.atividade1.MockData;
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
		fail("Not yet implemented");
	}

	@Test
	void cenario2A() {
		fail("Not yet implemented");
	}

	@Test
	void cenario3A() {
		fail("Not yet implemented");
	}
	
}
