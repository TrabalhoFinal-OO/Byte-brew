package cafeteria.modelo;

public class ClienteStandard extends Cliente {

    public ClienteStandard(String nome, String cpf) {
        super(nome, cpf);
    }

    @Override
    public void adicionarXP(double valor) {
        if (valor > 0) {
            adicionarAoSaldoXP((int) valor);
        }
    }
}