package br.edu.cassio.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Transactional
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Parcela {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer alunoId;
	private Double valor;
	private String dataVencimento;
	private Double desconto;
	private Double acrescimo;
	private Double valorPago;
	private String dataGeracao;
	private String dataPagamento;
	private String numero;

	public Parcela() {
	}

}
