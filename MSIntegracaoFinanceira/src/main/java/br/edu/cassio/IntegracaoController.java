package br.edu.cassio;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import br.edu.cassio.dto.AlunoDTO;

@Controller
@RequestMapping(path = "/integracaoFinanceira")
@ComponentScan
public class IntegracaoController {

	@GetMapping("/pegarDadosFinanceiro")
	public @ResponseBody AlunoDTO pegarDados(@RequestParam Integer id) {
		RestTemplate rest = new RestTemplate();
		String url = "http://localhost:8080/aluno/findAlunoById?id=" + id;
		AlunoDTO aluno = rest.getForObject(url, AlunoDTO.class);
		
		
		String urlParcelas = "http://localhost:8081/parcela/parcelasAluno?alunoId=" + id;
//		ParcelasInadimpletesDTO parcelas = rest.getForObject(url, ParcelasInadimpletesDTO.class);
		return parcelas;
	}

}