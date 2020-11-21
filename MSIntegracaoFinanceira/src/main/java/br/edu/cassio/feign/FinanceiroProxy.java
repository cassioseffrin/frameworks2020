package br.edu.cassio.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.cassio.dto.ParcelaDTO;

@FeignClient(name="msfinanceiro")
public interface FinanceiroProxy {
	@GetMapping("/parcela/parcelasInadimplentes")
	public List<ParcelaDTO> todasParcelasInadimplentes();

}
