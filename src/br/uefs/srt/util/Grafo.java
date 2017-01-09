package br.uefs.srt.util;

import java.util.ArrayList;
import java.util.Collections;


/** Classe que representa a estrutura de dados Grafo
 * @author Lucas Alves da Encarnação Oliveira
 */

public class Grafo {

	private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
	private ArrayList<Vertice> vertices = new ArrayList<Vertice>();


	/** Método para adicionar uma aresta
	 * @param origem String - nome que representa a origem
	 * @param destino String - nome que representa o destino
	 * @param minutos int - representa quantos minutos leva-se origem/destino ou vice-versa
	 * @param peso double - representa a distancia de origem/destino ou vice-versa em metros
	 */
	public void addAresta(String origem, String destino, int minutos, double peso){
		int i,j,k;

		//adiciona vertices e retorna posicao
		i = this.addVertice(origem);
		j = this.addVertice(destino);

		//adiciona aresta na lista
		Aresta a = new Aresta(((Vertice)this.vertices.get(i)), ((Vertice)this.vertices.get(j)), minutos, peso);

		this.arestas.add(a);
		k = this.arestas.size();

		//adiciona aresta na lista de arestas incidentes em cada vertice
		this.vertices.get(i).addIncidentes(this.arestas.get(k-1));
		this.vertices.get(j).addIncidentes(this.arestas.get(k-1));
	}

	/** Método para adicionar um vértice
	 * @param nome String - nome que representa um vértice
	 * @return int - index do vertice adicionado na lista de vértice
	 */
	public int addVertice(String nome){
		int i= this.indexVertice(nome); 

		if(i==0){
			this.vertices.add(new Vertice(nome));
			return (this.vertices.size() - 1);
		}

		return i;
	}

	/** Método que limpa os vértice do pai
	 *
	 */
	public void limparVerticesPai(){
		for(int i=0; i<this.getVertices().size() ;i++)
			this.getVertices().get(i).setPai(null);
	}

	/** Método que limpa os vértice visitados
	 *
	 */
	public void limparVerticeVisitado(){
		for(int i=0; i<this.getVertices().size() ;i++)
			this.getVertices().get(i).setVisitado(false);
	}

	/** Método que limpa as arestas visitadas
	 *
	 */
	public void limparArestaVisitada(){
		for(int i=0; i<this.getArestas().size() ;i++)
			this.getArestas().get(i).setVisitado(false);
	}

	/** Método que retorna a lista de vertices
	 * @return ArrayList<Vertice> - lista de vertices
	 */
	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	/** Método que retorna o index de um vértice
	 * @param nome String - nome que representa um vértice
	 * @return int - index do determinado vértice
	 */
	public int indexVertice(String nome){
		int i;

		for (i=0; i<this.vertices.size() ; i++)
			if (this.vertices.get(i).getNome().equals(nome))
				return i;

		return 0;

	}

	public Vertice encontrarVertice(String nome){
		return this.vertices.get(this.indexVertice(nome));
	}


	/** Método que procura uma determinada aresta
	 * @param vet1 Vertice - primeiro vértice
	 * @param vet2 Vertice - segundo vértice
	 * @return Aresta - retorna a aresta que liga os dois vértices
	 */

	public Aresta acharAresta(Vertice vet1, Vertice vet2){
		for(int i=0; i<this.arestas.size();i++){
			if( ((this.arestas.get(i).getOrigem().getNome().equals(vet1.getNome())) &&
					(this.arestas.get(i).getDestino().getNome().equals(vet2.getNome()))) ||
					((this.arestas.get(i).getOrigem().getNome().equals(vet2.getNome())) &&
							(this.arestas.get(i).getDestino().getNome().equals(vet1.getNome()))) ){
				return this.arestas.get(i);
			}
		}
		return null;
	}

	public ArrayList<Aresta> getArestas() {
		return arestas;
	}

	/** Método que é a implementação do algoritmo de Dijkstra para um grafo representado por lista de adjacência
	 * @param v1 Vertice - Vértice que representa a origem
	 * @param v2 Vertice - Vértice que representa o destino
	 * @return ArrayList<Vertice> -  Uma lista de vértice com todo o caminho mais curto 
	 */
	public ArrayList<Vertice> encontrarMenorCaminhoDijkstra(Vertice v1,Vertice v2) {

		ArrayList<Vertice> menorCaminho = new ArrayList<Vertice>();
		Vertice verticeCaminho;
		Vertice atual;
		Vertice vizinho;
		Aresta ligacao;
		ArrayList<Vertice> naoVisitados = new ArrayList<Vertice>();

		// Primeiro adiciona a origem na lista de menorCaminho
		menorCaminho.add(v1);

		// Inicializa a distância
		for (int i = 0; i < this.getVertices().size(); i++) {
			if (this.getVertices().get(i).getNome().equals(v1.getNome()))
				this.getVertices().get(i).setTempo(0);
			else
				this.getVertices().get(i).setTempo(Integer.MAX_VALUE);
			naoVisitados.add(this.getVertices().get(i));
		}

		Collections.sort(naoVisitados);

		// O algoritmo continua ate que todos os vertices sejam visitados
		while (!naoVisitados.isEmpty()) {

			atual = naoVisitados.get(0);
			/*
			 * Para cada vizinho, compara-se a sua possivel
			 * distancia, somando a distancia do vertice atual com a da aresta
			 * correspondente. Se essa distancia for menor que a distancia do
			 * vizinho, esta é atualizada.
			 */
			for (int i = 0; i < atual.getVizinhos().size(); i++) {

				vizinho = atual.getVizinhos().get(i);

				if (!vizinho.isVisitado()) {

					// Comparando a distância
					ligacao = this.acharAresta(atual,vizinho);
					if (vizinho.getTempo() > (atual.getTempo() + ligacao.getMinutos())) {
						vizinho.setTempo(atual.getTempo() + ligacao.getMinutos());
						vizinho.setDistancia(atual.getDistancia() + ligacao.getPeso());
						vizinho.setPai(atual);

						/*
						 * Se o vizinho é o vertice procurado, e foi feita uma
						 * mudanca na distancia, a lista com o menor caminho
						 * anterior eh apagada, pois existe um caminho menor
						 * vertices pais, até o vertice origem.
						 */
						if (vizinho == v2) {
							menorCaminho.clear();
							verticeCaminho = vizinho;
							menorCaminho.add(vizinho);
							while (verticeCaminho.getPai() != null) {
								menorCaminho.add(verticeCaminho.getPai());
								verticeCaminho = verticeCaminho.getPai();

							}
							// Ordena a lista para exibir na ordem
							Collections.sort(menorCaminho);

						}
					}
				}

			}
			// Seta vertice atual como visitado e o remove da lista de nao visitados
			atual.setVisitado(true);
			naoVisitados.remove(atual);

			Collections.sort(naoVisitados);

		}
		this.limparVerticesPai();
		return menorCaminho;
	}    
}


