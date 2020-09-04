package br.edu.cassio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/turma")
public class TurmaController {

	@Autowired
	private TurmaRepository turmaRepository;

	@GetMapping(path = "/todos")
	public @ResponseBody List<Turma> getTodos() {
		Iterable<Turma> turmas = turmaRepository.findAll();
		ArrayList<Turma> list = new ArrayList<Turma>();
		turmas.forEach(t -> list.add(t));
		return list;
	}

	@GetMapping(path = "/adicionar")
	public @ResponseBody String addTurma(@RequestParam String nome, @RequestParam Integer ano,
			@RequestParam Integer creditos) {
		Turma t = new Turma(nome, ano, creditos);
		turmaRepository.save(t);
		return "Salvo!";
	}

}