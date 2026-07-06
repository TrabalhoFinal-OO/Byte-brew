package cafeteria.servico;

import cafeteria.modelo.Pedido;
import cafeteria.modelo.ItemPedido;
import cafeteria.modelo.Bebida;

public class PromocaoEventoGeek implements Promocional {

    @Override
    public double calcularDesconto(Pedido pedido) {
        double totalBebidas = 0;

        for (ItemPedido item : pedido.getListaPedido()) {
            
            if (item.getProduto() instanceof Bebida) {
                totalBebidas += item.getSubtotal();
            }
        }
        
        return totalBebidas * 0.10;
    }
}