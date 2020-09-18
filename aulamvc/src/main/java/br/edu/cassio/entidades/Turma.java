package br.edu.cassio.entidades;

import java.util.List;

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
@Entity
@AllArgsConstructor
@Transactional
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Turma {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
	private String nome;
	
	public Turma() {
	}
	
	public Turma(String nome, Integer ano, Integer creditos) {
		super();
		this.nome = nome;
	}
 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turma", fetch = FetchType.EAGER)
    private List<Matricula> matriculas;

}
