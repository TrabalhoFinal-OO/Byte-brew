package cafeteria.modelo;

public class Comida extends Produto {
    private int tempoPreparoMinutos;
    private boolean isVegano;
    private boolean isSemGluten;

    public Comida(String codigo, String nome, double precoBase, int quantidadeEstoque, int tempoPreparoMinutos, boolean isVegano, boolean isSemGluten) {
        super(codigo, nome, precoBase, quantidadeEstoque);
        this.tempoPreparoMinutos = tempoPreparoMinutos;
        this.isVegano = isVegano;
        this.isSemGluten = isSemGluten;
    }

    public int getTempoPreparoMinutos() {
        return tempoPreparoMinutos;
    }

    public boolean getIsVegano() {
        return isVegano;
    }

    public boolean getIsSemGluten() {
        return isSemGluten;
    }
}
