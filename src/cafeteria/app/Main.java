package cafeteria.app;

import cafeteria.modelo.*;
import cafeteria.excecao.*;
import cafeteria.servico.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	
            Cardapio cardapio = new Cardapio();
                
            Menu menuDoSistema = new Menu(cardapio);
               
            menuDoSistema.iniciar();

	}
}