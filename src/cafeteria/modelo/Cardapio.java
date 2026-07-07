package cafeteria.modelo;

import java.util.ArrayList;
import java.util.List;

public class Cardapio {
    private List<Produto> itens;
    public Cardapio() {
        this.itens = new ArrayList<>();
        inicializarCardapio();
    }

    private void inicializarCardapio() {
            // ================= COMIDAS =================
            itens.add(new Comida("C01", "Beignets da Tiana", 10.00, 10, 10, false, false));
            itens.add(new Comida("C02", "Beignets da Tiana Veganos", 10.00, 10, 10, true, true));
            itens.add(new Comida("C03", "Bolo de Chocolate da Kiki", 15.00, 10, 15, false, false));
            itens.add(new Comida("C04", "Bolo de Chocolate da Kiki Vegano", 15.00, 10, 15, true, true));
            itens.add(new Comida("C05", "Pãezinhos do Totoro", 12.00, 10, 20, true, false));
            itens.add(new Comida("C06", "Donut Rosa do Homer", 9.50, 10, 15, false, false));
            itens.add(new Comida("C07", "Cookie do Homem-Aranha", 8.50, 15, 20, false, false));
            itens.add(new Comida("C08", "Bolo de Chocolate da Matilda", 16.00, 10, 20, false, false));
            itens.add(new Comida("C09", "Cinnamon Roll do Mickey", 12.00, 12, 15, false, false));
            itens.add(new Comida("C10", "Waffles da Onze", 14.00, 12, 10, false, false));
            
            // ================= BEBIDAS =================
            itens.add(new Bebida("B01", "Cerveja Amanteigada Pequena", 8.00, 10, "P", 200.0));
            itens.add(new Bebida("B02", "Cerveja Amanteigada Média", 10.00, 10, "M", 300.0));
            itens.add(new Bebida("B03", "Cerveja Amanteigada Grande", 12.00, 10, "G", 400.0));
            itens.add(new Bebida("B04", "Chocolate Quente do Ned Pequeno", 7.00, 10, "P", 200.0));
            itens.add(new Bebida("B05", "Chocolate Quente do Ned Médio", 9.00, 10, "M", 300.0));
            itens.add(new Bebida("B06", "Chocolate Quente do Ned Grande", 11.00, 10, "G", 400.0));
            itens.add(new Bebida("B07", "Café Central Perk Pequeno", 6.50, 10, "P", 200.0));
            itens.add(new Bebida("B08", "Café Central Perk Médio", 8.50, 10, "M", 300.0));
            itens.add(new Bebida("B09", "Café Central Perk Grande", 10.50, 10, "G", 400.0));
            itens.add(new Bebida("B10", "Chá de Desaniversário Pequeno", 7.00, 10, "P", 200.0));
            itens.add(new Bebida("B11", "Chá de Desaniversário Médio", 9.00, 10, "M", 300.0));
            itens.add(new Bebida("B12", "Chá de Desaniversário Grande", 11.00, 10, "G", 400.0));
            itens.add(new Bebida("B13", "Latte de Baunilha do Totoro Pequeno", 9.00, 10, "P", 200.0));
            itens.add(new Bebida("B14", "Latte de Baunilha do Totoro Médio", 11.00, 10, "M", 300.0));
            itens.add(new Bebida("B15", "Latte de Baunilha do Totoro Grande", 13.00, 10, "G", 400.0));
        
    }

    
    public void mostrarCardapio() {
        System.out.println("\n=================== CARDÁPIO ===================");
        
        System.out.println("\n--- COMIDAS ---");
        for (Produto produto : itens) {
            if (produto instanceof Comida) {
                System.out.printf("%s - %-35s R$ %.2f%n", 
                    produto.getCodigo(), produto.getNome(), produto.getPrecoBase());
            }
        }

        System.out.println("\n--- BEBIDAS ---");
        for (Produto produto : itens) {
            if (produto instanceof Bebida) {
                System.out.printf("%s - %-35s R$ %.2f%n", 
                    produto.getCodigo(), produto.getNome(), produto.getPrecoBase());
            }
        }
        
        System.out.println("================================================");
    }
    
    public Produto buscarProduto(String codigo) {
        for (Produto produto : itens) {
           
            if (produto.getCodigo().equalsIgnoreCase(codigo)) {
                return produto; 
            }
        }
        return null;     }
}
