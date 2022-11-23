package ravim.fiap.projeto.banco.model;

import ravim.fiap.projeto.banco.interfaces.*;
public abstract class Conta implements Padronavel{
	private double numero;
	private byte digito;
	private double agencia;
	private float saldo;
	private Cliente cliente;
	private int id;
		
	public Conta(){}

	public Conta(double numero, byte digito, double agencia, float saldo, Cliente cliente, int id) {
		super();
		this.numero = numero;
		this.digito = digito;
		this.agencia = agencia;
		this.saldo = saldo;
		this.cliente = cliente;
		this.id = id;
	}

	public double getNumero(double i) {
		return numero;
	}

	public void setNumero(double numero) {
		this.numero = numero;
	}

	public byte getDigito() {
		return digito;
	}

	public void setDigito(byte digito) {
		this.digito = digito;
	}

	public double getAgencia() {
		return agencia;
	}

	public void setAgencia(double agencia) {
		this.agencia = agencia;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Conta [numero=" + numero + ", digito=" + digito + ", agencia=" + agencia + ", saldo=" + saldo
				+ ", cliente=" + cliente + ", id=" + id + "]" ;
	}

}
