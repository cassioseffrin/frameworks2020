package br.edu.cassio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.cassio.entidades.Aluno;
import br.edu.cassio.entidades.Turma;
import br.edu.cassio.repository.AlunoRepository;
import br.edu.cassio.repository.TurmaRepository;

@Controller
@RequestMapping(path = "/aluno")
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private TurmaRepository turmaRepository;

	@GetMapping(path = "/todos")
	public @ResponseBody List<Aluno> getTodos() {
		Iterable<Aluno> Alunos = alunoRepository.findAll();
		ArrayList<Aluno> list = new ArrayList<Aluno>();
		Alunos.forEach(t -> list.add(t));
		return list;
	}

	@GetMapping(path = "/findAlunoById")
	public @ResponseBody Aluno getAluno(@RequestParam Integer id) {
		Aluno aluno = alunoRepository.findById(id).orElse(new Aluno());
		return aluno;
	}

	@GetMapping(path = "/adicionar")
	public @ResponseBody String addAluno(@RequestParam String nome, @RequestParam String endereco,
			@RequestParam String cidade, @RequestParam String cpf) {
		Aluno a = new Aluno();
		a.setNome(nome);
//		a.setEndereco(endereco);
//		a.setCidade(cidade);
		a.setCpf(cpf);

		alunoRepository.save(a);
		return "Aluno Salvo!";
	}

}