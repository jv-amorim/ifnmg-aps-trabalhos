package ifnmg.aps.atividade1;

public class Funcionario {
	
	private String nome;
	private String ramal;
	private FuncionarioCargoEnum cargo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public FuncionarioCargoEnum getCargo() {
		return cargo;
	}
	public void setCargo(FuncionarioCargoEnum cargo) {
		this.cargo = cargo;
	}
	
}
