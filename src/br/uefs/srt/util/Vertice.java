package br.uefs.srt.util;

import java.util.ArrayList;

	/**
	 * Classe que representa um vértice de um grafo não-direcional e valorado
	 * @author Lucas Alves da Encarnação Oliveira
	 */

public class Vertice implements Comparable<Vertice> {
	private String nome;
	private Vertice pai;
	private double distancia;
	private int tempo;
	private ArrayList<Aresta> incidentes = new ArrayList<Aresta>();
	private ArrayList<Vertice> vizinhos = new ArrayList<Vertice>();
	private boolean visitado; 
	
	public Vertice(String nome){
		this.setNome(nome);
	}
	
	public int getTempo(){
		return tempo;
	}
	
	public void setTempo(int tempo){
		this.tempo = tempo;
	}
	
	public double getDistancia(){
		return distancia;
	}
	
	public void setDistancia(double distancia){
		this.distancia = distancia;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Vertice getPai() {
		return pai;
	}
	
	public void setPai(Vertice pai) {
		this.pai = pai;
	}
	
	public boolean isVisitado() {
		return visitado;
	}
	
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public ArrayList<Aresta> getIncidentes() {
		return incidentes;
	}
	
	public void addIncidentes(Aresta incide) {
		this.incidentes.add(incide);
		
		//adicionando vizinhos a lista
		if ( (incide.getOrigem().getNome().equals(this.getNome())) &&
				(!this.isVizinho(incide.getDestino())) ){
			
			this.addVizinhos(incide.getDestino());
			
		}else if ( (incide.getDestino().getNome().equals(this.getNome())) &&
				(!this.isVizinho(incide.getOrigem())) ){
			
			this.addVizinhos(incide.getOrigem());
		}
	}
	
	public void addVizinhos(Vertice vizinho) {
		this.vizinhos.add(vizinho);
	}

	public ArrayList<Vertice> getVizinhos() {
		return vizinhos;
	}
	
	public boolean isVizinho(Vertice vizinho){
		int i;
		
		for (i=0; i<this.vizinhos.size() ; i++)
			if (this.vizinhos.get(i).getNome().equals(vizinho.getNome()))
				return true;		
		
		return false;
	}
	

	@Override
	public int compareTo(Vertice vertice) {
		
        if(this.getTempo() < vertice.getTempo()) 
        	return -1;
        else if(this.getTempo() == vertice.getTempo()) 
        	return 0;

        return 1;
		
	} 
}
