package br.edu.cassio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.cassio.entidades.Turma;
import br.edu.cassio.repository.TurmaRepository;
import lombok.extern.log4j.Log4j;


//@Log4j
@Controller
@RequestMapping(path = "/turma")

public class TurmaController {

	@Autowired
	private TurmaRepository turmaRepository;

	@GetMapping(path = "/todas")
	public @ResponseBody List<Turma> getTodos() {
		Iterable<Turma> turmas = turmaRepository.findAll();
		ArrayList<Turma> list = new ArrayList<Turma>();
		
		turmaRepository.existsById(1);
		turmas.forEach(t -> list.add(t));
		return list;
	}

	@GetMapping(path = "/adicionar")
	public @ResponseBody String addTurma(@RequestParam String nome, @RequestParam Integer ano,
			@RequestParam Integer creditos) {		
		Turma t = new Turma( nome, ano, creditos);
		turmaRepository.save(t);
		return "Salvo!";
	}

}