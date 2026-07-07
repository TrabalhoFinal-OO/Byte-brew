package cafeteria.modelo;

import java.util.ArrayList;
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
    
    private ArrayList<Cliente> listaClientesCadastrados;
    private ArrayList<Atendente> listaAtendentes;

    public Menu(Cardapio cardapio, Atendente atendente, Cliente cliente) {
        this.cardapio = cardapio;
        this.teclado = new Scanner(System.in);
        this.clienteAtual = cliente;
        
        this.listaClientesCadastrados = new ArrayList<>();
        this.listaAtendentes = new ArrayList<>();
        
        this.listaAtendentes.add(new Atendente("Carlos Silva (Caixa 01)", "A01"));
        this.listaAtendentes.add(new Atendente("Ana Souza (Caixa 02)", "A02"));

        if (cliente != null) {
            this.listaClientesCadastrados.add(cliente);
        }
        
        this.pedido = null;
    }

    public void iniciar() {
        int opcao = 0;

        do {
            exibirMenuPrincipal();
            try {
                opcao = Integer.parseInt(teclado.nextLine());
                processarOpcaoPrincipal(opcao);
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida! Por favor, digite um número.");
                opcao = 0;
            }
        } while (opcao != 4);
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n==================== SYSTEMA BYTE & BREW ====================");
        System.out.println("1. Ver Cardápio da Cafeteria");
        System.out.println("2. Cadastrar Cliente");
        System.out.println("3. INICIAR NOVO PEDIDO");
        System.out.println("4. Sair do Sistema");
        System.out.println("=============================================================");
        System.out.print("Escolha uma opção: ");
    }

    private void processarOpcaoPrincipal(int opcao) {
        switch (opcao) {
            case 1:
                cardapio.mostrarCardapio();
                break;
            case 2:
                cadastrarCliente();
                break;
            case 3:
                fluxoIniciarNovoPedido();
                break;
            case 4:
                System.out.println("\nEncerrando sistema. Obrigado!");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void fluxoIniciarNovoPedido() {
        System.out.println("\n--- Selecionar Atendente ---");
        System.out.println("Atendentes disponíveis:");
        for (int i = 0; i < listaAtendentes.size(); i++) {
            System.out.printf("%d - %s (Matrícula: %s)%n", (i + 1), listaAtendentes.get(i).getNome(), listaAtendentes.get(i).getMatricula());
        }
        System.out.print("Selecione o atendente: ");
        int escAtendente;
        try {
            escAtendente = Integer.parseInt(teclado.nextLine());
            this.atendenteAtual = listaAtendentes.get(escAtendente - 1);
        } catch (Exception e) {
            System.out.println("❌ Atendente inválido. Cancelando início do pedido.");
            return;
        }

        System.out.println("\n--- Identificar Cliente ---");
        System.out.println("1 - Selecionar Cliente já Cadastrado");
        System.out.println("2 - Cadastrar Novo Cliente Agora");
        System.out.println("3 - Não Identificar (Cliente de Balcão)");
        System.out.print("Escolha uma opção: ");
        
        int escCliente;
        try {
            escCliente = Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Opção inválida! Digite um número correspondente.");
            return;
        }

        if (escCliente == 1) {
            if (!selecionarClienteExistente()) {
                System.out.println("Voltando para o menu principal...");
                return;
            }
        } else if (escCliente == 2) {
            cadastrarCliente();
        } else if (escCliente == 3) {
            this.clienteAtual = null;
            System.out.println("Venda definida como: Cliente de Balcão.");
        } else {
            System.out.println("❌ Opção inválida! Pedido não foi iniciado.");
            return;
        }

        this.pedido = new Pedido(atendenteAtual, clienteAtual);
        System.out.println("\n🚀 Pedido #" + pedido.getNumeroPedido() + " aberto com sucesso!");
        
        menuPedidoAtivo();
    }

    private boolean selecionarClienteExistente() {
        if (listaClientesCadastrados.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado ainda.");
            return false;
        }
        System.out.println("\nClientes cadastrados:");
        for (int i = 0; i < listaClientesCadastrados.size(); i++) {
            Cliente c = listaClientesCadastrados.get(i);
            System.out.printf("%d - %s (CPF: %s)%n", (i + 1), c.getNome(), c.getCpf());
        }
        System.out.print("Selecione o número do cliente: ");
        try {
            int escolha = Integer.parseInt(teclado.nextLine());
            this.clienteAtual = listaClientesCadastrados.get(escolha - 1);
            return true;
        } catch (Exception e) {
            System.out.println("❌ Seleção inválida.");
            return false;
        }
    }

    private void cadastrarCliente() {
        System.out.print("\nNome do cliente: ");
        String nome = teclado.nextLine();

        System.out.print("CPF: ");
        String cpf = teclado.nextLine();

        System.out.println("\nTipo de cliente:\n1 - Standard\n2 - VIP");
        System.out.print("Escolha: ");

        int tipo;
        try {
            tipo = Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Tipo inválido.");
            return;
        }

        Cliente novoCliente;
        if (tipo == 1) {
            novoCliente = new ClienteStandard(nome, cpf);
        } else if (tipo == 2) {
            novoCliente = new ClienteVIP(nome, cpf);
        } else {
            System.out.println("Tipo inválido.");
            return;
        }

        listaClientesCadastrados.add(novoCliente);
        this.clienteAtual = novoCliente;

        System.out.println("\n✅ Cliente " + novoCliente.getNome() + " cadastrado com sucesso!");
    }

    private void menuPedidoAtivo() {
        int opcaoPedido = 0;
        do {
            System.out.println("\n------------------ PEDIDO EM ANDAMENTO ------------------");
            System.out.println("Atendente: " + pedido.getAtendente().getNome());
            System.out.println("Cliente: " + (pedido.getCliente() == null ? "Balcão" : pedido.getCliente().getNome()));
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Adicionar Item ao Pedido");
            System.out.println("2. Ver Itens do Pedido Atual");
            System.out.println("3. Finalizar Compra e Fechar Conta");
            System.out.println("4. Cancelar Pedido");
            System.out.println("------------------------------------------------------------");
            System.out.print("Escolha uma opção do pedido: ");

            try {
                opcaoPedido = Integer.parseInt(teclado.nextLine());
                switch (opcaoPedido) {
                    case 1:
                        fazerPedido();
                        break;
                    case 2:
                        exibirItensDoPedido();
                        break;
                    case 3:
                        finalizarCompra();
                        opcaoPedido = 3;
                        break;
                    case 4:
                        System.out.println("⚠️ Pedido cancelado e descartado.");
                        this.pedido = null;
                        opcaoPedido = 4;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Digite um número válido.");
                opcaoPedido = 0;
            }
        } while (opcaoPedido != 3 && opcaoPedido != 4);
    }

    private void fazerPedido() {
        System.out.print("\nDigite o código do produto: ");
        String codigo = teclado.nextLine();

        Produto produto = cardapio.buscarProduto(codigo);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Quantidade: ");
        int quantidade;
        try {
            quantidade = Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Quantidade inválida.");
            return;
        }

        try {
            pedido.adicionarItem(produto, quantidade);
            System.out.println("✅ " + produto.getNome() + " adicionado!");
        } catch (EstoqueInsuficienteException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void exibirItensDoPedido() {
        if (pedido.getListaPedido().isEmpty()) {
            System.out.println("\nO carrinho está vazio.");
            return;
        }

        System.out.println("\n============= ITENS DO PEDIDO =============");
        for (ItemPedido item : pedido.getListaPedido()) {
            System.out.printf("%s | Qtd: %d | Subtotal: R$ %.2f%n",
                    item.getProduto().getNome(), item.getQuantidade(), item.getSubtotal());
        }
        System.out.printf("\nTOTAL ATUAL: R$ %.2f%n", pedido.getTotal());
    }

    private void finalizarCompra() {
        if (pedido.getListaPedido().isEmpty()) {
            System.out.println("Seu carrinho está vazio. Não há o que finalizar.");
            return;
        }

        System.out.print("Hoje é Dia de Evento Geek? (S/N): ");
        String respGeek = teclado.nextLine();

        if (respGeek.equalsIgnoreCase("S")) {
            Promocional eventoGeek = new PromocaoEventoGeek();
            double desconto = eventoGeek.calcularDesconto(pedido);
            pedido.setDescontoPromocional(desconto); 
            System.out.println("🎉 Promoção Evento Geek aplicada com sucesso!");
        }

        if (clienteAtual instanceof ClienteVIP) {
            System.out.print("Deseja pagar com XP? (S/N): ");
            String resposta = teclado.nextLine();
            pedido.setPagoComXP(resposta.equalsIgnoreCase("S"));
        }

        try {
            pedido.finalizarPedido();

            System.out.println("\n✅ Compra finalizada com sucesso!");
            System.out.printf("Valor total cobrado: R$ %.2f%n", pedido.getTotal());

            this.pedido = null;

        } catch (PontosInsuficientesException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
}