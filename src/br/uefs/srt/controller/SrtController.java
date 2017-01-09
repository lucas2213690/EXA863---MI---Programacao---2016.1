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
	 * Método que monta um grafo a partir de um arquivo texto estruturado
	 * @throws IOException
	 */
	public void lerGrafo() throws IOException{
		Leitura leitura = new Leitura(grafo);
		
		leitura.montarGrafo();
	}
	
	/**
	 * Método que retorna o menor caminho a partir de dois vértices
	 * @param origem - Representa o vértice de origem
	 * @param destino - Representa o vértice de destino
	 * @return ArrayList<Vertice>
	 */
	public ArrayList<Vertice> caminhoDijkstra(String origem, String destino){
		Vertice a = grafo.encontrarVertice(origem);
		
		Vertice b = grafo.encontrarVertice(destino);
	
		ArrayList<Vertice> caminho = grafo.encontrarMenorCaminhoDijkstra(a, b);
		
		return caminho;
		
	}
	/**
	 * Método que retorna a distância do menor caminho entre dois vértices
	 * @param origem - Representa o vértice de origem
	 * @param destino - Reresenta o vértice de destino
	 * @return double - distãncia em metros
	 */
	public double distanciaDijkstra(String origem, String destino){
		Vertice a = grafo.encontrarVertice(origem);
		
		Vertice b = grafo.encontrarVertice(destino);
	
		ArrayList<Vertice> caminho = grafo.encontrarMenorCaminhoDijkstra(a, b);
		
		return caminho.get(caminho.size() - 1).getDistancia();
	}
	
	public void setPreçoKm(double preço){
		this.km = preço;
	}

	public double getPreçoKm(){
		return this.km;
	}

	/**
	 * Método que calcula o preço da viagem
	 * @param origem - Representa o vértice de origem
	 * @param destino - Representa o vértice de destino 
	 * @return double - Preço em R$
	 */
	public double calculaPreco(String origem, String destino){

		double distancia;
		
		Vertice a = grafo.encontrarVertice(origem);
		
		Vertice b = grafo.encontrarVertice(destino);

		ArrayList<Vertice> caminho = grafo.encontrarMenorCaminhoDijkstra(a, b);
		
		distancia = caminho.get(caminho.size() - 1).getDistancia();

		return distancia * getPreçoKm();

	}
	
	
	/**
	 * Método que muda o tempo de deslocamento entre dois vértices
	 * @param origem - Representa o vértice de origem
	 * @param destino - Representa o vértice de destino
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
