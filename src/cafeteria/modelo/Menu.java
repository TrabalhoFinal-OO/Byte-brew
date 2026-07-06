package cafeteria.modelo;

import java.util.Scanner;


public class Menu {
    private Cardapio cardapio;
    private Scanner teclado;
    private Carrinho carrinho;

    public Menu(Cardapio cardapio) {
        this.cardapio = cardapio;
        this.teclado = new Scanner(System.in);
        this.carrinho = new Carrinho();
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
        System.out.println(" 2. Fazer Pedido");
        System.out.println(" 3. Ver Carrinho");
        System.out.println(" 4. Finalizar Compra");
        System.out.println(" 5. Sair");
        System.out.println("================================================================");
        System.out.print("Escolha uma opção: ");
    }

    
    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
              
                cardapio.mostrarCardapio();
                break;
            case 2:
                fazerPedido();
                break;
            case 3:
               carrinho.exibirCarrinho();
                break;
            case 4:
                finalizarCompra();
                break;
            case 5:
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
                carrinho.adicionarItem(produtoEncontrado);
            } else {
                System.out.println("❌ Código inválido! Produto não encontrado.");
            }
        }

        private void finalizarCompra() {
            if (carrinho.getItens().isEmpty()) {
                System.out.println("❌ Seu carrinho está vazio. Adicione itens antes de finalizar.");
                return;
            }
            System.out.println("\n💰 Compra finalizada com sucesso!");
            carrinho.limpar();
        
    }
}