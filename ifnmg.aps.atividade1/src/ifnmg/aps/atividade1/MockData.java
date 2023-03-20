package ifnmg.aps.atividade1;

import java.util.ArrayList;

public class MockData {

	private static ArrayList<Campus> campi;
	private static ArrayList<Reserva> reservas;

	public static ArrayList<Campus> getCampi() {
		return campi;
	}

	public static ArrayList<Reserva> getReservas() {
		return reservas;
	}
	
	public static void instantiateMockData() {
		Campus campus = new Campus();
		campus.setNome("MOC");
		
		setCampusEndereco(campus);
		setCampusPredios(campus);
		
		ArrayList<Campus> campi = new ArrayList<Campus>();
		campi.add(campus);
		
		MockData.campi = campi;
		
		instanciateReservas();
	}

	private static void setCampusPredios(Campus campus) {
		// TODO - Finalizar mock.
		
		Predio predio3 = new Predio();
		predio3.setNome("3");

		SalaReuniao sala11 = new SalaReuniao();
		
		ArrayList<SalaReuniao> salasPredio3 = new ArrayList<SalaReuniao>();
		salasPredio3.add(sala11);
		
		predio3.setSalas(salasPredio3);
		
		ArrayList<Predio> predios = new ArrayList<Predio>();
		predios.add(predio3);
		
		campus.setPredios(predios);
	}

	private static void setCampusEndereco(Campus campus) {
		Endereco endereco = new Endereco();
		endereco.setCep("39404058");
		endereco.setLogradouro("Rua Dois");
		endereco.setNumero("300");
		endereco.setComplemento("");
		endereco.setBairro("Village do Lago I");
		endereco.setCidade("Montes Claros");
		endereco.setUf("MG");
		
		campus.setEndereco(endereco);
	}
	
	private static void instanciateReservas() {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		
		// TODO - Adicionar reservas.
		
		MockData.reservas = reservas;
	}
	
}
