package cafeteria.servico;

import cafeteria.excecao.PontosInsuficientesException;
import cafeteria.modelo.Pedido;

public class GerenciadorPedido {

    public void processarFechamento(Pedido pedido) throws PontosInsuficientesException {

        if (pedido != null) {
            pedido.finalizarPedido();
            System.out.println("O pedido foi processado e finalizado.");
        }
    }
}