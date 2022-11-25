package ravim.fiap.projeto.banco.test;

import java.util.List;

import javax.swing.JOptionPane;

import ravim.fiap.projeto.banco.model.Cliente;
import ravim.fiap.projeto.banco.model.Conta;
import ravim.fiap.projeto.banco.service.ClienteService;
import ravim.fiap.projeto.banco.service.ContaService;

public class App {

	public static void main(String[] args) {

		boolean continuar = true;

		do {

			Opcao opcao = getOpcoes();

			if(opcao==null) System.exit(0);
			System.out.println("Opcao selecionada: " + opcao);

			switch (opcao) {

			case CADASTRAR: {
				// Método para cadastrar conta

				String CPF = JOptionPane.showInputDialog("Informe o CPF do Cliente");

				Cliente cliente = ClienteService.findByCPF(CPF);

				if (cliente != null) {
					String nomeCliente = JOptionPane.showInputDialog("Informe o nome do cliente");
					cliente = ClienteService.salvar(new Cliente(nomeCliente, CPF));
				}

				TipoConta tipo = getTipoConta();

				Agencia agencia = getAgencia();

				Conta conta = ContaService.findByClienteAndTipoAndAgencia(cliente, tipo, agencia);

				if (conta == null) {

					conta = ContaService.salvar(cliente, tipo, agencia);
					System.out.println("Conta cadastrada: " + conta);
				} else {
					System.out.println("Já existia conta cadastrada para o cliente: " + conta);
				}

			}
				break;
			case CONSULTAR: {

				Conta conta = getConta();

				System.out.println(conta.getNumero() + "-" + conta.getDigito());

			}
				break;

			case DEPOSITAR: {

				Conta conta = getConta();
				double valor = Double.parseDouble(JOptionPane.showInputDialog("Valor"));
				ContaService.depositar(conta, valor);
				System.out.println(conta.getSaldo());

			}
				break;

			case SACAR: {

				Conta conta = getConta();
				double valor = Double.parseDouble(JOptionPane.showInputDialog("Valor"));
				ContaService.sacar(conta, valor);
				System.out.println(conta.getSaldo());

			}
				break;
			default:
				System.exit(0);

			}

			// @formatter:off
 			if (JOptionPane.showConfirmDialog(null, 
					"Deseja realizar outra operação?", 
					"Pergunta",
					JOptionPane.YES_NO_OPTION) == 1)
				continuar = false;
 			// @formatter:on

		} while (continuar);

	}

	private static Opcao getOpcoes() {
		// @formatter:off
 		return (Opcao) JOptionPane.showInputDialog(
									null, // componente pai. Como não temos será null
									"Selecione uma opção", 
									"Menu", 
									JOptionPane.QUESTION_MESSAGE, 
									null, // icone
									new Opcao[] { Opcao.CADASTRAR, Opcao.CONSULTAR, Opcao.DEPOSITAR, Opcao.SACAR }, // Número da opção
									Opcao.CADASTRAR);
 		// @formatter:on

	}

	private static TipoConta getTipoConta() {
		// @formatter:off
 		return (TipoConta) JOptionPane.showInputDialog(
										null, // componente pai. Como não temos será null
										"Selecione uma opção", 
										"Menu", 
										JOptionPane.QUESTION_MESSAGE,
										null, // icone
										new TipoConta[] { TipoConta.CORRENTE, TipoConta.POUPANCA }, // Número da opção
										TipoConta.CORRENTE);
 		// @formatter:on

	}

	private static Agencia getAgencia() {
		// @formatter:off
 		return (Agencia) JOptionPane.showInputDialog(
										null, 	
										"Selecione uma agência", 
										"Menu Agências Bancárias",
										JOptionPane.QUESTION_MESSAGE,
										null, // icone
										new Agencia[] { Agencia.SP, Agencia.RJ, Agencia.MG }, Agencia.SP);
 		// @formatter:on

	}

	private static Conta getConta() {
		List<Conta> contas = ContaService.finAll();
		if(contas.size()<=0) return null;
		
		
		// @formatter:off
 		return (Conta) JOptionPane.showInputDialog(
									null, 
									"Selecione uma das contas bancárias", 
									"Menu Contas",
									JOptionPane.QUESTION_MESSAGE, 
									null, // icone
									contas.toArray(), // Número da opção
									contas.get(0));
 		// @formatter:on

	}
}
