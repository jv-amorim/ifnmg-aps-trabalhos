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
		if (!intervalo.isValid()) {
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
		switch (periodoTipo) {
			case Dia:
				return reservaGerenciador.listarPorDia();
			case Semana:
				return reservaGerenciador.listarPorSemana();
			case Mes:
				return reservaGerenciador.listarPorMes();
			default:
				throw new Exception(Exceptions.PERIODO_INVALIDO.getMessage());
		}
	}
	
}
