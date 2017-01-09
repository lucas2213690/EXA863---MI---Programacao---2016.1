package br.uefs.sgad.util;

public class Lista implements ILista{

	private Celula primeira;

	private Celula ultima;

	private int total;

	public Lista(){
		this.primeira = null;

		this.ultima = null;

		total = 0;

	}

	/** 
	 *  M�todo que verifica se a lista est� vazia
	 *  @return boolean - true se estiver vazia, false se n�o estiver vazia
	 */

	public boolean estaVazia(){
		return primeira == null;		
	}

	/** 
	 * M�todo que retorna o tamanho da lista
	 * @return total - Valor do Tamanho
	 * 
	 */

	public int obterTamanho(){
		return total;
	}

	/**
	 * M�todo que insere uma Celula no �nicio da Lista
	 * @param elemento - Elemento que ser� inserido
	 */

	public void inserirInicio(Object elemento){

		Celula nova = new Celula(primeira, elemento);

		this.primeira = nova;



		if (this.total == 0){
			this.ultima = this.primeira;
		}

		this.total++;
	}

	/**
	 * M�todo que insere uma Celula no final da Lista
	 * @param elemento - Elemento que ser� inserido
	 */

	public void inserirFinal(Object elemento){

		if (total == 0){
			this.inserirInicio(elemento);
		} else {
			Celula nova = new Celula(elemento);
			this.ultima.setProxima(nova);
			this.ultima = nova;
			this.total++;
		}
	}

	/**
	 * M�todo que insere uma Celula numa determinada posi��o
	 * @param index - Posi��o em que este elemento ser� inserido
	 * @param elemento - Elemento que ser� inserido
	 */

	public void inserir(int index, Object elemento){

		Celula nova = new Celula(elemento);
		Celula temp = primeira;
		Celula anterior = null;

		int posicao = 0;
		int tamanho = this.total;

		if (temp == null)
			primeira = nova;

		if (index <= tamanho && index > -1){

			while (posicao != index){
				anterior = temp;
				temp = temp.getProxima();
				posicao++;
			}
		}

	}

	/**
	 * M�todo que recupera um objeto da lista
	 * @param index - Posi��o na qual ser� recuperado o objeto
	 * @return atual.getElemento(); - Objeto recuperado
	 */

	public Object recuperar(int index){

		Celula atual = primeira;
		if (index >= 0 && index < total ){
			for(int i = 0; i < index; i++){
				atual = atual.getProxima();
			}
		}
		return atual.getElemento();
	}

	/**
	 * M�todo que remove o primeiro elemento
	 * @return aux - Primeiro elemento
	 */

	public Object removerInicio(){

		Celula aux = this.primeira;
		this.primeira = this.primeira.getProxima();
		this.total--;

		if (this.total == 0)
			this.ultima = null;

		return aux;
	}

	/**
	 * M�todo que remove o ultimo elemento
	 * @return ultima - �ltimo elemento
	 */

	public Object removerFinal(){
		Celula aux;

		if (total == 1){
			primeira = null;
			ultima = null;
		}
		else if(total == 0){
			System.out.println("N�o h� elementos na lista");
		}
		else{
			aux = primeira;
			for(int i=1; i<this.total-1; i++)
				aux = aux.getProxima();
			ultima = aux;
		}
		return ultima;
	}


	public Object remover(int index){
		int i = 0;
		Celula anterior = primeira;
		Celula atual = primeira;
		while (i != index){
			if (atual.getProxima() == null)
				return null;
			else{
				anterior = atual;
				atual = atual.getProxima();
			}
		}
		if (atual == primeira)
			primeira = primeira.getProxima();
		else
			anterior.setProxima(atual.getProxima());
		total--;
		return atual.getElemento();
	}


	public void swap(int i, int j){
		Object elementoI = recuperar(i);
		Object elementoJ = recuperar(j);
		Celula aux = primeira;
		for(int a = 0; a < i; a++){
			aux = aux.getProxima(); 
		}
		aux.setElemento(elementoJ);

		aux = primeira;

		for(int a = 0; a < j; a++){
			aux = aux.getProxima();
		}

		aux.setElemento(elementoI);
	}

	/**
	 * M�todo que retorna o iterador
	 * @return iterador - Retorma o iterador recebendo o primeiro elemento da lista	
	 */
	public Iterador iterador(){

		Iterador iterator = new Iterator(this.primeira);
		return iterator;
	}








}