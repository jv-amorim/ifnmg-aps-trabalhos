package ifnmg.aps.atividade1;

public enum Exceptions {
	
	INTERVALO_INVALIDO("Intervalo de consulta inválido."),
	PERIODO_INVALIDO("Tipo de período selecionado inválido."),
	ASSUNTO_INVALIDO("O assunto da reserva é obrigatório."),
    EQUIPAMENTO_INVALIDO("O equipamento informado é inválido."),
    
    NENHUMA_SALA("Nenhuma sala disponível para o intervalo de consulta informado."),
	NENHUMA_RESERVA("Nenhuma reserva encontrada."),
	NENHUM_EQUIPAMENTO("Nenhum equipamento encontrado."),
	
    SALA_INDISPONIVEL("A sala informada está indisponível para o intervalo de consulta informado."),
    EQUIPAMENTO_INDISPONIVEL("O equipamento informado está indisponível.");
 
    private String message;
 
    Exceptions(String message) {
    	this.message = message;
	}
 
    public String getMessage() {
        return message;
    }
    
}
