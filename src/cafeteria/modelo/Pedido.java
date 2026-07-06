package cafeteria.modelo;

import java.time.LocalDate;
import java.util.ArrayList;

import cafeteria.excecao.EstoqueInsuficienteException;

public class Pedido {
	// Atributos 
	private static int contadorNumPedido = 1;
	
	private int numeroPedido;
	private LocalDate data;
	private Atendente atendente;
	private Cliente cliente;
	private ArrayList<ItemPedido> listaPedido;
	private boolean pagoComXP;
	private double descontoPromocional;
	
	// Métodos:
	
	// Método Construtor
	public Pedido(Atendente atendente, Cliente cliente){
		this.atendente = atendente;
		this.cliente = cliente;
		this.listaPedido = new ArrayList<>();
		this.data = LocalDate.now();
		
		this.numeroPedido = contadorNumPedido;
		contadorNumPedido++;
	}
	
	public void adicionarItem(Produto p){
		adicionarItem(p, 1);
	}
	
	public void adicionarItem(Produto p, int quantidade) {
		ItemPedido item = new ItemPedido(p, quantidade);
		listaPedido.add(item);
	}
	
	public double getTotal() {
		double total = 0;
		for(ItemPedido item: listaPedido) {
			total += item.getSubtotal();
		}
		return total-descontoPromocional;		
	}
	
	public void finalizarPedido() throws EstoqueInsuficienteException {
	    for (ItemPedido item : listaPedido) {
	        Produto produtoDoItem = item.getProduto();
	        int quantidadeVendida = item.getQuantidade();
	        produtoDoItem.baixarEstoque(quantidadeVendida);
	    }
	    
	    if (cliente != null) {
	        double valorTotalDoPedido = this.getTotal();
	        cliente.adicionarXP(valorTotalDoPedido); 
	    }
	}
	
	// Getters e Setters:
	public int getNumeroPedido() {
		return numeroPedido;
	}
	
	public void setDescontoPromocional(double desconto) {
		descontoPromocional = desconto;
	}
	
	public void setPagoComXP(boolean pagoComXP) {
		this.pagoComXP = pagoComXP;
	}
	
	public boolean isPagoComXP() {
		return pagoComXP;
	}
	
	public ArrayList<ItemPedido> getListaPedido(){
		return listaPedido;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public Atendente getAtendente() {
		return atendente;
	}

	public LocalDate getData() {
		return data;
	}
}
