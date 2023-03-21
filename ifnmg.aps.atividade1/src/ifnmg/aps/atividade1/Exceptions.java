package ifnmg.aps.atividade1;

public enum Exceptions {
	INTERVALO_INVALIDO("Intervalo de consulta inválido."),
    NENHUMA_SALA("Nenhuma sala disponível para o intervalo de consulta informado."),
	PERIODO_INVALIDO("Tipo de período selecionado inválido."),
	NENHUMA_RESERVA("Nenhuma reserva encontrada.");
 
    private String message;
 
    Exceptions(String message) {
    	this.message = message;
	}
 
    public String getMessage() {
        return message;
    }
}
