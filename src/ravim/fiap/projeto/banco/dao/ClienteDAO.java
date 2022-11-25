package ravim.fiap.projeto.banco.dao;

import java.util.List;
import java.util.Vector;

import ravim.fiap.projeto.banco.model.Cliente;

public class ClienteDAO {

	private static List<Cliente> clientes;

	static {
		clientes = new Vector<Cliente>();
	}

	public static List<Cliente> findAll() {
		return clientes;
	}

	public static Cliente salvar(Cliente cliente) {
		
		if(cliente.getId()==0) cliente.setId(clientes.size() + 1);
		clientes.add(cliente);
		return cliente;
	}

	public static Cliente findByCPF(String CPF) {
		return clientes.stream().filter(c -> c.getCpf() == CPF).findFirst()
				.orElse(null);
	}
}
