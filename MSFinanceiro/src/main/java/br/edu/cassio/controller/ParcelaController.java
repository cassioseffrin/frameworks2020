package br.edu.cassio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.cassio.entidades.Parcela;
import br.edu.cassio.repository.ParcelaRepository;

 

@Controller
@RequestMapping(path = "/parcela")
public class ParcelaController {

	@Autowired
	private ParcelaRepository parcelaRepository;

	@GetMapping(path = "/todos")
	public @ResponseBody List<Parcela> getTodas() {
		Iterable<Parcela> parcelas = parcelaRepository.findAll();
		ArrayList<Parcela> list = new ArrayList<Parcela>();
		parcelas.forEach(t -> list.add(t));
		return list;
	}
	
	
	@GetMapping(path = "/parcelasAluno")
	public @ResponseBody List<Parcela> getParcelasAluno(@RequestParam Integer alunoId) {
		Iterable<Parcela> parcelas = parcelaRepository.findAll();
		ArrayList<Parcela> list = new ArrayList<Parcela>();
		parcelas.forEach(t -> list.add(t));
		return list;
	}
	
	

	@GetMapping(path = "/adicionar")
	public @ResponseBody String addParcela(@RequestParam Integer alunoId, @RequestParam Double valor,
			@RequestParam String dataVencimento, @RequestParam String numero) {
		Parcela p = new Parcela();
		p.setAlunoId(alunoId);
		p.setValor(valor);
		p.setDataVencimento(dataVencimento);
		p.setNumero(numero);
		parcelaRepository.save(p);
		return "parcela Salva!";
	}

}