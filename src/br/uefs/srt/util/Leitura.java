package br.uefs.srt.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe responsável pela leitura de um arquivo texto que contêm as informações
 * necessárias para montar o grafo
 * @author lucas
 *
 */

public class Leitura{
	
	private Grafo grafo;
	private String verticeAtual;
	private boolean ehVertice;
	private BufferedReader reader;
	
	//Recebe a matriz que será preenchida
	public Leitura(Grafo grafo){
		setEhVertice(true);
		this.grafo = grafo;
	}
	
	public String getVerticeAtual(){
		return verticeAtual;
	}
	
	public void setVerticeAtual(String vertice){
		verticeAtual = vertice;
	}
	
	public boolean getEhVertice(){
		return ehVertice;
	}
	
	public void setEhVertice(boolean b){
		ehVertice = b;
		
	}
	
	
	
	
	/**
	 * Método responsável por montar o grafo 
	 * @throws IOException
	 */
	public void montarGrafo() throws IOException{
		
		int totalLinhas = 0;
		
		int linhasConexoes = 0;
		
		int totalBairros = 0;
		
		int totalConexoes = 0;
	
		reader = new BufferedReader(new FileReader("src/br/uefs/srt/util/data.txt"));
		String line;
		while ((line = reader.readLine()) != null)
		{
			
			if (linhasConexoes == 13){
				setEhVertice(true);
				linhasConexoes = 0;
			}
			
			if (totalLinhas == 0){
				//totalBairros =  Integer.parseInt(line);
				System.out.println(totalBairros);
			}
			else if (totalLinhas == 1){
				totalConexoes = Integer.parseInt(line);
			}
			else if (getEhVertice()){
				setVerticeAtual(line);
				//ehVertice = false;
				setEhVertice(false);
				linhasConexoes++;
			}
			else{
				lerAresta(line);
				linhasConexoes++;
			}
			totalLinhas++;
		}
		reader.close();
	}
		
	private void lerAresta(String linha){
		
		String destino;
		String distancia;
		String minutos;
		int minutosInt = 0;
		double distanciaFloat = 0.0;
		
		String[] aux = linha.split("#");
		
		destino = aux[0];
		
		distancia = aux[1];
		
		distanciaFloat = Double.parseDouble(distancia);
		
		minutos = aux[2];
		
		minutosInt = Integer.parseInt(minutos);
		
		grafo.addAresta(verticeAtual, destino, minutosInt, distanciaFloat);
	}
}
