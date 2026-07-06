package cafeteria.modelo;

import java.util.HashMap;
import java.util.Map;

public class ProdutoRepository {

	private static Map<String, Integer> estoqueProdutos = new HashMap<>();
	private static Map<String, Produto> catalogoProdutos = new HashMap<>();
	
	public void salvar(Produto p) {
		catalogoProdutos.put(p.getCodigo(), p);
	}
	
	public Produto buscarPorCodigo(String codigo) {
		return catalogoProdutos.get(codigo);
	}
	
	public int consultarEstoque(String codigo) {
		return estoqueProdutos.getOrDefault(codigo, 0);
		
	}
	public void atualizarEstoque(String codigo, int quantidade) {
		if(estoqueProdutos.containsKey(codigo)) {
			estoqueProdutos.put(codigo, quantidade);
	}
}
	public boolean deletar(String codigo) {
		if(catalogoProdutos.containsKey(codigo)) {
			catalogoProdutos.remove(codigo);
			estoqueProdutos.remove(codigo);
			return true;
		}
		return false;
	}
}
