package ravim.fiap.projeto.banco.dao;

import java.util.ArrayList;
import java.util.List;

import ravim.fiap.projeto.banco.model.Conta;



public class ContaDAO {

	public static List<Conta> conta1 = new ArrayList<>();
	
	public static String gravar(Conta c) {
		conta1.add(c);
		return "gravado com sucesso";
	}
}
