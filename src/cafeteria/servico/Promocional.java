package cafeteria.servico;

import cafeteria.modelo.Pedido;

public interface Promocional {
    double calcularDesconto(Pedido pedido);
}