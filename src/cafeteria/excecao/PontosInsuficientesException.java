package cafeteria.excecao;

public class PontosInsuficientesException extends Exception {

    private static final long serialVersionUID = 1L;

    public PontosInsuficientesException(String mensagem) {
        super(mensagem);
    }
}