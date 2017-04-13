package br.uefs.ecomp.spalle.model;

/**
 * Classe que representa um curso
 * @author Lucas Alves da Encarnação Oliveira
 *
 */

public class Curso {
	private int id;
	private String nome;
	private int qtdVagas;
	private int qtdVagasRestante;
	
	public Curso(){
	}
	
	/**
	 * Método utilizado para retornar o Id
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Método utilizado para determinar um Id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Método utilizado para retornar o Nome
	 * @return
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Método utilizado para determinar um Nome
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Método que retorna a quantidade de vagas
	 * @return
	 */
	public int getQtdVagas() {
		return qtdVagas;
	}
	
	/**
	 * Método que determina a quantidade de vagas de um curso
	 * @param qtdVagas
	 */
	public void setQtdVagas(int vagas) {
		qtdVagas = vagas;
	}
	
	/**
	 * Método que inicialmente recebe a QtdVagas afim de controlar as vagas restante em um curso
	 * @param vagas - QtdVagas
	 */
	
	
	public void setQtdVagasRestante(int vagas){
		qtdVagasRestante = vagas; 
		
	}
	
	/**
	 * Método que retorna a quantidade de vagas restante de um curso
	 * @return
	 */
	
	
	public int getQtdVagasRestante(){
		return qtdVagasRestante;
	}
	
	/**
	 * Método que decrementa a quantidade de vagas restante, quando elas são ocupadas
	 */
	
	public void decrementaVaga(){
		qtdVagasRestante--; 
	}
	
	@Override
	
	public String toString(){
		return "Nome: " + this.nome + ", ID: " + this.id + ", Quantidade de Vagas " + this.qtdVagas + "Quantidade de Vagas Restante" + this.qtdVagasRestante;
	}
		
	
}


