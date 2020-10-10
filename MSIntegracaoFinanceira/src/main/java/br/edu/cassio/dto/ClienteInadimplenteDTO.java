package br.edu.cassio.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteInadimplenteDTO {
	private String nomeCliente;
	private String cpfCliente;
	private LocalDate dataVencimento;
	private Long diasAtraso;
	private Double desconto;
	private Double acrescimo;
	private String numero;
}
