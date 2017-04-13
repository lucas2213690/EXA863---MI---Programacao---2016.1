package br.uefs.ecomp.spalle.model;

/**
 * Classe que representa um Aluno
 * @author Lucas Alves da Encarnação Oliveira
 *
 */

public class Aluno {
	private int id;
	private String nome;
	private String nacionalidade;
	private String pais;
	private String cidade;
	private String bairro;
	private String rua;
	private int numero;
	private int cep;
	private String telefone;
	private String opcao1;
	private String opcao2;
	private boolean sorteado1Opcao;
	private boolean sorteado2Opcao;
	private boolean desistiu1Opcao;
	private boolean desistiu2Opcao;
	
	/**
	 * Método utilizado para recuperar o Id
	 * @return id
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
	 * Método utilizado para recuperar um Nome
	 * @return nome 
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Método utilizado para determinar um Nome;
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Método utilizado para recuperar a Nacionalidade
	 * @return
	 */
	public String getNacionalidade() {
		return nacionalidade;
	}
	
	/**
	 * Método utilizado para determinar uma Nacionalidade
	 * @param nacionalidade
	 */
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	/**
	 * Método utilizado para recuperar o País
	 * @return pais
	 */
	public String getPais() {
		return pais;
	}
	
	/**
	 * Método utilizado para determinar o País
	 * @param pais
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	/**
	 * Método utilizado para recuperar a cidade
	 * @return cidade
	 */
	public String getCidade() {
		return cidade;
	}
	
	/**
	 * Método utilizado para determina a Cidade
	 * @param cidade
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	/**
	 * Método utilizado para recuperar o Bairro
	 * @return
	 */
	public String getBairro() {
		return bairro;
	}
	
	/**
	 * Método utilizado para determinar o Bairro
	 * @param bairro
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	/**
	 * Método utilizado para recuperar a Rua
	 * @return rua
	 */
	public String getRua() {
		return rua;
	}
	
	/**
	 * Método utilizado para determinar a Rua
	 * @param rua
	 */
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	/**
	 * Método utilizado para recuperar o Numero da casa
	 * @return numero
	 */
	public int getNumero() {
		return numero;
	}
	
	/**
	 * Método utilizado para determinar o Numero da casa
	 * @param numero
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/**
	 * Método utilizado para recuperar o Cep
	 * @return
	 */
	public int getCep() {
		return cep;
	}
	
	/**
	 * Método utilizado para determinar o Cep
	 * @param cep
	 */
	public void setCep(int cep) {
		this.cep = cep;
	}
	
	/**
	 * Método utilizado para recuperar o Telefone
	 * @return
	 */
	public String getTelefone() {
		return telefone;
	}
	
	/**
	 * Método utilizado para determinar o Telefone
	 * @param telefone
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	/**
	 * Método utilizado para recuperar a primeira opção de curso
	 * @return
	 */
	public String getOpcao1() {
		return opcao1;
	}
	
	/**
	 * Método utilizado para determinar a primeira opção de curso
	 * @param opcao1 - Curso que sera a segunda opção do candidato
	 */
	public void setOpcao1(String opcao1) {
		this.opcao1 = opcao1;
	}
	
	/**
	 * Método utilizado para recuperar a segunda opção de curso
	 * @return opcao2
	 */
	public String getOpcao2() {
		return opcao2;
	}
	
	/**
	 * Método utilizado para determinar a segunda opção de curso
	 * @param opcao2 - Curso que sera a segunda opção do candidato
	 */
	public void setOpcao2(String opcao2) {
		this.opcao2 = opcao2;
	}
	
	/**
	 * Método utilizado para determinar se o aluno já foi sorteado ou não na primeira opção
	 * @param b - status true ou false
	 */
	public void setSorteado1Opcao(boolean b){
		this.sorteado1Opcao = b;
	}
	
	/**
	 * Método utilizado para determinar se o aluno já foi sorteado ou não na segunda opção
	 * @param b - status true ou false
	 */
	public void setSorteado2Opcao(boolean b){
		this.sorteado2Opcao = b;
	}
	
	/**
	 * Método utilizado para recuperar o status de sorteado ou não na primeira opção
	 * @return boolean
	 */
	public boolean getSorteado1Opcao(){
		return this.sorteado1Opcao;
	}
	
	/**
	 * Método utilizado para recuperar o status de sorteado ou não na segunda opção
	 * @return boolean
	 */
	public boolean getSorteado2Opcao(){
		return this.sorteado2Opcao;
	}
	
	/**
	 * Método que retorna se o aluno já desistiu alguma vez da sua primeira opção
	 * @return boolean
	 */
	public boolean getDesistiu1Opcao(){
		return desistiu1Opcao;
	}
	
	/**
	 * Método que retorna se o aluno ja desistiu alguma vez da sua segunda opção
	 * @return boolean
	 */
	public boolean getDesistiu2Opcao(){
		return desistiu2Opcao;
	}
	
	/**
	 * Método que determina se o aluno desistiu da primeira opção
	 * @param b
	 */
	public void setDesistiu1Opcao(boolean b){
		desistiu1Opcao = b;
		
	/**
	 * Método que determina se o aluno desistiu da segunda opção
	 */
	}
	public void setDesistiu2Opcao(boolean b){
		desistiu2Opcao = b;
	}
	
	@Override
	
	public String toString(){
		return "Nome: " + this.nome + ", Nacionalidade: " + this.nacionalidade + " País: " + this.pais + ", Cidade: " + this.cidade  + " Bairro: " + this.bairro + ", Rua: " + this.rua + " Cep " + this.cep + ", Telefone " + this.telefone + " Primeira opção " + this.opcao1 + ", Segunda opção " + this.opcao2;
		
	}
	
}
