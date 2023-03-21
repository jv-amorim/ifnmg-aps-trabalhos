package ifnmg.aps.atividade1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
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
		setCampusEquipamentos(campus);
		setCampusFuncionarios(campus);
		
		ArrayList<Campus> campi = new ArrayList<Campus>();
		campi.add(campus);
		
		MockData.campi = campi;
		
		instanciateReservas();
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

	private static void setCampusPredios(Campus campus) {
		ArrayList<Predio> predios = new ArrayList<Predio>();
		
		predios.add(instantiatePredio2());
		predios.add(instantiatePredio3());
		
		campus.setPredios(predios);
	}

	private static Predio instantiatePredio2() {
		Predio predio2 = new Predio();
		predio2.setNome("2");

		SalaReuniao sala1 = new SalaReuniao();
		sala1.setNumero(1);
		sala1.setQtdLugares(60);

		SalaReuniao sala2 = new SalaReuniao();
		sala2.setNumero(2);
		sala2.setQtdLugares(20);

		SalaReuniao sala3 = new SalaReuniao();
		sala3.setNumero(3);
		sala3.setQtdLugares(40);
		
		ArrayList<SalaReuniao> salasPredio2 = new ArrayList<SalaReuniao>();
		salasPredio2.add(sala1);
		salasPredio2.add(sala2);
		salasPredio2.add(sala3);
		
		predio2.setSalas(salasPredio2);
		
		return predio2;
	}
	
	private static Predio instantiatePredio3() {
		Predio predio3 = new Predio();
		predio3.setNome("3");

		SalaReuniao sala11 = new SalaReuniao();
		sala11.setNumero(11);
		sala11.setQtdLugares(40);

		SalaReuniao sala12 = new SalaReuniao();
		sala12.setNumero(12);
		sala12.setQtdLugares(40);

		SalaReuniao sala13 = new SalaReuniao();
		sala13.setNumero(13);
		sala13.setQtdLugares(40);
		
		ArrayList<SalaReuniao> salasPredio3 = new ArrayList<SalaReuniao>();
		salasPredio3.add(sala11);
		salasPredio3.add(sala12);
		salasPredio3.add(sala13);
		
		predio3.setSalas(salasPredio3);
		
		return predio3;
	}
	
	private static void setCampusEquipamentos(Campus campus) {
		Equipamento equipamento1 = new Equipamento();
		equipamento1.setNome("Projetor X");
		equipamento1.setPatrimonio(3999.99);

		Equipamento equipamento2 = new Equipamento();
		equipamento2.setNome("Projetor Y");
		equipamento2.setPatrimonio(4500.00);

		Equipamento equipamento3 = new Equipamento();
		equipamento3.setNome("Acelerador de partículas");
		equipamento3.setPatrimonio(10000000.00);

		Equipamento equipamento4 = new Equipamento();
		equipamento4.setNome("Portal interdimensional");
		equipamento4.setPatrimonio(66666666.66);

		Equipamento equipamento5 = new Equipamento();
		equipamento5.setNome("Cafeteira");
		equipamento5.setPatrimonio(350.00);
		
		ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
		equipamentos.add(equipamento1);
		equipamentos.add(equipamento2);
		equipamentos.add(equipamento3);
		equipamentos.add(equipamento4);
		equipamentos.add(equipamento5);
		
		campus.setEquipamentos(equipamentos);
	}
	
	private static void setCampusFuncionarios(Campus campus) {
		Funcionario funcionario1 = new Funcionario();
		funcionario1.setNome("Detonator da Silva");
		funcionario1.setRamal("111");
		funcionario1.setCargo(FuncionarioCargoEnum.Professor);

		Funcionario funcionario2 = new Funcionario();
		funcionario2.setNome("Metal Avenger dos Santos");
		funcionario2.setRamal("222");
		funcionario2.setCargo(FuncionarioCargoEnum.Professor);

		Funcionario funcionario3 = new Funcionario();
		funcionario3.setNome("Blondie Hammet de Souza");
		funcionario3.setRamal("333");
		funcionario3.setCargo(FuncionarioCargoEnum.Professor);
		
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(funcionario1);
		funcionarios.add(funcionario2);
		funcionarios.add(funcionario3);
		
		campus.setFuncionarios(funcionarios);
	}
	
	private static void instanciateReservas() {
		SalaReuniao sala11 = MockData.campi.get(0).getPredios().get(1).getSalas().get(0);
		SalaReuniao sala12 = MockData.campi.get(0).getPredios().get(1).getSalas().get(1);
		
		Reserva reserva1 = new Reserva();
		reserva1.setSalaReuniao(sala11);
		reserva1.setDataLocacao(LocalDate.of(2023, Month.JANUARY, 1));
		reserva1.setHoraInicio(LocalTime.of(8, 0));
		reserva1.setHoraFim(LocalTime.of(9, 0));
		reserva1.setAssunto("Colonização de Marte");
		reserva1.setAtiva(true);
		reserva1.setEquipamentos(new ArrayList<Equipamento>());
		
		Reserva reserva2 = new Reserva();
		reserva2.setSalaReuniao(sala11);
		reserva2.setDataLocacao(LocalDate.of(2023, Month.JANUARY, 1));
		reserva2.setHoraInicio(LocalTime.of(9, 0));
		reserva2.setHoraFim(LocalTime.of(11, 0));
		reserva2.setAssunto("Hábitos alimentares dos ornitorrincos");
		reserva2.setAtiva(true);
		reserva2.setEquipamentos(new ArrayList<Equipamento>());
		
		Reserva reserva3 = new Reserva();
		reserva3.setSalaReuniao(sala12);
		reserva3.setDataLocacao(LocalDate.of(2023, Month.JANUARY, 2));
		reserva3.setHoraInicio(LocalTime.of(11, 0));
		reserva3.setHoraFim(LocalTime.of(11, 30));
		reserva3.setAssunto("2012: o mundo acabou e vivemos no pós-apocalipse?");
		reserva3.setAtiva(true);
		reserva3.setEquipamentos(new ArrayList<Equipamento>());
		
		Reserva reserva4 = new Reserva();
		reserva4.setSalaReuniao(sala12);
		reserva4.setDataLocacao(LocalDate.of(2023, Month.JANUARY, 2));
		reserva4.setHoraInicio(LocalTime.of(14, 0));
		reserva4.setHoraFim(LocalTime.of(16, 0));
		reserva4.setAssunto("Metallica");
		reserva4.setAtiva(true);
		reserva4.setEquipamentos(new ArrayList<Equipamento>());
		
		Equipamento projetorX = MockData.campi.get(0).getEquipamentos().get(0);
		reserva2.getEquipamentos().add(projetorX);
		
		Equipamento aceleradorDeParticulas = MockData.campi.get(0).getEquipamentos().get(2);
		reserva3.getEquipamentos().add(aceleradorDeParticulas);
		
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(reserva1);
		reservas.add(reserva2);
		reservas.add(reserva3);
		reservas.add(reserva4);
		
		MockData.reservas = reservas;
	}
	
}
