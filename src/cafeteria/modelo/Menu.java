package cafeteria.modelo;
import cafeteria.servico.*;
import cafeteria.excecao.*;

import java.util.Scanner;


public class Menu {
    private Cardapio cardapio;
    private Scanner teclado;
    private Pedido pedido;
    private ProdutoRepository produtoRepository;
    private Atendente atendenteAtual;
    private Cliente clienteAtual;

    public Menu(Cardapio cardapio, Atendente atendente, Cliente cliente) {
        this.cardapio = cardapio;
        this.teclado = new Scanner(System.in);
        this.pedido = new Pedido(atendente, cliente);
        this.produtoRepository = new ProdutoRepository();
        this.pedido = new Pedido(this.atendenteAtual, this.clienteAtual);
    }

    public void iniciar() {
        int opcao = 0;

        do {
            exibirOpcoes();
            opcao = teclado.nextInt();
            teclado.nextLine();
            processarOpcao(opcao);
        } while (opcao != 5); 
    }

    
    private void exibirOpcoes() {
        System.out.println("==============================MENU==============================");
        System.out.println(" 1. Ver Cardápio");
        System.out.println(" 2. Cadastrar/Fidelizar Cliente ");
        System.out.println(" 3. Fazer Pedido");
        System.out.println(" 4. Ver Pedido Atual");
        System.out.println(" 5. Finalizar Compra");
        System.out.println(" 6. Sair");
        System.out.println("================================================================");
        System.out.print("Escolha uma opção: ");
    }

    
    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cardapio.mostrarCardapio();
                break;
            case 2:
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
            	System.out.println("\nObrigado por visitar a cafeteria! Até logo!");
                break;
            default:
            	System.out.println("\nOpção inválida! Tente novamente.");
            	break;
        }
    }
        private void fazerPedido() {
            System.out.print("\nDigite o código do produto (ex: C01, B03): ");
            String codigo = teclado.nextLine();

            Produto produtoEncontrado = cardapio.buscarProduto(codigo);
            

            if (produtoEncontrado != null) {
            	int estoqueAtual = produtoRepository.consultarEstoque(codigo);
            	
            	if(estoqueAtual > 0) {
            		pedido.adicionarItem(produtoEncontrado);
            		System.out.println("✅ " + produtoEncontrado.getNome() + " adicionado ao pedido!");
            	}
            	else {
            		System.out.println("Desculpe, produto esgotado no estoque!");
            	}
              
            } else {
                System.out.println("Código inválido! Produto não encontrado.");
            }
        }
        private void exibirItensDoPedido() {
            if (pedido.getListaPedido().isEmpty()) {
                System.out.println("\n🛒 O seu pedido está vazio.");
                return;
            }
            System.out.println("\n--- ITENS DO PEDIDO ATUAL ---");
            for (ItemPedido item : pedido.getListaPedido()) {
                System.out.printf("- %s (Qtd: %d) - Subtotal: R$ %.2f%n", 
                    item.getProduto().getNome(), item.getQuantidade(), item.getSubtotal());
            }
            System.out.printf("Total: R$ %.2f%n", pedido.getTotal());
            System.out.println("-----------------------------");
        }

        private void finalizarCompra() {
        	if (pedido.getTotal() == 0.0) {
                System.out.println("❌ Seu carrinho está vazio. Adicione itens antes de finalizar.");
                return;
            }
            
            try {
                pedido.finalizarPedido();
                System.out.println("\n💰 Compra finalizada com sucesso!");
                System.out.printf("Total pago: R$ %.2f%n", pedido.getTotal());
            } catch (EstoqueInsuficienteException e) {
                System.out.println("❌ Erro ao finalizar: " + e.getMessage());
            }
        }
        
}