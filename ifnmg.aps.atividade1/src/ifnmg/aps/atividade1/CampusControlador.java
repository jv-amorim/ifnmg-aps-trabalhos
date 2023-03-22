package ifnmg.aps.atividade1;

import java.util.ArrayList;

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
	
}
