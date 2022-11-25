package ravim.fiap.projeto.banco.service;

import java.util.List;

import javax.swing.JOptionPane;

import ravim.fiap.projeto.banco.dao.ContaDAO;
import ravim.fiap.projeto.banco.model.Agencia;
import ravim.fiap.projeto.banco.model.Cliente;
import ravim.fiap.projeto.banco.model.Conta;
import ravim.fiap.projeto.banco.model.Corrente;
import ravim.fiap.projeto.banco.model.Poupanca;
import ravim.fiap.projeto.banco.model.TipoConta;

public class ContaService {

	public static boolean depositar(Conta conta, double valor) {

		if (valor <= 0) {
			JOptionPane.showMessageDialog(null, "Saldo Insuficiente");
			return false;
		}

		conta.setSaldo(conta.getSaldo() + valor);
		return true;
	}

	public static boolean sacar(Conta conta, double valor) {
		if (valor <= 0) {
			JOptionPane.showMessageDialog(null, "Valor para o saque é inválido");
			return false;
		}
			 

		if (conta instanceof Corrente) {
			if ((conta.getSaldo() + ((Corrente) conta).getLimite()) < valor) {
				JOptionPane.showMessageDialog(null, "Saldo Insuficiente");
				return false;
			}
		} else {
			if ((conta.getSaldo()) < valor)
				JOptionPane.showMessageDialog(null, "Saldo Insuficiente");
			return false;
		}

		conta.setSaldo(conta.getSaldo() - valor);
		return true;
	}

	/**
	 * Como só é possível majorar limite de conta corrente o método deve aceitar
	 * somente conta corrente
	 * 
	 * @param conta
	 * @param valor
	 * @return
	 */
	public static boolean aumentarLimite(Corrente conta, float valor) {

		if (valor <= 0) {
			JOptionPane.showMessageDialog(null, "valor inválido para aumento de limite");
			return false;
		}
		conta.setLimite(conta.getLimite() + valor);
		return true;
	}

	public static void debitarTaxa(Corrente conta, double taxa) {
		conta.setSaldo(conta.getSaldo() - taxa);
	}

	public static boolean creditarRendimento(Poupanca conta, double percentual) {
		// considerando que valor é um percentual
		if (conta.getSaldo() > 0 && percentual > 0) {
			conta.setSaldo(conta.getSaldo() * (1 + (percentual / 100)));
			return true;
		} else {
			return false;
		}
	}

	public static Conta consultar(int numero) {
		// TODO Auto-generated method stub
		return ContaDAO.findByNumero(numero);
	}

	public static Conta consultar(Agencia agencia, int numero) {
		// TODO Auto-generated method stub
		return ContaDAO.findByAgenciaAndNumero(agencia, numero);
	}

	/**
	 * Quando se salva uma conta a agente não sabe o número ainda nem o dígito.
	 * esses valores devem ser gerados pelo sistema.
	 * 
	 * @param cliente
	 * @param tipo
	 * @param agencia
	 * @param numero
	 * @param digito
	 * @return
	 */
	public static Conta salvar(Cliente cliente, TipoConta tipo, Agencia agencia) {

		// um mesmo cliente não pode ter mais que um tipo de conta por agencia
		Conta existe = findByClienteAndTipoAndAgencia(cliente, tipo, agencia);
		if (existe == null) {
			if (tipo == TipoConta.CORRENTE) {
				int numero = gerarNumero(agencia, tipo, cliente);
				byte digito = gerarDigito(String.valueOf(numero));
				return ContaDAO.salvar(new Corrente(agencia, numero, digito, cliente));
			} else if (tipo == TipoConta.POUPANCA) {
				int numero = gerarNumero(agencia, tipo, cliente);
				byte digito = gerarDigito(String.valueOf(numero));
				return ContaDAO.salvar(new Poupanca(agencia, numero, digito, cliente));
			} else {
				JOptionPane.showMessageDialog(null, "Tipo inválido de conta");
				return null;
			}
		}
		return existe;
	}

	public static Conta findByClienteAndTipoAndAgencia(Cliente cliente, TipoConta tipo, Agencia agencia) {

		return ContaDAO.findByClienteAndTipoContaAndAgencia(cliente, tipo, agencia);
	}

	/**
	 * Ou o cliente já tem um número de conta e sempre deverá devolver o mesmo
	 * numero de conta ou criamos um número de conta com a regra: id do tipo de
	 * conta concatenado com '00' e o tamanho da lista de contas no momento em que
	 * foi pedido para gerar o número.
	 * 
	 * @param agencia
	 * @param tipo
	 * @param cliente
	 * @return
	 */
	public static int gerarNumero(Agencia agencia, TipoConta tipo, Cliente cliente) {
		// @formatter:off
 		Conta conta = ContaDAO.findAll().stream()
				.filter(c -> c.getCliente() == cliente)
				.filter(c -> c.getTipo() == tipo)
				.filter(c-> c.getAgencia()==agencia)
				.findFirst()
				.orElse(null);
 		
 		if(conta!=null) return (int) conta.getNumero();
 		
 		String numero = tipo.getId() + "00"+ (ContaDAO.findAll().size()+1);
 		
 		// @formatter:on
		return Integer.parseInt(numero);
	}

	/**
	 * Retorna 10 - o último dígito do resultado da ( Soma dos dígitos do números
	 * ímpares multiplicado por 3, somados com a soma dos dígitos pares)
	 * 
	 * @param v
	 * @return
	 */
	private static byte gerarDigito(String v) {
		long somaPares = 0, somaImpares = 0;

		for (int i = 0; i < v.length(); i++) {

			int valor = Integer.parseInt(String.valueOf(v.charAt(i)));

			if (valor % 2 == 0) {
				somaPares += valor;
			} else {
				somaImpares += valor;
			}
		}

		String res = String.valueOf((somaPares + (somaImpares * 3)));

		byte digito = (byte) (10 - Integer.parseInt(String.valueOf(res.charAt(res.length() - 1))));

		return digito;
	}

	public static List<Conta> finAll() {
		return ContaDAO.findAll();
	}
}
