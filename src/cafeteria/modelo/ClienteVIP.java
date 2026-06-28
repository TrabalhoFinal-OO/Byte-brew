package cafeteria.modelo;

public class ClienteVIP extends Cliente {

	private static final int XP_REAL = 10;

	public ClienteVIP(String nome, String cpf) {
		super(nome, cpf);
	}

	@Override
	public void adicionarXP(double valor) {
		super.adicionarXP(valor * 2);
	}

	public boolean pagarXP(double valor) {

		if (valor <= 0) {
			return false;
		}

		int pontos = (int) Math.round(valor * XP_REAL);

		if (getSaldoXP() < pontos) {
			return false;
		}

		descontarXP(pontos);
		return true;
	}
}
