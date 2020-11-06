package br.edu.cassio.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
public class Matricula {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer ano;

	private Integer sequencia;

	@Temporal(value = TemporalType.DATE)
	private Date dataHora;

	
    @ManyToOne(optional = false)
    private Aluno aluno;
    
    @ManyToOne(optional = false)
    private Turma turma;
    
	@ManyToMany(mappedBy = "matriculas")
	private List<Disciplina> disciplinas;
	
	@ManyToOne()
	private Curso curso;

//		@ManyToMany
//		@JoinTable(name = "matricula_disciplina", joinColumns = {
//				@JoinColumn(name = "matricula_id", referencedColumnName = "id") }, 
//		inverseJoinColumns = {
//				@JoinColumn(name = "disciplina_id", referencedColumnName = "id") })
//		private List<Disciplina> disciplinas;

}
