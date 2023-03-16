package ifnmg.aps.atividade1;

public class Funcionario {

	private int id;
	private String nome;
	private String ramal;
	private CargoEnum cargo;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
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

	public CargoEnum getCargo() {
		return cargo;
	}
	public void setCargo(CargoEnum cargo) {
		this.cargo = cargo;
	}

}
