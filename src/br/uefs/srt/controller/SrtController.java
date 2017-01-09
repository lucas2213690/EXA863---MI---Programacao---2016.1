package br.uefs.srt.controller;

import java.io.IOException;
import java.util.ArrayList;
import br.uefs.srt.util.*;

public class SrtController {
	
	private static Grafo grafo;
	
	private static double km;
	
	
	
	public SrtController(){
		grafo = new Grafo();
	}
	
	/**
	 * M�todo que monta um grafo a partir de um arquivo texto estruturado
	 * @throws IOException
	 */
	public void lerGrafo() throws IOException{
		Leitura leitura = new Leitura(grafo);
		
		leitura.montarGrafo();
	}
	
	/**
	 * M�todo que retorna o menor caminho a partir de dois v�rtices
	 * @param origem - Representa o v�rtice de origem
	 * @param destino - Representa o v�rtice de destino
	 * @return ArrayList<Vertice>
	 */
	public ArrayList<Vertice> caminhoDijkstra(String origem, String destino){
		Vertice a = grafo.encontrarVertice(origem);
		
		Vertice b = grafo.encontrarVertice(destino);
	
		ArrayList<Vertice> caminho = grafo.encontrarMenorCaminhoDijkstra(a, b);
		
		return caminho;
		
	}
	/**
	 * M�todo que retorna a dist�ncia do menor caminho entre dois v�rtices
	 * @param origem - Representa o v�rtice de origem
	 * @param destino - Reresenta o v�rtice de destino
	 * @return double - dist�ncia em metros
	 */
	public double distanciaDijkstra(String origem, String destino){
		Vertice a = grafo.encontrarVertice(origem);
		
		Vertice b = grafo.encontrarVertice(destino);
	
		ArrayList<Vertice> caminho = grafo.encontrarMenorCaminhoDijkstra(a, b);
		
		return caminho.get(caminho.size() - 1).getDistancia();
	}
	
	public void setPre�oKm(double pre�o){
		this.km = pre�o;
	}

	public double getPre�oKm(){
		return this.km;
	}

	/**
	 * M�todo que calcula o pre�o da viagem
	 * @param origem - Representa o v�rtice de origem
	 * @param destino - Representa o v�rtice de destino 
	 * @return double - Pre�o em R$
	 */
	public double calculaPreco(String origem, String destino){

		double distancia;
		
		Vertice a = grafo.encontrarVertice(origem);
		
		Vertice b = grafo.encontrarVertice(destino);

		ArrayList<Vertice> caminho = grafo.encontrarMenorCaminhoDijkstra(a, b);
		
		distancia = caminho.get(caminho.size() - 1).getDistancia();

		return distancia * getPre�oKm();

	}
	
	
	/**
	 * M�todo que muda o tempo de deslocamento entre dois v�rtices
	 * @param origem - Representa o v�rtice de origem
	 * @param destino - Representa o v�rtice de destino
	 * @param tempo - o novo tempo
	 */
	public void mudarTempoDeslocamento(String origem, String destino, int tempo){
		
		Vertice a = grafo.encontrarVertice(origem);
		
		Vertice b = grafo.encontrarVertice(destino);
		
		Aresta aux = grafo.acharAresta(a, b);
		
		aux.setMinutos(tempo);
	}
	
	public ArrayList<Vertice> getVertices(){
		ArrayList<Vertice> listaVertices = grafo.getVertices();
		
		return listaVertices;
	}
	public ArrayList<Aresta> getArestas(){
		ArrayList<Aresta> listaArestas = grafo.getArestas();
		
		return listaArestas;
	}
	
}
