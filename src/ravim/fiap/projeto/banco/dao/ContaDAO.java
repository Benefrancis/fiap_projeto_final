package ravim.fiap.projeto.banco.dao;

import java.util.List;
import java.util.Vector;

import ravim.fiap.projeto.banco.model.Cliente;
import ravim.fiap.projeto.banco.model.Conta;
import ravim.fiap.projeto.banco.test.Agencia;
import ravim.fiap.projeto.banco.test.TipoConta;

public class ContaDAO {

	private static List<Conta> contas = new Vector<>();

	public static boolean gravar(Conta c) {
		return contas.add(c);
	}

	public static List<Conta> findAll() {
		return contas;
	}

	public static Conta findByNumero(int numero) {

		// Buscando a primeira conta com o número informado... lembrar-se de antes de
		// cadastrar uma conta consultar para ver se já tem conta cadastrada com o mesmo
		// número

		// @formatter:off
 		return contas.stream()
				.filter(c -> c.getNumero() == numero)
				.findFirst()
				.orElse(null);
 		// @formatter:on

	}

	public static Conta findByAgenciaAndNumero(Agencia agencia, int numero) {
		// @formatter:off
 		return contas.stream()
				.filter(c -> c.getAgencia() == agencia)
				.filter(c -> c.getNumero() == numero)
				.findFirst()
				.orElse(null);
 		// @formatter:on

	}

	public static Conta findByClienteAndTipoContaAndAgencia(Cliente cliente, TipoConta tipo, Agencia agencia) {
		// @formatter:off
 		return contas.stream()
				.filter(c -> c.getCliente() == cliente)
				.filter(c -> c.getTipo() == tipo)
				.filter(c-> c.getAgencia()==agencia)
				.findFirst()
				.orElse(null);
 		// @formatter:on

	}

	public static Conta salvar(Conta conta) {
		if (conta.getId() == 0)
			conta.setId(contas.size() + 1);
		contas.add(conta);
		return conta;
	}



}
