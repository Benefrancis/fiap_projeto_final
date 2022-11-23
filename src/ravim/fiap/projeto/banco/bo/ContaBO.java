package ravim.fiap.projeto.banco.bo;

import ravim.fiap.projeto.banco.dao.ContaDAO;
import ravim.fiap.projeto.banco.excecoes.Excecoes;
import ravim.fiap.projeto.banco.model.Cliente;

public class ContaBO {
	
ContaDAO dao = new ContaDAO();
	
	public void incluir(String nome, double agencia, Cliente cliente, String cpf) throws Excecoes {
		
		if(nome.length() <= 5) {
			throw new Excecoes("Número de caracteres insuficiente");
		}
		if(cpf.length() < 11 || cpf.length() > 11 ) {
			throw new Excecoes("CPF: Número de dígitos incorreto");
		}
		if(agencia <= 0) {
			throw new Excecoes("Número da agencia tem que ser maior que zero") ;
		}
	}
}
