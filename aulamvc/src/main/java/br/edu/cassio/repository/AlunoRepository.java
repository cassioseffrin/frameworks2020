package br.edu.cassio.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.cassio.entidades.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Integer> {

}
