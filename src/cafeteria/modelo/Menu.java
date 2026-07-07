package cafeteria.modelo;

import java.util.Scanner;
import cafeteria.servico.*;

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
            cadastrarCliente();
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

    private void cadastrarCliente() {

        System.out.print("\nNome do cliente: ");
        String nome = teclado.nextLine();

        System.out.print("CPF: ");
        String cpf = teclado.nextLine();

        System.out.println("\nTipo de cliente:");
        System.out.println("1 - Standard");
        System.out.println("2 - VIP");
        System.out.print("Escolha: ");

        int tipo = teclado.nextInt();
        teclado.nextLine();

        if (tipo == 1) {
            clienteAtual = new ClienteStandard(nome, cpf);
        } else if (tipo == 2) {
            clienteAtual = new ClienteVIP(nome, cpf);
        } else {
            System.out.println("Tipo inválido.");
            return;
        }

        pedido = new Pedido(atendenteAtual, clienteAtual);

        System.out.println("\nCliente cadastrado com sucesso!");
        System.out.println("Nome: " + clienteAtual.getNome());
        System.out.println("CPF: " + clienteAtual.getCpf());
    }

    private void fazerPedido() {

        if (clienteAtual == null) {
            System.out.println("Cadastre um cliente antes de fazer um pedido.");
            return;
        }

        System.out.print("\nDigite o código do produto: ");
        String codigo = teclado.nextLine();

        Produto produto = cardapio.buscarProduto(codigo);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Quantidade: ");
        int quantidade = teclado.nextInt();
        teclado.nextLine();

        try {

            pedido.adicionarItem(produto, quantidade);

            System.out.println("✅ " + produto.getNome() + " adicionado ao pedido!");

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
        System.out.println("Cliente: " + pedido.getCliente().getNome());

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

        System.out.print("Hoje é Dia de Evento Geek? (S/N): ");
        String respGeek = teclado.nextLine();

        if (respGeek.equalsIgnoreCase("S")) {
            
            Promocional eventoGeek = new PromocaoEventoGeek();
            double desconto = eventoGeek.calcularDesconto(pedido);
            
            pedido.setDescontoPromocional(desconto);
            System.out.println("🎉 Promoção Evento Geek aplicada com sucesso (10% de desconto nas bebidas)!");
        }

        if (clienteAtual instanceof ClienteVIP) {

            System.out.print("Deseja pagar com XP? (S/N): ");
            String resposta = teclado.nextLine();

            if (resposta.equalsIgnoreCase("S")) {
                pedido.setPagoComXP(true);
            } else {
                pedido.setPagoComXP(false);
            }
        }

        try {

            pedido.finalizarPedido();

            System.out.println("\n✅ Compra finalizada com sucesso!");
            System.out.printf("Valor total com descontos: R$ %.2f%n", pedido.getTotal());

            
            pedido = new Pedido(atendenteAtual, clienteAtual);

        } catch (PontosInsuficientesException e) {

            System.out.println("❌ " + e.getMessage());

        }
    }
}
