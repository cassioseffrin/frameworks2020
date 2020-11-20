package br.edu.cassio.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import br.edu.cassio.dto.AlunoDTO;
import br.edu.cassio.dto.ClienteInadimplenteDTO;
import br.edu.cassio.dto.ParcelaDTO;
import br.edu.cassio.feign.AlunoProxy;

@Controller
@RequestMapping(path = "/integracaoFinanceira")
@ComponentScan

 
public class IntegracaoController {

	@Autowired
	LoadBalancerClient loadBalancer;
	
	@Autowired
	AlunoProxy alunoProxy;
 
	@GetMapping("/findAlunoById/{id}")
	public  @ResponseBody AlunoDTO findAluno(@PathVariable int id) {
		ServiceInstance instance = loadBalancer.choose("msacademico");
//		URI uriAluno = URI.create(String.format("http://%s:%s/aluno/findById?id=%s", instance.getHost(), instance.getPort(), id));
//		RestTemplate rest = new RestTemplate();
//		AlunoDTO a = rest.getForObject(uriAluno, AlunoDTO.class);
		
		
		AlunoDTO a = alunoProxy.getAlunoById(id);

		return a;
	}

	@GetMapping("/parcelasInacimpletes")
	public @ResponseBody List<ClienteInadimplenteDTO> parcelasInacimpletes() {
		ArrayList<ClienteInadimplenteDTO> lstInadim = getParcelasInadimplentes();
		return lstInadim;
	}

	private ArrayList<ClienteInadimplenteDTO> getParcelasInadimplentes() {
		RestTemplate rest = new RestTemplate();
		String url = "http://localhost:8081/parcela/parcelasInadimplentes";
		ResponseEntity<ParcelaDTO[]> response = rest.getForEntity(url, ParcelaDTO[].class);
		ParcelaDTO[] lstParcelas = response.getBody();
		ArrayList<ClienteInadimplenteDTO> lstInadim = new ArrayList<>();
		for (int i = 0; i < lstParcelas.length; i++) {
			ParcelaDTO p = lstParcelas[i];
			String urlAluno = "	" + p.getAlunoId();
			AlunoDTO a = rest.getForObject(urlAluno, AlunoDTO.class);
			LocalDate dataAtual = LocalDate.now();
			long diff = dataAtual.toEpochDay() - p.getDataVencimento().toEpochDay();
			ClienteInadimplenteDTO cli = new ClienteInadimplenteDTO(a.getNome(), a.getCpf(), p.getDataVencimento(),
					diff, p.getDesconto(), p.getAcrescimo(), p.getNumero());
			lstInadim.add(cli);
		}
		return lstInadim;
	}

}