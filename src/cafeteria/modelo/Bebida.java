package cafeteria.modelo;

public class Bebida extends Produto {
	
	private String tamanho;
	private double quantidadeCafeinaMg;
	
	public Bebida(String codigo, String nome, double precoBase, int quantidadeEstoque, String tamanho, double quantidadeCafeinaMg) {
		super(codigo, nome, precoBase, quantidadeEstoque);
		this.tamanho = tamanho;
		this.quantidadeCafeinaMg = quantidadeCafeinaMg;
	}
	
	public String getTamanho() {
		return tamanho;
	}
	
	public double getQuantidadeCafeinaMg() {
		return quantidadeCafeinaMg;
	}
}
