package ravim.fiap.projeto.banco.model;

import ravim.fiap.projeto.banco.interfaces.*;
import ravim.fiap.projeto.banco.test.Agencia;
import ravim.fiap.projeto.banco.test.TipoConta;

public abstract class Conta implements Movimentavel {

	private long id;

	private Agencia agencia;

	private int numero;

	private byte digito;

	private double saldo;

	private TipoConta tipo;

	private Cliente cliente;

	public Conta(TipoConta tipo) {
		this.tipo = tipo;
	}

	public TipoConta getTipo() {
		return tipo;
	}

	public void setTipo(TipoConta tipo) {
		this.tipo = tipo;
	}

	public Conta(TipoConta tipo, long id, Agencia agencia, int numero, byte digito, Cliente cliente, double saldo) {
		super();
		this.tipo = tipo;
		this.id = id;
		this.agencia = agencia;
		this.numero = numero;
		this.digito = digito;
		this.saldo = saldo;
		this.cliente = cliente;

	}

	public Conta(TipoConta tipo, Agencia agencia, int numero, byte digito, Cliente cliente) {
		this.tipo = tipo;
		this.agencia = agencia;
		this.numero = numero;
		this.digito = digito;
		this.cliente = cliente;
	}

	public Conta(TipoConta tipo, long id, Agencia agencia, int numero, byte digito, Cliente cliente) {
		this.tipo = tipo;
		this.id = id;
		this.agencia = agencia;
		this.numero = numero;
		this.digito = digito;
		this.cliente = cliente;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public byte getDigito() {
		return digito;
	}

	public void setDigito(byte digito) {
		this.digito = digito;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Conta [id=").append(id).append(", agencia=").append(agencia).append(", numero=").append(numero)
				.append(", digito=").append(digito).append(", saldo=").append(saldo).append(", tipo=").append(tipo)
				.append(", cliente=").append(cliente).append("]");
		return builder.toString();
	}

 

}
