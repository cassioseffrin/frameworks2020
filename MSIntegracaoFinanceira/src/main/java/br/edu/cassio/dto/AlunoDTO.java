package br.edu.cassio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class AlunoDTO {
	private Integer id;
	private String nome;
	private String endereco;
	private String cidade;
	private String cpf;
}
