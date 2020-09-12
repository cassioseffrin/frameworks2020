package br.edu.cassio;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
@Entity (name = "tb_turma")
@AllArgsConstructor
@Transactional
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Turma {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
	private String nome;
	
	private Integer ano;
	
	private Integer creditos;

	public Turma() {
	}
	
	public Turma(String nome, Integer ano, Integer creditos) {
		super();
		this.nome = nome;
		this.ano = ano;
		this.creditos = creditos;
	}
	
 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turma", fetch = FetchType.EAGER)
    private Collection<Aluno> alunoCollection;

}
