package ravim.fiap.projeto.banco.model;

public enum Agencia {
	
	SP(1, "São Paulo"), RJ(2, "Rio de Janeiro"), MG(3, "Minas Gerais");

	private String nome;
	private int id;

	Agencia(int id, String nome) {
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
