package cafeteria.modelo;

public abstract class Cliente {
	
	private String nome;
	private String cpf;
	private int saldoXP;
	
	public Cliente(String nome, String cpf) {
		this.nome =  nome;
		this.cpf = cpf;
		this.saldoXP = 0;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public int getSaldoXP(){
		return saldoXP;
	}
	
	public abstract void adicionarXP(double valor);
		
		
	protected void adicionarAoSaldoXP(int pontos) {
        if (pontos > 0) {
            saldoXP += pontos;
        }
    }

    protected void descontarXP(int pontos) {
        if (pontos > 0 && pontos <= saldoXP) {
            saldoXP -= pontos;
        }
    }
}
