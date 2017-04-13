package br.uefs.ecomp.spalle.model;

/**
 * Classe que representa um Aluno
 * @author Lucas Alves da Encarna��o Oliveira
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
	 * M�todo utilizado para recuperar o Id
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * M�todo utilizado para determinar um Id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * M�todo utilizado para recuperar um Nome
	 * @return nome 
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * M�todo utilizado para determinar um Nome;
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * M�todo utilizado para recuperar a Nacionalidade
	 * @return
	 */
	public String getNacionalidade() {
		return nacionalidade;
	}
	
	/**
	 * M�todo utilizado para determinar uma Nacionalidade
	 * @param nacionalidade
	 */
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	/**
	 * M�todo utilizado para recuperar o Pa�s
	 * @return pais
	 */
	public String getPais() {
		return pais;
	}
	
	/**
	 * M�todo utilizado para determinar o Pa�s
	 * @param pais
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	/**
	 * M�todo utilizado para recuperar a cidade
	 * @return cidade
	 */
	public String getCidade() {
		return cidade;
	}
	
	/**
	 * M�todo utilizado para determina a Cidade
	 * @param cidade
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	/**
	 * M�todo utilizado para recuperar o Bairro
	 * @return
	 */
	public String getBairro() {
		return bairro;
	}
	
	/**
	 * M�todo utilizado para determinar o Bairro
	 * @param bairro
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	/**
	 * M�todo utilizado para recuperar a Rua
	 * @return rua
	 */
	public String getRua() {
		return rua;
	}
	
	/**
	 * M�todo utilizado para determinar a Rua
	 * @param rua
	 */
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	/**
	 * M�todo utilizado para recuperar o Numero da casa
	 * @return numero
	 */
	public int getNumero() {
		return numero;
	}
	
	/**
	 * M�todo utilizado para determinar o Numero da casa
	 * @param numero
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/**
	 * M�todo utilizado para recuperar o Cep
	 * @return
	 */
	public int getCep() {
		return cep;
	}
	
	/**
	 * M�todo utilizado para determinar o Cep
	 * @param cep
	 */
	public void setCep(int cep) {
		this.cep = cep;
	}
	
	/**
	 * M�todo utilizado para recuperar o Telefone
	 * @return
	 */
	public String getTelefone() {
		return telefone;
	}
	
	/**
	 * M�todo utilizado para determinar o Telefone
	 * @param telefone
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	/**
	 * M�todo utilizado para recuperar a primeira op��o de curso
	 * @return
	 */
	public String getOpcao1() {
		return opcao1;
	}
	
	/**
	 * M�todo utilizado para determinar a primeira op��o de curso
	 * @param opcao1 - Curso que sera a segunda op��o do candidato
	 */
	public void setOpcao1(String opcao1) {
		this.opcao1 = opcao1;
	}
	
	/**
	 * M�todo utilizado para recuperar a segunda op��o de curso
	 * @return opcao2
	 */
	public String getOpcao2() {
		return opcao2;
	}
	
	/**
	 * M�todo utilizado para determinar a segunda op��o de curso
	 * @param opcao2 - Curso que sera a segunda op��o do candidato
	 */
	public void setOpcao2(String opcao2) {
		this.opcao2 = opcao2;
	}
	
	/**
	 * M�todo utilizado para determinar se o aluno j� foi sorteado ou n�o na primeira op��o
	 * @param b - status true ou false
	 */
	public void setSorteado1Opcao(boolean b){
		this.sorteado1Opcao = b;
	}
	
	/**
	 * M�todo utilizado para determinar se o aluno j� foi sorteado ou n�o na segunda op��o
	 * @param b - status true ou false
	 */
	public void setSorteado2Opcao(boolean b){
		this.sorteado2Opcao = b;
	}
	
	/**
	 * M�todo utilizado para recuperar o status de sorteado ou n�o na primeira op��o
	 * @return boolean
	 */
	public boolean getSorteado1Opcao(){
		return this.sorteado1Opcao;
	}
	
	/**
	 * M�todo utilizado para recuperar o status de sorteado ou n�o na segunda op��o
	 * @return boolean
	 */
	public boolean getSorteado2Opcao(){
		return this.sorteado2Opcao;
	}
	
	/**
	 * M�todo que retorna se o aluno j� desistiu alguma vez da sua primeira op��o
	 * @return boolean
	 */
	public boolean getDesistiu1Opcao(){
		return desistiu1Opcao;
	}
	
	/**
	 * M�todo que retorna se o aluno ja desistiu alguma vez da sua segunda op��o
	 * @return boolean
	 */
	public boolean getDesistiu2Opcao(){
		return desistiu2Opcao;
	}
	
	/**
	 * M�todo que determina se o aluno desistiu da primeira op��o
	 * @param b
	 */
	public void setDesistiu1Opcao(boolean b){
		desistiu1Opcao = b;
		
	/**
	 * M�todo que determina se o aluno desistiu da segunda op��o
	 */
	}
	public void setDesistiu2Opcao(boolean b){
		desistiu2Opcao = b;
	}
	
	@Override
	
	public String toString(){
		return "Nome: " + this.nome + ", Nacionalidade: " + this.nacionalidade + " Pa�s: " + this.pais + ", Cidade: " + this.cidade  + " Bairro: " + this.bairro + ", Rua: " + this.rua + " Cep " + this.cep + ", Telefone " + this.telefone + " Primeira op��o " + this.opcao1 + ", Segunda op��o " + this.opcao2;
		
	}
	
}
