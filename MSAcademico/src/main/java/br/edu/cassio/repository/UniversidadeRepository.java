package br.edu.cassio.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.cassio.entidades.Universidade;

public interface UniversidadeRepository extends CrudRepository<Universidade, Integer> {
}
