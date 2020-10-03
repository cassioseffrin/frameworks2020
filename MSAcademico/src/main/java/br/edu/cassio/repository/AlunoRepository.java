package br.edu.cassio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.cassio.entidades.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Integer> {
	
   @Query("SELECT aluno FROM Aluno aluno WHERE aluno.nome = :nome ")
   Aluno findAlunosByNome(@Param("nome") String nome);

}
