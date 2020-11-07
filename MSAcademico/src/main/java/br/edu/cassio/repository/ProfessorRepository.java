package br.edu.cassio.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.cassio.entidades.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, Integer> {
}
