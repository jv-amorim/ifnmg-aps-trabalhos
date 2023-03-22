package ifnmg.aps.atividade1;

public enum Exceptions {
	INTERVALO_INVALIDO("Intervalo de consulta inválido."),
	PERIODO_INVALIDO("Tipo de período selecionado inválido."),
	ASSUNTO_INVALIDO("O assunto da reserva é obrigatório."),
    NENHUMA_SALA("Nenhuma sala disponível para o intervalo de consulta informado."),
	NENHUMA_RESERVA("Nenhuma reserva encontrada."),
    SALA_INDISPONIVEL("Sala informada disponível para o intervalo de consulta informado.");
 
    private String message;
 
    Exceptions(String message) {
    	this.message = message;
	}
 
    public String getMessage() {
        return message;
    }
}
