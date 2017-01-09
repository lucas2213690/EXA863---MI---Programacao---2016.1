package br.uefs.srt.controller;

import javax.json.Json;


import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.servlet.annotation.WebServlet;
import br.uefs.srt.util.Vertice;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Classe que retorna um JSON com o menor caminho
 * @author Lucas Alves da Encarnação Oliveira
 *
 */

@WebServlet("/caminhoDijkstra")
public class Dijkstra extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		PrintWriter out = response.getWriter();
		
		SrtController controller = new SrtController();
		
		String origem = request.getParameter("origem");
		
		String destino = request.getParameter("destino");
			
		ArrayList<Vertice> caminho = controller.caminhoDijkstra(origem, destino);
		
		JsonArrayBuilder jsonArray =  Json.createArrayBuilder();
		for(int i = 0;i<caminho.size(); i++){
			Vertice aux = caminho.get(i);
			jsonArray.add(aux.getNome());
		}
		
		
		
		JsonArray caminhoCurto = jsonArray.build();
		
		out.println(caminhoCurto);
		
		
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		SrtController controller = new SrtController();
	
		try {
			controller.lerGrafo();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	

}
