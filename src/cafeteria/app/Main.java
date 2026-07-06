package cafeteria.app;

import cafeteria.modelo.*;
import cafeteria.excecao.*;
import cafeteria.servico.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		//defininco o cardapio
		// ================= COMIDAS =================
    
		Comida beignets_tiana = new Comida("C01", "Beignets da Tiana", 10.00, 10, 10, false, false);
		Comida beignets_tiana_veg = new Comida("C02", "Beignets da Tiana Veganos", 10.00, 10, 10, true, true);
		Comida bolo_kiki = new Comida("C03", "Bolo de Chocolate da Kiki", 15.00, 10, 15, false, false);
		Comida bolo_kiki_veg = new Comida("C04", "Bolo de Chocolate da Kiki Vegano", 15.00, 10, 15, true, true);
		Comida paezinhos_totoro = new Comida("C05", "Pãezinhos do Totoro", 12.00, 10, 20, true, false);
		Comida donut_homer = new Comida("C06", "Donut Rosa do Homer", 9.50, 10, 15, false, false);
		Comida cookie_homem_aranha = new Comida("C07", "Cookie do Homem-Aranha", 8.50, 15, 20, false, false);
		Comida bolo_matilda = new Comida("C08", "Bolo de Chocolate da Matilda", 16.00, 10, 20, false, false);
		Comida cinnamon_roll_mickey = new Comida("C09", "Cinnamon Roll do Mickey", 12.00, 12, 15, false, false);
		Comida waffles_onze = new Comida("C10", "Waffles da Onze", 14.00, 12, 10, false, false);
	
		// ================= BEBIDAS =================

		Bebida cerveja_amanteigada_P = new Bebida("B01", "Cerveja Amanteigada Pequena", 8.00, 10, "P", 200.0);
		Bebida cerveja_amanteigada_M = new Bebida("B02", "Cerveja Amanteigada Média", 10.00, 10, "M", 300.0);
		Bebida cerveja_amanteigada_G = new Bebida("B03", "Cerveja Amanteigada Grande", 12.00, 10, "G", 400.0);
		Bebida chocolate_quente_ned_P = new Bebida("B04", "Chocolate Quente do Ned Pequeno", 7.00, 10, "P", 200.0);
		Bebida chocolate_quente_ned_M = new Bebida("B05", "Chocolate Quente do Ned Médio", 9.00, 10, "M", 300.0);
		Bebida chocolate_quente_ned_G = new Bebida("B06", "Chocolate Quente do Ned Grande", 11.00, 10, "G", 400.0);
	    Bebida cafe_central_perk_P = new Bebida("B07", "Café Central Perk Pequeno", 6.50, 10, "P", 200.0);
		Bebida cafe_central_perk_M = new Bebida("B08", "Café Central Perk Médio", 8.50, 10, "M", 300.0);
		Bebida cafe_central_perk_G = new Bebida("B09", "Café Central Perk Grande", 10.50, 10, "G", 400.0);
		Bebida cha_desaniversario_P = new Bebida("B10", "Chá de Desaniversário Pequeno", 7.00, 10, "P", 200.0);
		Bebida cha_desaniversario_M = new Bebida("B11", "Chá de Desaniversário Médio", 9.00, 10, "M", 300.0);
		Bebida cha_desaniversario_G = new Bebida("B12", "Chá de Desaniversário Grande", 11.00, 10, "G", 400.0);
		Bebida latte_totoro_P = new Bebida("B13", "Latte de Baunilha do Totoro Pequeno", 9.00, 10, "P", 200.0);
		Bebida latte_totoro_M = new Bebida("B14", "Latte de Baunilha do Totoro Médio", 11.00, 10, "M", 300.0);
		Bebida latte_totoro_G = new Bebida("B15", "Latte de Baunilha do Totoro Grande", 13.00, 10, "G", 400.0);
        
		int opcao;
		
		do {
		System.out.println("================================================");
		System.out.println("               ☕ BYTE & BREW ☕");
		System.out.println("           Cafeteria Geek Temática");
		System.out.println("================================================");
		System.out.println();
		
		System.out.println("=====================MENU========================");
		System.out.println(" 1.  Ver Cardápio");
		System.out.println(" 2.  Fazer Pedido");
		System.out.println(" 3.  Ver Carrinho");
		System.out.println(" 4.  Finalizar Compra");
		System.out.println(" 5.  Sair");
		System.out.println("================================================");
		System.out.print("Escolha uma opção: ");
		 opcao = teclado.nextInt();
	
		switch(opcao){
		case 1:
		System.out.println("\n=================== CARDÁPIO ===================");

		System.out.println("\nCOMIDAS");
		System.out.println("C01 - Beignets da Tiana ............. R$ 10,00");
		System.out.println("C02 - Beignets da Tiana Veganos ..... R$ 10,00");
		System.out.println("C03 - Bolo de Chocolate da Kiki ..... R$ 15,00");
		System.out.println("C04 - Bolo de Chocolate da Kiki Veg.  R$ 15,00");
		System.out.println("C05 - Pãezinhos do Totoro ........... R$ 12,00");
		System.out.println("C06 - Donut Rosa do Homer ........... R$ 9,50");
		System.out.println("C07 - Cookie do Homem-Aranha ........ R$ 8,50");
		System.out.println("C08 - Bolo de Chocolate da Matilda .. R$ 16,00");
		System.out.println("C09 - Cinnamon Roll do Mickey ....... R$ 12,00");
		System.out.println("C10 - Waffles da Onze ............... R$ 14,00");

		System.out.println("\nBEBIDAS");
		System.out.println("B01 - Cerveja Amanteigada P ......... R$ 8,00");
		System.out.println("B02 - Cerveja Amanteigada M ......... R$10,00");
		System.out.println("B03 - Cerveja Amanteigada G ......... R$12,00");
		System.out.println("B04 - Chocolate Quente do Ned P ..... R$ 7,00");
		System.out.println("B05 - Chocolate Quente do Ned M ..... R$ 9,00");
		System.out.println("B06 - Chocolate Quente do Ned G ..... R$11,00");
		System.out.println("B07 - Café Central Perk P ........... R$ 6,50");
		System.out.println("B08 - Café Central Perk M ........... R$ 8,50");
		System.out.println("B09 - Café Central Perk G ........... R$10,50");
		System.out.println("B10 - Chá de Desaniversário P ....... R$ 7,00");
		System.out.println("B11 - Chá de Desaniversário M ....... R$ 9,00");
		System.out.println("B12 - Chá de Desaniversário G ....... R$11,00");
		System.out.println("B13 - Latte do Totoro P ............. R$ 9,00");
		System.out.println("B14 - Latte do Totoro M ............. R$11,00");
		System.out.println("B15 - Latte do Totoro G ............. R$13,00");

		System.out.println("\"================================================");
		break;
		
		case 2:
		break;	
		
		case 3:
		break;
		
		case 4:
		break;
		}
		
		}while(opcao != 5);
	
		

	}
}
