package br.edu.cassio;

import br.com.arpa.api.entidade.EntidadePersistente;
import br.com.arpa.api.entidade.exception.ArpaRemoverRegraException;
import br.com.arpa.api.entidade.grid.FiltroColunaGrid;
import br.com.arpa.api.regra.ArpaRegra;
import br.com.arpa.api.rest.CrudRest;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  * Implementação padrão da interface CrudRest. Implementação em Spring
 * utilizado MVC com RequestMapping, ResquestBody e * ResponseBody. * * @author
 * forchesatto 
 */
public abstract class CrudRestPadrao<T extends EntidadePersistente, R extends ArpaRegra<T, ?>> implements CrudRest<T> {
	     
	protected static final String MEDIA_TYPE_CONSUMES = "application/json";     
	protected static final String MEDIA_TYPE_PRODUCES = "application/json; charset=utf-8";     
	private Class<T> modelClazz;     
	@Autowired   
	private R regra;     
	@Autowired    @Qualifier("binarioPermissaoValidador")   
	private Validator validator;     

	public CrudRestPadrao() {    }     /**
									 *      * Construtor padrão pois a regra de negécio é sempre obritória para o
									 * rest saber como executar o comando     * efetivamente.     *     * @param
									 * regra     *            {@link ArpaRegra}.     
									 */
	  

	public CrudRestPadrao(R regra) {        this.regra = regra;    }     /**
																 *      * Método padrão para recuperar de uma classe
																 * Generic o tipo da EntidadePersistence. É sempre pego
																 * da posição 0 do     * generic da classe filha.     * 
																 *    * @return {@link Class} T     
																 */
	  

	@SuppressWarnings("unchecked")    protected Class<T> getModelClazz() {        if (this.modelClazz == null) {            Type[] types = (( ParameterizedType ) getClass().getGenericSuperclass()).getActualTypeArguments();            this.modelClazz = ( Class<T> ) types[0];        }        return this.modelClazz;    }     /**
																																																																						 *  
																																																																						 *  
																																																																						 *  *
																																																																						 * Retorna
																																																																						 * a
																																																																						 * classe
																																																																						 * de
																																																																						 * regra
																																																																						 * responsável
																																																																						 * por
																																																																						 * efetivamente
																																																																						 * executar
																																																																						 * os
																																																																						 * comandos. 
																																																																						 *  
																																																																						 *  * 
																																																																						 *  
																																																																						 *  * @return
																																																																						 * {@link ArpaRegra}. 
																																																																						 *  
																																																																						 *  
																																																																						 */
	  

	protected R getRegra() {        return regra;    }     

	@Override    @ResponseBody    public T alterar(@RequestBody final T entidade) {        return regra.alterar(entidade);    }     

	@Override    @RequestMapping(            method = RequestMethod.POST,            value = "/autoComplete",            produces = MediaType.APPLICATION_JSON_VALUE)    @ResponseBody    public List<T> autoComplete(@RequestParam("term") String nome) {        return regra.autoComplete(nome);    }     

	@Override    public String autoCompleteCodigo(@PathVariable("valor") Long valor) {        return regra.autoCompleteCodigo(valor);    }     

	@Override    @RequestMapping(            method = RequestMethod.POST,            value = "/autoCompleteInternacionalizacao",            produces = MediaType.APPLICATION_JSON_VALUE)    @ResponseBody    public List<T> autoCompleteInternacionalizacao(@RequestParam("nomeCampo") String nomeCampo, @RequestParam("nome") String nome,            @RequestParam("idiomaId") Long idiomaId) {        return regra.autoCompleteInternacionalizacao(nomeCampo, nome, idiomaId);    }        @Override    @ResponseBody   

	public T buscar(@PathVariable(            value = "id") Long id) {        return regra.buscar(id).orElse(null);    }        @Override    @ResponseBody   

	public T inserir(@RequestBody T entidade) {        return regra.inserir(entidade);    }     

	@Override    public Page<T> listaFiltrada(@RequestParam("pagina") int pagina, @RequestParam("tamanhoPagina") int tamanhoPagina,            @RequestBody List<FiltroColunaGrid> filtros) {        return regra.filtrado(filtros, pagina, tamanhoPagina);    }        @Override   

	public void remover(@PathVariable Long codigo) {        try {            T entidade = getModelClazz().newInstance();            entidade.setCodigo(codigo);            regra.remover(entidade);        } catch (ReflectiveOperationException e) {            throw new ArpaRemoverRegraException(e.getMessage(), e);        }    }     

	@Override    public void remover(@RequestBody T entidade) {        regra.remover(entidade);    }
}Ï