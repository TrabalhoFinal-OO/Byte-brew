package cafeteria.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import cafeteria.excecao.EstoqueInsuficienteException;
import cafeteria.excecao.PontosInsuficientesException;

public class Pedido {

    private static int contadorNumPedido = 1;
    private static final int XP_POR_REAL = 10;

    private int numeroPedido;
    private LocalDate data;
    private Atendente atendente;
    private Cliente cliente;
    private List<ItemPedido> listaPedido;
    private boolean pagoComXP;
    private double descontoPromocional;

    public Pedido(Atendente atendente, Cliente cliente) {
        this.numeroPedido = contadorNumPedido++;
        this.data = LocalDate.now();
        this.atendente = atendente;
        this.cliente = cliente;
        this.listaPedido = new ArrayList<>();
    }

    // Sobrecarga
    public void adicionarItem(Produto produto) throws EstoqueInsuficienteException {
        adicionarItem(produto, 1);
    }

    public void adicionarItem(Produto produto, int quantidade) throws EstoqueInsuficienteException {

        if (produto.getQuantidadeEstoque() < quantidade) {
            throw new EstoqueInsuficienteException(
                "Não há estoque suficiente de " + produto.getNome() +
                ". Solicitado: " + quantidade +
                ", Disponível: " + produto.getQuantidadeEstoque()
            );
        }

        listaPedido.add(new ItemPedido(produto, quantidade));
    }

    public double getTotal() {
        double total = 0;

        for (ItemPedido item : listaPedido) {
            total += item.getSubtotal();
        }

        return total - descontoPromocional;
    }

    // Agora lança PontosInsuficientesException
    public void finalizarPedido() throws PontosInsuficientesException {

        // Pagamento com XP
        if (pagoComXP && cliente instanceof ClienteVIP) {
            ClienteVIP vip = (ClienteVIP) cliente;
            vip.pagarXP(getTotal());
        }

        // Baixa estoque
        for (ItemPedido item : listaPedido) {
            item.getProduto().baixarEstoque(item.getQuantidade());
        }

        // Adiciona XP somente se NÃO pagou com XP
        if (cliente != null && !pagoComXP) {
            cliente.adicionarXP(getTotal());
        }
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public LocalDate getData() {
        return data;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getListaPedido() {
        return listaPedido;
    }

    public void setDescontoPromocional(double descontoPromocional) {
        this.descontoPromocional = descontoPromocional;
    }

    public void setPagoComXP(boolean pagoComXP) {
        this.pagoComXP = pagoComXP;
    }

    public boolean isPagoComXP() {
        return pagoComXP;
    }

    public static int getXpPorReal() {
        return XP_POR_REAL;
    }

    @Override
    public String toString() {
        return "Pedido Nº " + numeroPedido +
               "\nData: " + data +
               "\nTotal: R$ " + String.format("%.2f", getTotal());
    }
}
