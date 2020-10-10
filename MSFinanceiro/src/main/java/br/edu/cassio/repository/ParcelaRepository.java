package br.edu.cassio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.edu.cassio.entidades.Parcela;

public interface ParcelaRepository extends CrudRepository<Parcela, Integer> {

	@Query("SELECT p FROM Parcela p")
	List<Parcela> findAllParcelasInadimplentes();
//	Iterable<Parcela>
}
