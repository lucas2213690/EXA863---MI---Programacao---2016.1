package br.uefs.stmserver.util;

/**
 * Classe C�lula, que auxilia na implementa��o da lista encadeada
 * @author Lucas Alves da Encarna��o Oliveira
 *
 */

public class Celula {
	
	private Object elemento;
	
	private Celula proxima;
	
	/**
	 * Construtor com overloading
	 * @param proxima - Pr�ximo elemento
	 * @param elemento - Elemento daquela posi��o
	 */
	
	public Celula(Celula proxima, Object elemento){
		this.proxima = proxima;
		
		this.elemento = elemento;
	}
	
	public Celula(Object elemento){
		this.elemento = elemento;
	}
	
	/**
	 * M�todo utilizado para alterar o atributo proxima
	 * @param proxima - Refer�ncia para a proxima Celula
	 */
	public void setProxima(Celula proxima){
		this.proxima = proxima;
	}
	
	/**
	 * M�todo utilizado para retormar a pr�xima Celula
	 * @return proxima - Pr�xima Celula
	 */
	public Celula getProxima(){
		return proxima;
	}
	
	/**
	 * M�todo utilizado para recuperar o elemento atual da Celula
	 * @return elemento - Elemento atual de uma Celula
	 */
	public Object getElemento(){
		return elemento;
	}
	/**
	 * M�todo que seta um elemento dentro de determinada celula
	 * @param o - Elemento que sera inserido dentro da celula
	 */
	public void setElemento(Object o){
		elemento = o;
	}
}
