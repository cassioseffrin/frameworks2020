package br.edu.cassio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import br.edu.cassio.dto.AlunoDTO;
import br.edu.cassio.dto.ClienteInadimplenteDTO;
import br.edu.cassio.dto.ParcelaDTO;

@Controller
@RequestMapping(path = "/integracaoFinanceira")
@ComponentScan
public class IntegracaoController {
	
	@Autowired
	RestTemplate rest = new RestTemplate();

	@GetMapping("/parcelasInacimpletes")
	public @ResponseBody List<ClienteInadimplenteDTO> parcelasInacimpletes() {
		ArrayList<ClienteInadimplenteDTO> lstInadim = getParcelasInadimplentes();
		return lstInadim;
	}

	private ArrayList<ClienteInadimplenteDTO> getParcelasInadimplentes() {
		
		String url = "http://localhost:8081/parcela/parcelasInadimplentes";
		ResponseEntity<ParcelaDTO[]> response = rest.getForEntity(url, ParcelaDTO[].class);
		ParcelaDTO[] lstParcelas = response.getBody();
		ArrayList<ClienteInadimplenteDTO> lstInadim = new ArrayList<>();
		for (int i =0 ; i< lstParcelas.length ; i++) {
			ParcelaDTO p = lstParcelas[i];	
			String urlAluno = "http://localhost:8080/aluno/findById?id=" + p.getAlunoId();
			AlunoDTO a = rest.getForObject(urlAluno, AlunoDTO.class);
			LocalDate dataAtual = LocalDate.now();	
			long diff = dataAtual.toEpochDay() - p.getDataVencimento().toEpochDay(); 
			ClienteInadimplenteDTO cli = new ClienteInadimplenteDTO(
					a.getNome(), a.getCpf(), p.getDataVencimento(), diff, p.getDesconto(), p.getAcrescimo(),   p.getNumero());
			lstInadim.add(cli);
		}
		return lstInadim;
	}

}