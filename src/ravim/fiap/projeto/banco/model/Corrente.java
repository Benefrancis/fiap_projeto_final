package ravim.fiap.projeto.banco.model;

import ravim.fiap.projeto.banco.dao.ContaDAO;

public class Corrente extends Conta {
	private float limite;
	private float taxa;
	
	public Corrente(){}

	public Corrente(double numero, byte digito, short agencia, float saldo, Cliente cliente, int id, float limite, float taxa) {
		super(numero, digito, agencia, saldo, cliente, id);
		this.limite = limite;
		this.taxa = taxa;
	}

	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}

	public float getTaxa() {
		return taxa;
	}

	public void setTaxa(float taxa) {
		this.taxa = taxa;
	}
	
	public float aumentarLimite(float valor) {
		for(Conta b : ContaDAO.conta1) {
			if(b instanceof Corrente) {
				((Corrente) b).setLimite(((Corrente) b).getLimite() * valor);
			}
		}
		return valor;
	}
	
	public void debitarTaxa() {
		for(Conta b : ContaDAO.conta1) {
			if(b instanceof Corrente) {
				b.setSaldo(b.getSaldo() - (b.getSaldo() * taxa));
			}
		}	
	}
	@Override
	public boolean depositar(float valor) {		
		for(Conta b : ContaDAO.conta1) {
			if(b instanceof Corrente) {
				b.setSaldo(b.getSaldo() + valor);
			}
		}
		return true;
	}	
	@Override
	public boolean sacar(float valor) { 
		for(Conta b : ContaDAO.conta1) {
			if(b instanceof Corrente) {
				b.setSaldo(b.getSaldo() - valor);
			}
		}
		return true;
	}
	public void consultar(double x) { //Sem usar beans
		for(Conta a : ContaDAO.conta1) {
			if(a instanceof Corrente) {
				if(a.getNumero(x) == x) {
					System.out.println(a.toString());
				}
			}			
		}
	}
	@Override
	public String toString() {
		return "Corrente [limite=" + limite + ", taxa=" + taxa + ", toString()=" + super.toString() + "]";
	}
	
}
