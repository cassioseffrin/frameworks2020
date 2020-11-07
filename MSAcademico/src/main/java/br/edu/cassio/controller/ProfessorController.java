package br.edu.cassio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.cassio.entidades.Professor;

@Controller
@RequestMapping("/professor")
public class ProfessorController extends CRUDController<Professor, Integer> {
    
	@Autowired
    public ProfessorController(CrudRepository<Professor, Integer> repository) {
        super(repository);
    }
    
}