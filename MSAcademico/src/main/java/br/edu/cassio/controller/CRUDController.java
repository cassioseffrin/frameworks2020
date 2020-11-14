package br.edu.cassio.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.cassio.uteis.Uteis;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CRUDController<T, ID extends Serializable> {
	private CrudRepository<T, ID> repository;
	
	public CRUDController(CrudRepository<T, ID> repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public @ResponseBody Collection<T> todos() {
		Iterable<T> todos = this.repository.findAll();
		return Uteis.converterInterabelParaList(todos);
	}

	@GetMapping(path = "/findById")
	public @ResponseBody Object getById(@RequestParam ID id) {
		Optional<T> objeto = this.repository.findById(id);
		log.info("chamado endpoint findById, classe: "+ objeto.getClass());
		return objeto;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> salvar(@RequestBody T json) {
		log.info("criado() com body {} do tipo {}", json, json.getClass());
		T objetoCriado = this.repository.save(json);
		Map<String, Object> m = new HashMap<>();
		m.put("Sucesso", true);
		m.put("criado", objetoCriado);
		return m;
	}
	
	@PostMapping(value="/{id}", consumes={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Map<String, Object> atualizar(@PathVariable ID id, @RequestBody T json) {
        T entity = (T) this.repository.findById(id);
        T atualizada = this.repository.save(entity);
        Map<String, Object> m = new HashMap<>();
        m.put("id", id);
        m.put("atualizada", atualizada);
        return m;
    }
}
