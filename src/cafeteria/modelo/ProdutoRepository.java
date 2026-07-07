package cafeteria.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoRepository {

    private static Map<String, Produto> catalogoProdutos = new HashMap<>();
    private static Map<String, Integer> estoqueProdutos = new HashMap<>();

    // Salva um produto no catálogo e registra seu estoque
    public void salvar(Produto produto) {
        catalogoProdutos.put(produto.getCodigo(), produto);
        estoqueProdutos.put(produto.getCodigo(), produto.getQuantidadeEstoque());
    }

    // Busca um produto pelo código
    public Produto buscarPorCodigo(String codigo) {
        return catalogoProdutos.get(codigo);
    }

    // Retorna todos os produtos cadastrados
    public List<Produto> consultarTodos() {
        return new ArrayList<>(catalogoProdutos.values());
    }

    // Consulta a quantidade em estoque
    public int consultarEstoque(String codigo) {
        return estoqueProdutos.getOrDefault(codigo, 0);
    }

    // Atualiza o estoque no produto e no repositório
    public void atualizarEstoque(String codigo, int quantidade) {

        Produto produto = catalogoProdutos.get(codigo);

        if (produto != null) {
            produto.setQuantidadeEstoque(quantidade);
            estoqueProdutos.put(codigo, quantidade);
        }
    }

    // Verifica se o produto existe
    public boolean existeProduto(String codigo) {
        return catalogoProdutos.containsKey(codigo);
    }

    // Remove um produto do catálogo
    public boolean deletar(String codigo) {

        if (catalogoProdutos.containsKey(codigo)) {
            catalogoProdutos.remove(codigo);
            estoqueProdutos.remove(codigo);
            return true;
        }

        return false;
    }

    // Lista os produtos cadastrados
    public void listarProdutos() {

        for (Produto produto : catalogoProdutos.values()) {

            System.out.println(
                produto.getCodigo() + " - " +
                produto.getNome() +
                " | R$ " + produto.getPrecoBase() +
                " | Estoque: " + produto.getQuantidadeEstoque()
            );

        }
    }
}
