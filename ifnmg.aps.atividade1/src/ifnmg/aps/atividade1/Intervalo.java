package ifnmg.aps.atividade1;

import java.time.LocalDate;
import java.time.LocalTime;

public class Intervalo {
	
	private LocalDate data;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	
	public LocalDate getData() {
		return data;
	}
	
	public LocalTime getHoraInicio() {
		return horaInicio;
	}
	
	public LocalTime getHoraFim() {
		return horaFim;
	}
	
	public Intervalo(LocalDate data, LocalTime horaInicio, LocalTime horaFim) {
		this.data = data;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}
	
	public boolean isValid() {
		return horaInicio.isBefore(horaFim);
	}
	
}
