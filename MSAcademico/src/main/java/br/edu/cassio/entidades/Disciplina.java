package br.edu.cassio.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Transactional
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Getter
@Setter
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nome;

	private Integer creditos;

	@ManyToMany
	private List<Matricula> matriculas;

//		@ManyToMany(mappedBy = "disciplinas")
//		private List<Matricula> matriculas;

}
