package br.uefs.srt.util;

public class Aresta {
	
	/**
	 * Classe que representa um aresta de um grafo não-direcional e valorado
	 * @author Lucas Alves da Encarnação Oliveira
	 */

	private double peso;
	private int minutos;
	private Vertice origem;
	private Vertice destino;
	private boolean visitado;
	
	/**
	 * Construtor que inicializa os atributos com os devidos valores
	 * @param origem String - nome que representa a origem
	 * @param destino String - nome que representa o destino
	 * @param minutos int - representa quantos minutos leva-se origem/destino ou vice-versa
	 * @param peso double - representa a distancia de origem/destino ou vice-versa em metros
	 */
	public Aresta(Vertice origem, Vertice destino, int minutos,double peso) {
		setOrigem(origem);
		setDestino(destino);
		setMinutos(minutos);
		setPeso(peso);
	}
	
	/** 
	 * Método que retorna se a aresta foi visitada ou não
	 * @return boolean
	 */
	public boolean isVisitado() {
		return visitado;
	}
	/**
	 * Método que seta um boolean no atributo visitado
	 * @param visitado
	 */
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
	/**
	 * Método que retorna o peso de uma aresta
	 * @return double
	 */
	public double getPeso() {
		return peso;
	}
	/**
	 * Método que seta um peso
	 * @param peso double - Representa a distância de uma aresta em metros
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}
	/**
	 * Método que seta o atributo minutos na aresta
	 * @param minutos -  Representa a distância em minutos 
	 */
	public void setMinutos(int minutos){
		this.minutos = minutos;
	}
	/**
	 * Método que retorna os minutos de uma resta
	 * @return int
	 */
	public int getMinutos(){
		return minutos;
	}
	/**
	 * Método que retorna a origem de uma determinada aresta
	 * @return Vertice - origem
	 */
	public Vertice getOrigem() {
		return origem;
	}
	/**
	 * Método que seta uma origem a uma determinada aresta
	 * @param origem Vertice
	 */
	public void setOrigem(Vertice origem) {
		
		this.origem = origem;
	}
	/**
	 * Método que retorna o destino de uma determinada aresta
	 * @return Vertice - destino
	 */
	public Vertice getDestino() {
		return destino;
	}
	/**
	 * Método que seta um vértice como destino de uma aresta
	 * @param destino Vertice
	 */
	public void setDestino(Vertice destino) {
		
		this.destino = destino;
	}
}