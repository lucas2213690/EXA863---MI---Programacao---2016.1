package br.uefs.sgad.controller;

import java.io.*;
import br.uefs.sgad.util.*;

public class SgadController {

	/*  Referência para a estrutura de dados Árvore	*/
	private Arvore arvore =  new Arvore();


	/**
	 * Método que recebe o caminho de um diretório  
	 * cria a raiz da Árvore e percorre todas as sub-pastas
	 * 
	 * @author Lucas Alves da Encarnação Oliveira
	 * @param root
	 * @throws NaoEhPastaException - Exceção lançada quando o usuário informa um arquivo ao inves de um diretório
	 */

	public Fila montarArvore(String root) throws NaoEhPastaException, PastaNaoEncontradaException{


		Fila caminho = new Fila();
		/*Monta o file com a pasta root*/
		File file = new File(root);
		/*
		 * Se for diretorio e existir no SO chama percorreDiretorio()
		 * Senão lança a exceção NaoEhPastaException ou PastaNaoEncontradaException
		 */
		if (file.isFile()){
			throw new NaoEhPastaException("Nao é pasta", file.getAbsolutePath());
		}
		else if (!file.exists()){
			throw new PastaNaoEncontradaException("A pasta nao foi encontrada", file.getAbsolutePath());
		}
		else if (file.isDirectory()){
			arvore.addChild(null, file.getName() + "\\");
			percorreDiretorio(file, arvore.getRoot());

		}

		Iterador itr = arvore.iterador();

		Element a = null;
		while (itr.temProximo()){
			a = (Element) itr.obterProximo();
			caminho.inserirFinal(a.getData());
		}


		return caminho;


	}

	/**
	 * Método auxiliar que percorre todas as sub-pastas dada uma raiz
	 * de forma recursiva
	 * 
	 * @author Lucas Alves da Encarnação Oliveira
	 * @param  pai Element- Elemento que será adicionado os filhos
	 * @param path File - Representa um fike
	 */

	private void percorreDiretorio(File path, Element pai){

		Element o;
		File[] arquivos = path.listFiles();
		for (File file: arquivos){
			if (file.isFile())
				o = arvore.addChild(pai, file.getName());
			if (file.isDirectory()){
				/* Adiciona uma barra se for pasta */
				o = arvore.addChild(pai, file.getName() + "\\");
				/* Chama o método recursivamente */
				percorreDiretorio(file, o);
			}
		}
	}

	/**
	 * Método que recebe o nome de um arquivo e a profundidade máxima , e retorna uma Fila com os caminhos respectivos
	 * @author Lucas Alves da Encarnação Oliveira
	 * @param nomeArquivo String - Nome completo do arquivo que se deseja procurar
	 * @param profundidade  int -  Profundidade máxima de busca
	 * @return Fila -  Uma fila com o(s) caminho(s) completo(s) de todas as correspôndencias
	 * @throws ArquivoNaoEncontradoException - Exceção lançada quando não se encontra o arquivo(s) desejado
	 */
	public Fila procuraArquivo(String nomeArquivo, int profundidade) throws ArquivoNaoEncontradoException{

		String endereco = null;
		Element aux = null;
		String data = null;
		Fila caminhos = new Fila();

		Iterador itr = arvore.iterador();

		if (profundidade == 0){

			while (itr.temProximo()){
				aux = (Element) itr.obterProximo();
				data = (String) aux.getData();


				if (data.equals(nomeArquivo) && !data.contains("\\")){
					/* Monta o caminho completo e insere na Fila */
					endereco = montaEndereco(aux);
					caminhos.inserirFinal(endereco);
				}

			}

			if (caminhos.estaVazia()){
				throw new ArquivoNaoEncontradoException();
			}

		}

		else{

			while (itr.temProximo()){
				aux = (Element) itr.obterProximo();
				data = (String) aux.getData();


				if (arvore.depth(aux) <= profundidade && data.equals(nomeArquivo) && !data.contains("\\")){
					/* Monta o caminho completo e insere na Fila */
					endereco = montaEndereco(aux);
					caminhos.inserirFinal(endereco);
				}

			}

			if (caminhos.estaVazia()){
				throw new ArquivoNaoEncontradoException();
			}

		}

		return caminhos;
	}

	/**
	 * Método que recebe o nome de uma pasta e a profundidade máxima , e retorna uma Fila com os caminhos respectivos
	 * @author Lucas Alves da Encarnação Oliveira
	 * @param nomePasta  String -  Nome do diretório que se deseja procurar
	 * @param profundidade int -  Profundidade máxima de busca
	 * @return Fila -  Uma fila com o(s) caminho(s) completo(s) de todas as correspôndencias
	 * @throws PastaNaoEncontradaException - Exceção lançada quando não se encontra o diretório(s) desejado
	 */

