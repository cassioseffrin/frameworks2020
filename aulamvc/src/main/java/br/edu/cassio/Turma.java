package br.edu.cassio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 

@Entity (name = "tb_turma")
public class Turma {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer codigo;
    
	private String nome;
	
	private Integer ano;
	
	@Column (name="Credito")
	private Integer creditos;

	
	public Turma() {

	}
	
	public Turma(String nome, Integer ano, Integer creditos) {
		super();
		this.nome = nome;
		this.ano = ano;
		this.creditos = creditos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getCreditos() {
		return creditos;
	}

	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}
	
	
	

}
