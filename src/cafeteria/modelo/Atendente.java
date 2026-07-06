package cafeteria.modelo;

public class Atendente {
	String nome;
	String matricula;

	public Atendente(String nome, String matricula) {
	    this.nome = nome;
	    this.matricula = matricula;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String getMatricula(){
		return matricula;
	}
	
}
