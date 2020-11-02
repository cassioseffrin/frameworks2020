package br.edu.cassio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.cassio.entidades.Aluno;
 


@Controller
@RequestMapping(path = "/aluno")
public class AlunoController extends CRUDController<Aluno , Integer>   {
	@Autowired
	public AlunoController(CrudRepository<Aluno, Integer> repository) {
        super(repository);
    }
}