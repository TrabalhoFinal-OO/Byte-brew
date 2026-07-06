package cafeteria.modelo;

import java.util.Scanner;


public class Menu {
    private Cardapio cardapio;
    private Scanner teclado;

    public Menu(Cardapio cardapio) {
        this.cardapio = cardapio;
        this.teclado = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao = 0;

        do {
            exibirOpcoes();
            opcao = teclado.nextInt();
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
                
                break;
            case 3:
               
                break;
            case 4:
          
                break;
            case 5:
            
                break;
            default:
              
        }
    }
}