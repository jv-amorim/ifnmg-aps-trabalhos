package ifnmg.aps.atividade1.dtos;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

import ifnmg.aps.atividade1.entities.Equipamento;
import ifnmg.aps.atividade1.entities.Reserva;
import ifnmg.aps.atividade1.enums.PeriodoTipoEnum;
import ifnmg.aps.atividade1.utils.DateTimeUtils;
import ifnmg.aps.atividade1.utils.StringUtils;

public class ReservaAgrupamento {
	
	private PeriodoTipoEnum periodoTipo;
	private LocalDate data;
	private Integer semana;
	private Month mes;
	private ArrayList<Reserva> reservas;
	
	public PeriodoTipoEnum getPeriodoTipo() {
		return periodoTipo;
	}
	public void setPeriodoTipo(PeriodoTipoEnum periodoTipo) {
		this.periodoTipo = periodoTipo;
	}
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public Integer getSemana() {
		return semana;
	}
	public void setSemana(Integer semana) {
		this.semana = semana;
	}
	
	public Month getMes() {
		return mes;
	}
	public void setMes(Month mes) {
		this.mes = mes;
	}

	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	@Override
    public String toString() {
		if (periodoTipo == null) {
			return "Tipo de período nulo. Nenhuma reserva a ser listada.";
		}

		String result = "";
		
		switch (periodoTipo) {
			case Dia:
				result += String.format("Dia %s", DateTimeUtils.dateToString(data));
				break;
			case Mes:
				String mesTraduzido = DateTimeUtils.mesToString(mes);
				result += String.format("Mês %s", StringUtils.capitalize(mesTraduzido));
				break;
			case Semana:
				result += String.format("Semana %s", semana);
				break;
		}
		
		result += "\n\n";
		
		if (reservas.size() == 0) {
			result += "Nenhuma reserva encontrada.";
			return result;
		}
		
		for (int i = 0; i < reservas.size(); i++) {
			Reserva reserva = reservas.get(i);
			String horaInicio = DateTimeUtils.timeToString(reserva.getHoraInicio());
			String horaFim = DateTimeUtils.timeToString(reserva.getHoraFim());
			
			result += String.format("  - Reserva %s\n", i + 1);
			result += String.format("    Assunto: %s\n", reserva.getAssunto());
			result += String.format("    Horário: %s - %s\n", horaInicio, horaFim);
			result += String.format("    Ativa: %s\n", reserva.getAtiva() ? "Sim" : "Não");
			result += String.format("    Sala: Nº %s (%s lugares)\n", reserva.getSalaReuniao().getNumero(), reserva.getSalaReuniao().getQtdLugares());
			
			ArrayList<Equipamento> equipamentos = reserva.getEquipamentos();
			
			if (equipamentos != null && equipamentos.size() > 0) {
				result += "    Equipamentos:\n";
				
				for (Equipamento equipamento : reserva.getEquipamentos()) {
					result += String.format("      * %s\n", equipamento.getNome());
				}
			}
			
			result += "\n";
		}
		
		return result;
    }
}
