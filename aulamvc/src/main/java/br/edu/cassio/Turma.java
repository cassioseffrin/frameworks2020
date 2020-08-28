package br.edu.cassio;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Turma {
	
	@Column
	private String nome;
	
	@Column
	private Integer ano;
	
	@Column
	private Integer creditos;

	
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
