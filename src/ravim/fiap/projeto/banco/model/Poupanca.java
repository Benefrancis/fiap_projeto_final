package ravim.fiap.projeto.banco.model;

import ravim.fiap.projeto.banco.service.ContaService;
import ravim.fiap.projeto.banco.test.Agencia;
import ravim.fiap.projeto.banco.test.TipoConta;

public class Poupanca extends Conta {

	private float rendimento;

	public Poupanca() {
		super(TipoConta.POUPANCA);
	}

	
	
	
	public Poupanca(Agencia agencia, int numero, byte digito, Cliente cliente) {
		super(TipoConta.POUPANCA, agencia, numero, digito, cliente);
	}

	public Poupanca(long id, Agencia agencia, int numero, byte digito, Cliente cliente, double saldo) {
		super(TipoConta.POUPANCA, id, agencia, numero, digito, cliente, saldo);
	}

	public Poupanca(short numero, byte digito, Agencia agencia, float saldo, Cliente cliente, int id, float rendimento) {
		super(TipoConta.POUPANCA, id, agencia, numero, digito, cliente, saldo);
		this.rendimento = rendimento;
	}

	public float getRendimento() {
		return rendimento;
	}

	public void setRendimento(float rendimento) {
		this.rendimento = rendimento;
	}

	public boolean creditarRendimento(double valor) {
		return ContaService.creditarRendimento(this, valor);
	}

	@Override
	public boolean depositar(double valor) {
		return ContaService.depositar(this, valor);
	}

	@Override
	public boolean sacar(double valor) {
		return ContaService.sacar(this, valor);
	}

	public void consultar() {
		toString();
	}

	@Override
	public String toString() {
		return getNumero() + "-" + getDigito();
	}

}
