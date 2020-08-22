package br.edu.cassio;

public class Turma {
	
	private String nome;
	private Integer ano;
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
