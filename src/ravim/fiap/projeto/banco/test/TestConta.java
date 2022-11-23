package ravim.fiap.projeto.banco.test;

import java.util.Scanner;
import ravim.fiap.projeto.banco.bo.ContaBO;
import ravim.fiap.projeto.banco.dao.ContaDAO;
import ravim.fiap.projeto.banco.excecoes.Excecoes;
import ravim.fiap.projeto.banco.model.Cliente;
import ravim.fiap.projeto.banco.model.Conta;
import ravim.fiap.projeto.banco.model.Corrente;
import ravim.fiap.projeto.banco.model.Poupanca;

public class TestConta {
	
		@SuppressWarnings("resource")
		public static void main(String[] args) throws Excecoes {
			Scanner scanner = new Scanner(System.in);//Scanner Object
			
			//Variables
			String nomeCliente=null, cpf=null, correnteOuPoupança=null, outraConta="S";
			double num = 12, agen =12; byte dig =5; float limit=1000, tax=0.005F; int idCliente=0;
			
			//Client info
			try {
				System.out.println("Insira o número de indentidade");
				idCliente = scanner.nextInt();
			} catch (Exception e) {
				throw new Excecoes("Falha", e);
			} 
			scanner.nextLine();		
			System.out.println("Insira nome completo");
			nomeCliente = scanner.nextLine();
	
			System.out.println("Insira o cpf");				
			cpf = scanner.nextLine();
			
			//Instantiation of Objects
			Cliente cliente = new Cliente(idCliente, nomeCliente, cpf);//Client INST Object
			Corrente contaCorrente = new Corrente();//Checking account INST Object
			Poupanca poupanca = new Poupanca();//Savings account INST Object
			ContaBO bo = new ContaBO();//BO INST Object
			bo.incluir(nomeCliente, agen, cliente, cpf);

			//Account option
			while(outraConta.equalsIgnoreCase("S")) { //This WHILE allows the user to create new accounts (ATM as many as possible; this will change)
				System.out.println("Escolha Cadastrar [1=Conta Corrente, 2=Poupança]");
			
				contaCorrente = new Corrente();//Instantiate object for all new accounts
				contaCorrente.setCliente(cliente);
				contaCorrente.setNumero(num);
			
				poupanca = new Poupanca();//Instantiate object for all new accounts
				poupanca.setCliente(cliente);
				poupanca.setNumero(num);
			
			correnteOuPoupança = scanner.next();			
			while(!correnteOuPoupança.equalsIgnoreCase("1") && !correnteOuPoupança.equals("2")) {//Restrict to options 1 or 2
				System.out.println("Número inválido: inserir [1] or [2]");
				correnteOuPoupança = scanner.next();
			}//END WHILE Corrente Ou Poupanca
				if(correnteOuPoupança.equals("1")) {//Choice checking account
					cadastroContaCorrente(scanner, agen, dig, limit, tax, contaCorrente);
					contaCorrente.debitarTaxa();//NTS (note to self): Void Method finally worked after I called it in the driver 
					//Option other accounts			
					System.out.println("Você gostaria de abrir outra conta [S] para Sim ou [N] para Não ?");//Option to create another account
					outraConta = scanner.next();
					while(!outraConta.equalsIgnoreCase("S") && !outraConta.equalsIgnoreCase("N")) {
						System.out.println("Valor incorreto: escolha [S] ou [N]");
						outraConta = scanner.next();					 
					}				
					for(Conta b : ContaDAO.conta1) {
						System.out.println("O número da sua conta é " + b.getNumero(idCliente));
						System.out.println(b.toString());
					}
				} 
				if(correnteOuPoupança.equals("2")) {//Choice savings account				
					poupanca.setAgencia(agen);
					poupanca.setDigito(dig);
					
					System.out.println("Qual quantia você gostaria de aplicar na Poupança?");
					try {					
						float saldP = scanner.nextFloat();
						poupanca.setSaldo(saldP);
						poupanca.setRendimento(0.02F);
						ContaDAO.gravar(poupanca);
						poupanca.creditarRendimento(0.02F);					
					} catch (Exception e) {
						throw new Excecoes("Falha", e);
					}				
					System.out.println("Você gostaria de abrir outra conta [S] para Sim ou [N] para Não ?");
					outraConta = scanner.next();
					while(!outraConta.equalsIgnoreCase("S") && !outraConta.equalsIgnoreCase("N")) {
						System.out.println("Valor incorreto: escolha [S] ou [N]");
						outraConta = scanner.next();					 
					}
				}//END IF Checking or Savings 
			}//END WHILE to create more accounts
			
			System.out.println("[1=Verificar Conta, 2=Fazer Operacao Conta Corrente, 3=Finalizar] ");
			String verOperFin=null;
			verOperFin = scanner.next();
			while(!verOperFin.equals("1") && !verOperFin.equals("2") && !verOperFin.equals("3")) {
				System.out.println("Número inválido: escolha [1=Verificar Conta, 2=Fazer Operacao Conta Corrente, 3=Finalizar]");
				verOperFin = scanner.next();
			}
			//Consult account
			if(verOperFin.equals("1")) { //Option Checking or Savings
				double consulta=0;
				String corrOuPoup="";
					System.out.println("[1=Conta Corrente, 2=Poupanca]");
					corrOuPoup = scanner.next();				
					while(!corrOuPoup.equals("1") && !corrOuPoup.equals("2")) {
						System.out.println("Número incorreto: [1=Conta Corrente, 2=Poupanca]");
						corrOuPoup = scanner.next();
					}
					if(corrOuPoup.equals("1")) {
						System.out.println("Número da conta");
						consulta = scanner.nextShort();
						contaCorrente.consultar(consulta);
					} else {
						System.out.println("Número da conta");
						consulta = scanner.nextShort();
						poupanca.consultarP(consulta);
					}				
			//Choice of Checking account Operations	
			} else if(verOperFin.equals("2")) {
				String opcao=null;
				System.out.println("Qual operação você deseja fazer? [1=Depositar, 2=Sacar, 3=Aumentar Limite, 4=Ver Saldo, Finalizar Operação]");
				opcao = scanner.next();
				while(!opcao.equals("1") && !opcao.equals("2") && !opcao.equals("3") && !opcao.equals("4")) { 
					System.out.println("Número inválido: tente novamente");
					opcao = scanner.next();
				}
				//Deposit
				if(opcao.equals("1")) {
					try {
						System.out.println("Digite o valor a ser depositado na conta corrente");
						float valor = scanner.nextFloat();
						contaCorrente.depositar(valor);
					} catch (Exception e) {
						throw new Excecoes("Falha", e);
					}
					for(Conta b : ContaDAO.conta1) {
						if(b instanceof Corrente) {
							System.out.println("Saldo " + b.getSaldo());
						}										
					}							
				} //End deposit option
				//Withdraw
				if(opcao.equals("2")) {
					System.out.println("Digite o valor a ser sacado da Conta Corrente");
					float valor = scanner.nextFloat();				
					while(limit < valor) { //This WHILE does not allow a user to withdraw an amount over the limit
						System.out.println("Limite para saque de: " + limit + " Insira outro número");
						valor = scanner.nextFloat();
					}
					contaCorrente.sacar(valor);
					for(Conta b : ContaDAO.conta1) {
						if(b instanceof Corrente) {
							System.out.println("Saldo " + b.getSaldo());	
						}											
					}
				}//End withdraw option 
				//Increase limit
				if(opcao.equals("3")) {
					float newLimit=0;
					try {
						System.out.println("Aumente o limite");
						newLimit = scanner.nextFloat();				
						System.out.println("New limit: " + contaCorrente.aumentarLimite(newLimit));
					} catch (Exception e) {
						throw new Excecoes("Falha", e);
					}				
					for(Conta b : ContaDAO.conta1) {
						if(b instanceof Corrente) {
							System.out.println("Saldo " + b.toString());
						}											
					}
				}//End increase limit option
				//Check balance
				if(opcao.equals("4")) {				
					for(Conta b : ContaDAO.conta1) {
						if(b instanceof Corrente) {
							System.out.println("Saldo" + b.getSaldo());	
						}
					}
				} 
			} else {
				System.out.println("Operação finalizada");
			}
					
		}//main
		private static void cadastroContaCorrente(Scanner scanner, double agen, byte dig, float limit, float tax,
				Corrente contaCorrente) throws Excecoes {
			float sald;
			contaCorrente.setAgencia(agen);
			contaCorrente.setDigito(dig);
			contaCorrente.setLimite(limit);
			contaCorrente.setTaxa(tax);
				try {
					System.out.println("Qual quantia você gostaria de aplicar na Conta Corrente?");
					sald = scanner.nextFloat();	
					contaCorrente.setSaldo(sald);
					ContaDAO.gravar(contaCorrente);
				} catch (Exception e) {
					throw new Excecoes("Falha", e);
				}	

		}

}
