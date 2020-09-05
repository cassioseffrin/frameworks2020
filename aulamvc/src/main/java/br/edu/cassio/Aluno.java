package br.edu.cassio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
@Entity (name = "tb_aluno")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Aluno {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	private String nome;
	private String endereco;
	private String cidade;	
	private String cpf;
	

	Aluno(){
	}
	 
    @ManyToOne(optional = false)
    private Turma turma;
 
}
