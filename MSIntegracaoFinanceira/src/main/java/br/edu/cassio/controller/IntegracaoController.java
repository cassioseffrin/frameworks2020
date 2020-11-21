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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import br.edu.cassio.dto.AlunoDTO;
import br.edu.cassio.dto.ClienteInadimplenteDTO;
import br.edu.cassio.dto.ParcelaDTO;
import br.edu.cassio.feign.AlunoProxy;
import br.edu.cassio.feign.FinanceiroProxy;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(path = "/integracaoFinanceira")
@ComponentScan
@Slf4j
public class IntegracaoController {

	@Autowired
	LoadBalancerClient loadBalancer;

	@Autowired
	private AlunoProxy alunoProxy;

	@Autowired
	private FinanceiroProxy financeiroProxy;

//	@GetMapping("/findAlunoById/{id}")
//	public  @ResponseBody AlunoDTO findAluno(@PathVariable int id) {
//		ServiceInstance instance = loadBalancer.choose("msacademico");
//		URI uriAluno = URI.create(String.format("http://%s:%s/aluno/findById?id=%s", instance.getHost(), instance.getPort(), id));
//		RestTemplate rest = new RestTemplate();
//		AlunoDTO a = rest.getForObject(uriAluno, AlunoDTO.class);
//		return a;
//	}

	@GetMapping("/findAlunoByIdFeign/{id}")
	public @ResponseBody AlunoDTO findAlunoFeign(@PathVariable int id) {
		return alunoProxy.pegarDados(id);
	}

	@GetMapping("/parcelasInadimplentes")
	public @ResponseBody List<ClienteInadimplenteDTO> parcelasInacimpletes() {
		List<ParcelaDTO> lstParcelas = financeiroProxy.todasParcelasInadimplentes();
		ArrayList<ClienteInadimplenteDTO> lstInadim = new ArrayList<>();
		for (ParcelaDTO p : lstParcelas) {
			AlunoDTO a = alunoProxy.pegarDados(p.getAlunoId());
			long diferencaData = diferencaData(p);
			ClienteInadimplenteDTO cli = new ClienteInadimplenteDTO(a.getNome(), a.getCpf(), p.getDataVencimento(),
					diferencaData, p.getDesconto(), p.getAcrescimo(), p.getNumero());
			lstInadim.add(cli);
		}
		return lstInadim;
	}
	
	
	private long diferencaData(ParcelaDTO p) {
		LocalDate dataAtual = LocalDate.now();
		long diff = dataAtual.toEpochDay() - p.getDataVencimento().toEpochDay();
		return diff;
	}

}