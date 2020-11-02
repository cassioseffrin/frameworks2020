package br.edu.cassio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.cassio.entidades.Endereco;

@Controller
@RequestMapping("/endereco")
public class EnderecoController extends CRUDController<Endereco, Integer> {
    @Autowired
    public EnderecoController(CrudRepository<Endereco, Integer> repository) {
        super(repository);
    }
}