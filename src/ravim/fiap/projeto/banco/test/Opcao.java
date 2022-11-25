package ravim.fiap.projeto.banco.test;

public enum Opcao {

	CADASTRAR(1, "Nova Conta"), CONSULTAR(2, "Consultar Conta"), DEPOSITAR(3, "Depositar"), SACAR(4, "Sacar");

	private String nome;
	private int id;

	Opcao(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {

		return nome;
	}
}
