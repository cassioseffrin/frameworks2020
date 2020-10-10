package br.edu.cassio.dto;

import java.util.ArrayList;
import java.util.List;

public class ListaParcelas {

	private List<ParcelaDTO> parcelas;

	public ListaParcelas() {
		parcelas = new ArrayList<>();
	}

	public List<ParcelaDTO> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelaDTO> parcelas) {
		this.parcelas = parcelas;
	}

}
