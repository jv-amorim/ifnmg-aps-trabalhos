package ifnmg.aps.atividade1.dtos;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import ifnmg.aps.atividade1.entities.Reserva;
import ifnmg.aps.atividade1.enums.PeriodoTipoEnum;

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
	
}
