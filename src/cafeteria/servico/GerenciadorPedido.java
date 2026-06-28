package cafeteria.servico;

import cafeteria.excecao.EstoqueInsuficienteException;
import cafeteria.modelo.Pedido;

public class GerenciadorPedido {

	public void processarfechamento(Pedido pedido) throws EstoqueInsuficienteException {
		
		if(pedido != null) {
			pedido.finalizarPedido();
			System.out.println("O pedido foi processado e finalizado.");
		}
	}
}
