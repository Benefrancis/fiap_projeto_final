package ravim.fiap.projeto.banco.test;

public enum TipoConta {

	CORRENTE(1, "Conta Corrente"), POUPANCA(2, "Conta Poupança");

	private int id;

	private String nome;

	TipoConta(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}
}
