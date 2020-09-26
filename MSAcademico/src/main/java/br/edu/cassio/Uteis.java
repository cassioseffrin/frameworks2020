package br.edu.cassio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class Uteis {
	public static <T> Collection<T> converterInterabelToList(Iterator<T> iterator) {
		Collection<T> list = new ArrayList<>();
		iterator.forEachRemaining(list::add);
		return list;
	}

	public static <T> Collection<T> converterInterabelParaList(Iterable<T> todos) {
		Collection<T> novaLista = new ArrayList<>();
		todos.forEach(novaLista::add);
		return novaLista;
		
	}

}
