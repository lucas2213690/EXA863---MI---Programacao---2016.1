package br.uefs.srt.controller;

import javax.json.Json;


import javax.json.JsonObjectBuilder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Método que retorna um JSON estruturado com os dados de uma viagem
 * @author Lucas Alves da Encarnação Oliveira
 *
 */

@WebServlet("/dadosViagem")
public class DadosViagem extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		PrintWriter out = response.getWriter();
		
		String origem = request.getParameter("origem");
		
		String destino = request.getParameter("destino");
		
		SrtController controller = new SrtController();
		
		
		
		JsonObjectBuilder json = Json.createObjectBuilder();
		
		double preco = controller.calculaPreco(origem, destino);
		
		double distancia = controller.distanciaDijkstra(origem, destino);
		
		json.add("preco", preco);
		
		json.add("distancia", distancia);
		
		out.println(json.build());
		
		
		
	}

}
