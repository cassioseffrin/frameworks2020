package br.edu.cassio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.cassio.entidades.Aluno;

@Controller
@RequestMapping(path = "/aluno")
public class AlunoController extends CRUDController<Aluno, Integer> {
	
	
	@Autowired
	DiscoveryClient clientes;
	
	@Autowired
	public AlunoController(CrudRepository<Aluno, Integer> repository) {
		super(repository);
	}



	@GetMapping("/servicos")
	public @ResponseBody String getURI() {
		List<ServiceInstance> lstRecursosDaInstancia = clientes.getInstances("MSFINANCEIRO");
		if (lstRecursosDaInstancia != null && lstRecursosDaInstancia.size() > 0) {
			ServiceInstance instancia = lstRecursosDaInstancia.get(0);
			return String.format("URI: %s \nHost: %s\n ServiceId: %s", instancia.getUri().toString(),
					instancia.getHost(), instancia.getServiceId());
		}
		return "servico nao encontrado";
	}

}