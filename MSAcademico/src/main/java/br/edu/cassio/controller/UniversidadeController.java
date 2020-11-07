package br.edu.cassio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.cassio.entidades.Universidade;

@Controller
@RequestMapping("/universiade")
public class UniversidadeController extends CRUDController<Universidade, Integer> {
    
	@Autowired
    public UniversidadeController(CrudRepository<Universidade, Integer> repository) {
        super(repository);
    }
    
}