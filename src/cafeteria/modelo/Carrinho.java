package cafeteria.modelo;
import java.util.ArrayList;
import java.util.List;

	public class Carrinho {
	    private List<Produto> itens;

	    public Carrinho() {
	        this.itens = new ArrayList<>();
	    }
	    
	    public void adicionarItem(Produto produto) {
	        this.itens.add(produto);
	        System.out.println("✅ " + produto.getNome() + " adicionado ao carrinho!");
	    }
	    public void exibirCarrinho() {
	        if (itens.isEmpty()) {
	            System.out.println("\n🛒 Seu carrinho está vazio.");
	            return;
	        }

	        System.out.println("\n--- SEU CARRINHO ---");
	        double total = 0;
	        for (Produto p : itens) {
	            System.out.printf("- %-30s R$ %.2f%n", p.getNome(), p.getPrecoBase());
	            total += p.getPrecoBase();
	        }
	        System.out.printf("TOTAL: R$ %.2f%n", total);
	        System.out.println("--------------------");
	    }

	    public List<Produto> getItens() {
	        return itens;
	    }
	    
	    public void limpar() {
	        this.itens.clear();
	    }
	}

