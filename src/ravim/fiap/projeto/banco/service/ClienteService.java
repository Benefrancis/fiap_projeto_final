package ravim.fiap.projeto.banco.service;

import ravim.fiap.projeto.banco.dao.ClienteDAO;
import ravim.fiap.projeto.banco.model.Cliente;

public class ClienteService {

	public static Cliente salvar(Cliente cliente) {

		Cliente existe = findByCPF(cliente.getCpf());

		if (existe == null)
			return ClienteDAO.salvar(cliente);

		return existe;

	}

	public static Cliente findByCPF(String CPF) {
		return ClienteDAO.findByCPF(CPF);
	}

}
