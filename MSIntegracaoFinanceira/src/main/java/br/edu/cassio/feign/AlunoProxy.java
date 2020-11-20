package br.edu.cassio.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.cassio.dto.AlunoDTO;

@FeignClient(name="msacademico", url = "localhost:8080")
public interface AlunoProxy {

	@GetMapping("/aluno/findById/{id}")
	public AlunoDTO getAlunoById(@PathVariable("id") Integer id);

}