package br.uefs.stmserver.util;

import java.util.NoSuchElementException;

/**
 * Classe que representa o iterador utilizado para percorrer a lista encadeada
 * @author Lucas Alves da Encarnação Oliveira
 *
 */

public class Iterator implements Iterador {
	
	private Celula proximaCelula;
	
	/**
	 * Construtor da classe Iterator
	 * @param primeira - Recebe o primeiro elemento da lista 
	 */
	public Iterator(Celula primeira){
		this.proximaCelula = primeira;
	}
	
	/**
	 * Método utilizado para verificar se tem um próximo elemento
	 * @return boolean - true se tiver proximo elemento, false se não tiver nenhum próximo elemento
	 */
	public boolean temProximo(){
		//return proximaCelula.getProxima() != null;
		return proximaCelula != null;
	}
	
	/**
	 * Método que retornar o elemento atual e itera a lista para o próximo elemento
	 * @return node - Retorna o elemento atual
	 * @exception NoSuchElementException()
	 */
	public Object obterProximo(){
		
		if (!temProximo()) throw new NoSuchElementException();
        Object node = proximaCelula.getElemento();
        proximaCelula = proximaCelula.getProxima();
        return node;
	}

}