	public Fila procuraPasta(String nomePasta, int profundidade) throws PastaNaoEncontradaException{

		String endereco = null;
		Element aux = null;
		String data = null;
		Fila caminhos = new Fila();

		Iterador itr = arvore.iterador();

		if (profundidade == 0){

			while (itr.temProximo()){
				aux = (Element) itr.obterProximo();
				data = (String) aux.getData();

				if (data.equals(nomePasta) && data.contains("\\")){
					/* Monta o caminho completo e insere na Fila */
					endereco = montaEndereco(aux);
					caminhos.inserirFinal(endereco);
				}

			}

			if (caminhos.estaVazia()){
				throw new PastaNaoEncontradaException();
			}


		}

		else{

			while (itr.temProximo()){
				aux = (Element) itr.obterProximo();
				data = (String) aux.getData();

				if (arvore.depth(aux) <= profundidade && data.equals(nomePasta) && data.contains("\\")){
					/* Monta o caminho completo e insere na Fila */
					endereco = montaEndereco(aux);
					caminhos.inserirFinal(endereco);
				}

			}

			if (caminhos.estaVazia()){
				throw new PastaNaoEncontradaException();
			}
		}

		return caminhos;
	}

	/**
	 * Esse método recebe uma extensão de arquivo e a profundidade máxima, e retorna uma Fila com os caminhos respectivos 
	 * @author Lucas Alves da Encarnação Oliveira
	 * @param tipoArquivo String - Extensão de arquivo que se deseja procurar
	 * @param profundidade int -  Profundidade máxima de busca
	 * @return Fila -  Uma fila com o(s) caminho(s) completo(s) de todas as correspôndencias
	 * @throws TipoNaoEncontradoException - Exceção lançada quando não se encontra nenhum arquivo do tipo desejado
	 */
	public Fila procuraExtensao(String tipoArquivo, int profundidade) throws TipoNaoEncontradoException{

		String endereco = null;

		Element aux = null;

		String data = null;

		Fila caminhos = new Fila();

		Iterador itr = arvore.iterador();

		if (profundidade == 0){

			while (itr.temProximo()){
				aux = (Element) itr.obterProximo();
				data = (String) aux.getData();


				if (!data.contains("\\") && data.contains("." + tipoArquivo)){

					if (arvore.depth(aux) <= profundidade){
						/* Monta o caminho completo e insere na Fila */
						endereco = montaEndereco(aux);
						caminhos.inserirFinal(endereco);
					}
				}
			}

			if (caminhos.estaVazia()){
				throw new TipoNaoEncontradoException();
			}

		}

		else{

			while (itr.temProximo()){
				aux = (Element) itr.obterProximo();
				data = (String) aux.getData();


				if (!data.contains("\\") && data.contains("." + tipoArquivo)){

					if (arvore.depth(aux) <= profundidade){
						/* Monta o caminho completo e insere na Fila */
						endereco = montaEndereco(aux);
						caminhos.inserirFinal(endereco);
					}
				}
			}

			if (caminhos.estaVazia()){
				throw new TipoNaoEncontradoException();
			}
		}

		return caminhos;
	}

	/**
	 * Método que recebe o caminho de uma pasta o nome do arquivo que será gerado, e cria um arquivo com todas as sub-pastas e arquivos contidos
	 * na pasta informada pelo usuário
	 * @author Lucas Alves da Encarnação Oliveira
	 * @param nome String
	 * @param nomeArquivo String - Nome do arquivo que sera gerado
	 */

	public String gerarArquivo(String root, String nomeArquivo, String path){

		String msg = null;

		String caminho = null;

		Iterador itr = null;

		Element aux = null;

		/* Split pelo pela expressão regular - */
		String [] nomeSplit = root.split("-");

		int cont = 0;

		while (cont < nomeSplit.length){
			aux = arvore.find(nomeSplit[cont]);
			cont++;					
		}

		itr = arvore.iterador(aux);


		try {
			FileWriter writer = new FileWriter(path + nomeArquivo + ".txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);


			while (itr.temProximo()){
				aux = (Element) itr.obterProximo();

				caminho = montaEndereco(aux);
				bufferedWriter.write(caminho);
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
		} 
		catch (IOException e) {
			msg = "Houve problemas na geração do arquivo";
		}

		msg =  "Arquivo gerado com Sucesso";
		return msg;

	}


	/**
	 * Método auxiliar que monta o caminho de um determinado arquivo/pasta de forma recursiva
	 * @author Lucas Alves da Encarnação Oliveira
	 * @param v Element - Diretório\arquivo ínserido na árvore
	 * @return String - Caminho completo de um diretório\arquivo
	 */

	private String montaEndereco(Element v){
		if (v == arvore.getRoot()){
			return (String) v.getData();
		}
		else{
			NoArvore a = (NoArvore) v;	
			return montaEndereco(a.getParent()) + (String)a.getData();
		}
	}
}
