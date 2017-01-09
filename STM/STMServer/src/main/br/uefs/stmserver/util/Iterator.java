package br.uefs.stmserver.util;

import java.util.NoSuchElementException;

/**
 * Classe que representa o iterador utilizado para percorrer a lista encadeada
 * @author Lucas Alves da Encarna��o Oliveira
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
	 * M�todo utilizado para verificar se tem um pr�ximo elemento
	 * @return boolean - true se tiver proximo elemento, false se n�o tiver nenhum pr�ximo elemento
	 */
	public boolean temProximo(){
		//return proximaCelula.getProxima() != null;
		return proximaCelula != null;
	}
	
	/**
	 * M�todo que retornar o elemento atual e itera a lista para o pr�ximo elemento
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

