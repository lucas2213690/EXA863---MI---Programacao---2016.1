package br.uefs.srt.controller;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.uefs.srt.util.Vertice;
import br.uefs.srt.util.Aresta;
import javax.json.JsonObject;
import javax.json.Json;

import javax.json.JsonObjectBuilder;
import javax.json.JsonArrayBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Class que retorna todos os vértices e arestas do Grafo no formato JSON
 * @author Lucas Alves da Encarnação Oliveira
 *
 */


public class EstruturaGrafo extends HttpServlet {
	
	
	private static final long serialVersionUID = 586871104955335931L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		PrintWriter out = response.getWriter();
		
		SrtController controller = new SrtController();
		
		ArrayList<Vertice> vertice = controller.getVertices();
		
		ArrayList<Aresta> aresta = controller.getArestas();
		
		JsonObjectBuilder estruturaGrafo = Json.createObjectBuilder();
		JsonObjectBuilder verticeBuilder = Json.createObjectBuilder();
		JsonObjectBuilder arestaBuilder = Json.createObjectBuilder();
		JsonArrayBuilder arrayVertice = Json.createArrayBuilder();
		JsonArrayBuilder arrayAresta = Json.createArrayBuilder();
		
		for(int i = 0; i < vertice.size(); i++){
			verticeBuilder.add("id",vertice.get(i).getNome());
			arrayVertice.add(verticeBuilder);

		}
		
		estruturaGrafo.add("nodes", arrayVertice);
		
		
		
		for(int i = 0; i < aresta.size(); i++){
			arestaBuilder.add("origem", aresta.get(i).getOrigem().getNome()).add("destino", aresta.get(i).getDestino().getNome()).add("peso", aresta.get(i).getPeso());
			arrayAresta.add(arestaBuilder);
		}
		
		estruturaGrafo.add("links", arrayAresta);
		
		JsonObject json = estruturaGrafo.build();
		
		out.println(json);
		
		
		
		
		
		
	
		
		
		
	}

}
