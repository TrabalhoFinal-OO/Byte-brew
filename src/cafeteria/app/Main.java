package cafeteria.app;

import cafeteria.modelo.*;

public class Main {

	public static void main(String[] args) {

            Cardapio cardapio = new Cardapio();
            Atendente atendenteAtual = new Atendente("Caixa 01", "A01");
            Cliente clienteAtual = null;
            
                
            Menu menuDoSistema = new Menu(cardapio, atendenteAtual, clienteAtual);
               
            menuDoSistema.iniciar();

	}
}
