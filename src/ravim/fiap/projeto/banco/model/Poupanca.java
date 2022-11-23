package ravim.fiap.projeto.banco.model;

import ravim.fiap.projeto.banco.dao.ContaDAO;

public class Poupanca extends Conta {
	
	private float rendimento;
	
	public Poupanca(){}

	public Poupanca(short numero, byte digito, short agencia, float saldo, Cliente cliente, int id, float rendimento) {
		super(numero, digito, agencia, saldo, cliente, id);
		this.rendimento = rendimento;
	}

	public float getRendimento() {
		return rendimento;
	}

	public void setRendimento(float rendimento) {
		this.rendimento = rendimento;
	}
	
	public boolean creditarRendimento(float x) {
		for(Conta b : ContaDAO.conta1) {
			if(b instanceof Poupanca) {
			b.setSaldo(b.getSaldo() * x + b.getSaldo());	
			}
		}
		return true;
	}
 	
	@Override
	public boolean depositar(float valor) {
		return true;
	}

	@Override
	public boolean sacar(float valor) {
		return true;
	}
	
	public void consultarP(double x) { //Sem usar beans
		for(Conta a : ContaDAO.conta1) {
			if(a instanceof Poupanca) {
				if(a.getNumero(x) == x) {
					System.out.println(a.toString());
				}
			}			
		}
	}

	@Override
	public String toString() {
		return "Poupanca [rendimento=" + rendimento + ", toString()=" + super.toString() + "]";
	}
}
