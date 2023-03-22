package ifnmg.aps.atividade1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Reserva {
	
	private SalaReuniao salaReuniao;
	private LocalDate dataLocacao;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	private String assunto;
	private boolean ativa;
	private ArrayList<Equipamento> equipamentos;

	public SalaReuniao getSalaReuniao() {
		return salaReuniao;
	}
	public void setSalaReuniao(SalaReuniao salaReuniao) {
		this.salaReuniao = salaReuniao;
	}
	
	public LocalDate getDataLocacao() {
		return dataLocacao;
	}
	public void setDataLocacao(LocalDate dataLocacao) {
		this.dataLocacao = dataLocacao;
	}
	
	public LocalTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	public LocalTime getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}
	
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	
	public ArrayList<Equipamento> getEquipamentos() {
		return equipamentos;
	}
	public void setEquipamentos(ArrayList<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}
	
	public boolean getAtiva() {
		return ativa;
	}
	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
	
	public void adicionarEquipamento(Equipamento equipamento) {
		if (equipamentos == null) {
			equipamentos = new ArrayList<Equipamento>();
		}
		equipamentos.add(equipamento);
	}
}
