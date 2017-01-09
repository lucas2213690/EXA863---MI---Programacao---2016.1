package br.uefs.stmserver.util;

/**
 * Classe Célula, que auxilia na implementação da lista encadeada
 * @author Lucas Alves da Encarnação Oliveira
 *
 */

public class Celula {
	
	private Object elemento;
	
	private Celula proxima;
	
	/**
	 * Construtor com overloading
	 * @param proxima - Próximo elemento
	 * @param elemento - Elemento daquela posição
	 */
	
	public Celula(Celula proxima, Object elemento){
		this.proxima = proxima;
		
		this.elemento = elemento;
	}
	
	public Celula(Object elemento){
		this.elemento = elemento;
	}
	
	/**
	 * Método utilizado para alterar o atributo proxima
	 * @param proxima - Referência para a proxima Celula
	 */
	public void setProxima(Celula proxima){
		this.proxima = proxima;
	}
	
	/**
	 * Método utilizado para retormar a próxima Celula
	 * @return proxima - Próxima Celula
	 */
	public Celula getProxima(){
		return proxima;
	}
	
	/**
	 * Método utilizado para recuperar o elemento atual da Celula
	 * @return elemento - Elemento atual de uma Celula
	 */
	public Object getElemento(){
		return elemento;
	}
	/**
	 * Método que seta um elemento dentro de determinada celula
	 * @param o - Elemento que sera inserido dentro da celula
	 */
	public void setElemento(Object o){
		elemento = o;
	}
}
