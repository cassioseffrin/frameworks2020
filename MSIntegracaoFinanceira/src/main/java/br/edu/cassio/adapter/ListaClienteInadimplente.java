package br.edu.cassio.adapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.edu.cassio.dto.ClienteInadimplenteDTO;

public class ListaClienteInadimplente {

	public static Collection<ClienteInadimplenteDTO> listarClientes() {

		ClienteInadimplenteDTO cli = new ClienteInadimplenteDTO("Nome", "234234", LocalDate.now(), 10L, 10.2, 12.2,
				"1");

		List<ClienteInadimplenteDTO> lst = new ArrayList<>();
		lst.add(cli);

		return lst;

	}
}
