package cafeteria.modelo;

import java.util.Scanner;

import cafeteria.excecao.EstoqueInsuficienteException;
import cafeteria.excecao.PontosInsuficientesException;

public class Menu {

    private Cardapio cardapio;
    private Scanner teclado;
    private Pedido pedido;
    private Atendente atendenteAtual;
    private Cliente clienteAtual;

    public Menu(Cardapio cardapio, Atendente atendente, Cliente cliente) {
        this.cardapio = cardapio;
        this.teclado = new Scanner(System.in);

        this.atendenteAtual = atendente;
        this.clienteAtual = cliente;

        this.pedido = new Pedido(atendente, cliente);
    }

    public void iniciar() {

        int opcao;

        do {
            exibirOpcoes();
            opcao = teclado.nextInt();
            teclado.nextLine();

            processarOpcao(opcao);

        } while (opcao != 6);
    }

    private void exibirOpcoes() {

        System.out.println("\n============================== MENU ==============================");
        System.out.println("1. Ver Cardápio");
        System.out.println("2. Cadastrar/Fidelizar Cliente");
        System.out.println("3. Fazer Pedido");
        System.out.println("4. Ver Pedido Atual");
        System.out.println("5. Finalizar Compra");
        System.out.println("6. Sair");
        System.out.println("=================================================================");
        System.out.print("Escolha uma opção: ");
    }

    private void processarOpcao(int opcao) {

        switch (opcao) {

            case 1:
                cardapio.mostrarCardapio();
                break;

            case 2:
                System.out.println("Cadastro de cliente ainda não implementado.");
                break;

            case 3:
                fazerPedido();
                break;

            case 4:
                exibirItensDoPedido();
                break;

            case 5:
                finalizarCompra();
                break;

            case 6:
                System.out.println("\nObrigado por visitar a Byte & Brew!");
                break;

            default:
                System.out.println("Opção inválida!");
        }
    }

    private void fazerPedido() {

        System.out.print("\nDigite o código do produto: ");
        String codigo = teclado.nextLine();

        Produto produtoEncontrado = cardapio.buscarProduto(codigo);

        if (produtoEncontrado == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        try {

            pedido.adicionarItem(produtoEncontrado);

            System.out.println("✅ " + produtoEncontrado.getNome() + " adicionado ao pedido!");

        } catch (EstoqueInsuficienteException e) {

            System.out.println("❌ " + e.getMessage());

        }
    }

    private void exibirItensDoPedido() {

        if (pedido.getListaPedido().isEmpty()) {
            System.out.println("\nSeu pedido está vazio.");
            return;
        }

        System.out.println("\n============= PEDIDO =============");

        for (ItemPedido item : pedido.getListaPedido()) {
            System.out.printf("%s | Qtd: %d | Subtotal: R$ %.2f%n",
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getSubtotal());
        }

        System.out.printf("\nTOTAL: R$ %.2f%n", pedido.getTotal());
    }

    private void finalizarCompra() {

        if (pedido.getListaPedido().isEmpty()) {
            System.out.println("Seu carrinho está vazio.");
            return;
        }

        try {

            pedido.finalizarPedido();

            System.out.println("\n✅ Compra finalizada com sucesso!");
            System.out.printf("Valor total: R$ %.2f%n", pedido.getTotal());

        } catch (PontosInsuficientesException e) {

            System.out.println("❌ " + e.getMessage());

        }
    }
}
