package ravim.fiap.projeto.banco.model;

import ravim.fiap.projeto.banco.service.ContaService;
import ravim.fiap.projeto.banco.test.Agencia;
import ravim.fiap.projeto.banco.test.TipoConta;

public class Corrente extends Conta {

	private double limite;

	private double taxa;

	public Corrente() {
		super(TipoConta.CORRENTE);
	}

	public Corrente(Agencia agencia, int numero, byte digito, Cliente cliente) {
		super(TipoConta.CORRENTE, agencia, numero, digito, cliente);
	}

	public Corrente(long id, Agencia agencia, int numero, byte digito, Cliente cliente, double saldo) {
		super(TipoConta.CORRENTE,id, agencia, numero, digito, cliente, saldo);
	}

	public Corrente(long id, Agencia agencia, int numero, byte digito, double saldo, Cliente cliente, double limite,
			double taxa) {
		super(TipoConta.CORRENTE,id, agencia, numero, digito, cliente, saldo);
		this.limite = limite;
		this.taxa = taxa;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public boolean aumentarLimite(float valor) {
		return ContaService.aumentarLimite(this, valor);
	}

	public void debitarTaxa() {
		ContaService.debitarTaxa(this, taxa);
	}

	@Override
	public boolean depositar(double valor) {
		return ContaService.depositar(this, valor);

	}

	@Override
	public boolean sacar(double valor) {
		return ContaService.sacar(this, valor);
	}

	@Override
	public String toString() {
		return getNumero() + "-" + getDigito();
	}

}
