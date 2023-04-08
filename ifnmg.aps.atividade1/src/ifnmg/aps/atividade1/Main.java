package ifnmg.aps.atividade1;

import ifnmg.aps.atividade1.controllers.CampusControlador;
import ifnmg.aps.atividade1.controllers.ConsoleControlador;
import ifnmg.aps.atividade1.data.MockData;
import ifnmg.aps.atividade1.services.ReservaGerenciador;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO - Mover bloco abaixo para factory apropriado.
		MockData.instantiateMockData();
		CampusControlador campusControlador = new CampusControlador();
		campusControlador.initData(MockData.getCampi());
		ReservaGerenciador.getInstance().initData(MockData.getReservas());

		// TODO - Transformar em classe pai ou interface. Avaliar se o nome está apropriado.
		ConsoleControlador consoleControlador = new ConsoleControlador(campusControlador);
		consoleControlador.iniciar();
	}

}
