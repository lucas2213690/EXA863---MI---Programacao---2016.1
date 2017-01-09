package br.uefs.sgad.view;
import java.io.IOException;

import br.uefs.sgad.util.*;
import br.uefs.sgad.controller.*;

public class Sgad {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		SgadController controller = new SgadController();

		int opcao = 0;

		Fila caminhoArquivo = null;
		Fila caminhoPasta = null;
		Fila caminhoTipo = null;
		String nomeArquivo = null;
		String nomePasta = null;
		String tipoArquivo = null;
		String aux = null;
		String path = null;
		String msg = null;
		Integer profundidade = 0;

		do{
			Menu.exibir();
			opcao = Console.readInt();



			switch (opcao){

			// Opção 1 do Menu

			case 1:

				System.out.println("Digite o caminho");
				nomePasta = Console.readString();

				try {
					caminhoArquivo = controller.montarArvore(nomePasta);
					while (!caminhoArquivo.estaVazia()){
						aux = (String) caminhoArquivo.removerInicio();
						System.out.println(aux);
					}
				} catch (NaoEhPastaException e) {
					e.printStackTrace();
				} catch (PastaNaoEncontradaException e){
					e.printStackTrace();
				}

				break;

			case 2:
				System.out.println("Digite o nome do Arquivo");
				nomeArquivo = Console.readString();
				System.out.println("Digite a profundidade");
				profundidade = Console.readInt();

				try{
					caminhoArquivo = controller.procuraArquivo(nomeArquivo, profundidade);
					while (!caminhoArquivo.estaVazia()){
						aux = (String) caminhoArquivo.removerInicio();
						System.out.println(aux);
					}
				} catch (ArquivoNaoEncontradoException e){
					e.printStackTrace();
				}
				break;


			case 3:
				System.out.println("Digite o nome da Pasta seguida de uma \\");
				nomePasta = Console.readString();
				System.out.println("Digite a profundidade");
				profundidade = Console.readInt();

				try{
					caminhoPasta = controller.procuraPasta(nomePasta, profundidade);
					while (!caminhoPasta.estaVazia()){
						aux = (String) caminhoPasta.removerInicio();
						System.out.println(aux);
					}
				} catch (PastaNaoEncontradaException e){
					e.printStackTrace();
				}
				break;

			case 4:
				System.out.println("Digite o nome da extensao que deseja procurar");
				tipoArquivo = Console.readString();
				System.out.println("Digite a profundidade");
				profundidade = Console.readInt();
				try{
					caminhoTipo = controller.procuraExtensao(tipoArquivo, profundidade);
					while (!caminhoTipo.estaVazia()){
						aux = (String) caminhoTipo.removerInicio();
						System.out.println(aux);
					}


				} catch (TipoNaoEncontradoException e){
					e.printStackTrace();

				}
				break;

			case 5:
				System.out.println("Digite o caminho completo da pasta separada por -");
				nomePasta = Console.readString();
				System.out.println("Informe o nome do arquivo que sera gerado");
				nomeArquivo = Console.readString();

				System.out.println("Digite o caminho em que sera salvo o arquivo");
				path = Console.readString();
				msg = controller.gerarArquivo(nomePasta, nomeArquivo, path);
				
				System.out.println(msg);
				break;


			}

		}while(opcao != 6);

	}
}

