package cafeteria.modelo;

import cafeteria.excecao.PontosInsuficientesException;

public class ClienteVIP extends Cliente {

    private static final int XP_POR_REAL = 10;

    public ClienteVIP(String nome, String cpf) {
        super(nome, cpf);
    }

    @Override
    public void adicionarXP(double valor) {
        if (valor > 0) {
            adicionarAoSaldoXP((int) (valor * 2));
        }
    }

    public boolean pagarXP(double valor) throws PontosInsuficientesException {

        if (valor <= 0) {
            return false;
        }

        int pontosNecessarios = (int) Math.round(valor * XP_POR_REAL);

        if (getSaldoXP() < pontosNecessarios) {
            throw new PontosInsuficientesException(
                "XP insuficiente para realizar o pagamento."
            );
        }

        descontarXP(pontosNecessarios);
        return true;
    }
}