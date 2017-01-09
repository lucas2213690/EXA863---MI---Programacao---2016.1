package br.uefs.sgad.controller;

import java.io.*;
import br.uefs.sgad.util.*;

public class SgadController {

	/*  Refer�ncia para a estrutura de dados �rvore	*/
	private Arvore arvore =  new Arvore();


	/**
	 * M�todo que recebe o caminho de um diret�rio  
	 * cria a raiz da �rvore e percorre todas as sub-pastas
	 * 
	 * @author Lucas Alves da Encarna��o Oliveira
	 * @param root
	 * @throws NaoEhPastaException - Exce��o lan�ada quando o usu�rio informa um arquivo ao inves de um diret�rio
	 */

	public Fila montarArvore(String root) throws NaoEhPastaException, PastaNaoEncontradaException{


		Fila caminho = new Fila();
		/*Monta o file com a pasta root*/
		File file = new File(root);
		/*
		 * Se for diretorio e existir no SO chama percorreDiretorio()
		 * Sen�o lan�a a exce��o NaoEhPastaException ou PastaNaoEncontradaException
		 */
		if (file.isFile()){
			throw new NaoEhPastaException("Nao � pasta", file.getAbsolutePath());
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
	 * M�todo auxiliar que percorre todas as sub-pastas dada uma raiz
	 * de forma recursiva
	 * 
	 * @author Lucas Alves da Encarna��o Oliveira
	 * @param  pai Element- Elemento que ser� adicionado os filhos
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
				/* Chama o m�todo recursivamente */
				percorreDiretorio(file, o);
			}
		}
	}

	/**
	 * M�todo que recebe o nome de um arquivo e a profundidade m�xima , e retorna uma Fila com os caminhos respectivos
	 * @author Lucas Alves da Encarna��o Oliveira
	 * @param nomeArquivo String - Nome completo do arquivo que se deseja procurar
	 * @param profundidade  int -  Profundidade m�xima de busca
	 * @return Fila -  Uma fila com o(s) caminho(s) completo(s) de todas as corresp�ndencias
	 * @throws ArquivoNaoEncontradoException - Exce��o lan�ada quando n�o se encontra o arquivo(s) desejado
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
	 * M�todo que recebe o nome de uma pasta e a profundidade m�xima , e retorna uma Fila com os caminhos respectivos
	 * @author Lucas Alves da Encarna��o Oliveira
	 * @param nomePasta  String -  Nome do diret�rio que se deseja procurar
	 * @param profundidade int -  Profundidade m�xima de busca
	 * @return Fila -  Uma fila com o(s) caminho(s) completo(s) de todas as corresp�ndencias
	 * @throws PastaNaoEncontradaException - Exce��o lan�ada quando n�o se encontra o diret�rio(s) desejado
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
	 * Esse m�todo recebe uma extens�o de arquivo e a profundidade m�xima, e retorna uma Fila com os caminhos respectivos 
	 * @author Lucas Alves da Encarna��o Oliveira
	 * @param tipoArquivo String - Extens�o de arquivo que se deseja procurar
	 * @param profundidade int -  Profundidade m�xima de busca
	 * @return Fila -  Uma fila com o(s) caminho(s) completo(s) de todas as corresp�ndencias
	 * @throws TipoNaoEncontradoException - Exce��o lan�ada quando n�o se encontra nenhum arquivo do tipo desejado
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
	 * M�todo que recebe o caminho de uma pasta o nome do arquivo que ser� gerado, e cria um arquivo com todas as sub-pastas e arquivos contidos
	 * na pasta informada pelo usu�rio
	 * @author Lucas Alves da Encarna��o Oliveira
	 * @param nome String
	 * @param nomeArquivo String - Nome do arquivo que sera gerado
	 */

	public String gerarArquivo(String root, String nomeArquivo, String path){

		String msg = null;

		String caminho = null;

		Iterador itr = null;

		Element aux = null;

		/* Split pelo pela express�o regular - */
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
			msg = "Houve problemas na gera��o do arquivo";
		}

		msg =  "Arquivo gerado com Sucesso";
		return msg;

	}


	/**
	 * M�todo auxiliar que monta o caminho de um determinado arquivo/pasta de forma recursiva
	 * @author Lucas Alves da Encarna��o Oliveira
	 * @param v Element - Diret�rio\arquivo �nserido na �rvore
	 * @return String - Caminho completo de um diret�rio\arquivo
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
