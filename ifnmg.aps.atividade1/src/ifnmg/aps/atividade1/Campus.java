package ifnmg.aps.atividade1;

import java.util.ArrayList;

public class Campus {
	
    private String nome;
    private Endereco endereco;
    private ArrayList<Predio> predios;
    private ArrayList<Equipamento> equipamentos;
    private ArrayList<Funcionario> funcionarios;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public ArrayList<Predio> getPredios() {
        return predios;
    }
    public void setPredios(ArrayList<Predio> predios) {
        this.predios = predios;
    }
    
    public ArrayList<Equipamento> getEquipamentos() {
        return equipamentos;
    }
    public void setEquipamentos(ArrayList<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
    
    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }
    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
}