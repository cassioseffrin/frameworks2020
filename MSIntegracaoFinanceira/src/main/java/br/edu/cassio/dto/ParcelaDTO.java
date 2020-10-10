package br.edu.cassio.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParcelaDTO {
	private Integer id;
	private Integer alunoId;
	private Double valor;
	private LocalDate dataVencimento;
	private LocalDate dataPagamento;
	private Double desconto;
	private Double acrescimo;
	private Double valorPago;
	private String dataGeracao;
	private String numero;
}
