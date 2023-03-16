package ifnmg.aps.atividade1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		
		Reserva reserva1 = new Reserva();
		reserva1.setId(0);
		reserva1.setDataLocacao(LocalDate.of(2023, Month.JANUARY, 01));
		reserva1.setHoraInicio(LocalTime.of(8, 0));
		reserva1.setHoraFim(LocalTime.of(13, 0));
		reservas.add(reserva1);
		
		Reserva reserva2 = new Reserva();
		reserva2.setId(0);
		reserva2.setDataLocacao(LocalDate.of(2023, Month.JANUARY, 01));
		reserva2.setHoraInicio(LocalTime.of(9, 0));
		reserva2.setHoraFim(LocalTime.of(11, 0));
		reservas.add(reserva2);
		
		Reserva reserva3 = new Reserva();
		reserva3.setId(0);
		reserva3.setDataLocacao(LocalDate.of(2023, Month.JANUARY, 01));
		reserva3.setHoraInicio(LocalTime.of(11, 0));
		reserva3.setHoraFim(LocalTime.of(11, 30));
		reservas.add(reserva3);
		
		Reserva reserva4 = new Reserva();
		reserva4.setId(0);
		reserva4.setDataLocacao(LocalDate.of(2023, Month.JANUARY, 01));
		reserva4.setHoraInicio(LocalTime.of(11, 30));
		reserva4.setHoraFim(LocalTime.of(13, 0));
		reservas.add(reserva4);
		
		SalaReuniao sala = new SalaReuniao();
		sala.setReservas(reservas);
		
		Intervalo intervalo = new Intervalo(LocalDate.of(2023, Month.JANUARY, 01), LocalTime.of(10, 0), LocalTime.of(12, 0));
		
		boolean result = sala.verificarDisponibilidade(intervalo);
		System.out.println(result);
	}

}
