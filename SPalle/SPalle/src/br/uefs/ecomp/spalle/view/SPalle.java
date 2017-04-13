package br.uefs.ecomp.spalle.view;

import java.io.IOException;
import br.uefs.ecomp.spalle.controller.SPalleController;
import br.uefs.ecomp.spalle.util.Console;

/****************************************************************************************

Autor: Lucas Alves da Encarnação Oliveira
Componente Curricular : MI - Programação
Concluido em: 27/07/2016
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet, qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não seráo considerados para fins de avaliação

*****************************************************************************************/

public class SPalle {

	public static void main(String[] args) throws NumberFormatException, IOException  {
		
		String nome, nacionalidade, pais, cidade, bairro, rua, opcao1, opcao2, telefone;
		int id, numero, cep, menu, qtdVagas;
		
		SPalleController controller = new SPalleController();
		
		
		while (true){
			
			Menu.exibirMenu();
			menu = Console.readInt();
			
			
			
			switch (menu){
			
			// CadastrarAluno
			case 1:
				
				
				System.out.println("Digite o nome");
				nome = Console.readString();
				System.out.println("Digite a nacionalidade");
				nacionalidade = Console.readString();
				System.out.println("Digite o País");
				pais = Console.readString();
				System.out.println("Digite o cidade");
				cidade = Console.readString();
				System.out.println("Digite o bairro");
				bairro = Console.readString();
				System.out.println("Digite a rua");
				rua = Console.readString();
				System.out.println("Infome o número da casa");
				numero = Console.readInt();
				System.out.println("Informe o cep");
				cep = Console.readInt();
				System.out.println("Informe seu telefone");
				telefone = Console.readString();
				System.out.println("Digite sua primeira opção de curso");
				opcao1 = Console.readString();
				System.out.println("Digite sua segunda opcao de curso ");
				opcao2 = Console.readString();
				
				
				controller.cadastrarAluno(nome, nacionalidade, pais, cidade, bairro, rua, numero, cep, telefone, opcao1, opcao2);
				break;
			
			// ConsultarAluno
			case 2:
				System.out.println("Infome o número de id do aluno");
				id = Console.readInt();
				controller.consultarAluno(id);
				break;
			
			// CadastrarCurso
			case 3:
				
				System.out.println("Escreva o nome do novo Curso");
				nome = Console.readString();
				System.out.println("Infome o id do Curso");
				id = Console.readInt();
				System.out.println("Infome a quantidade de Vagas deste curso");
				qtdVagas = Console.readInt();
				controller.cadastrarCurso(id, nome, qtdVagas);
				break;
			
			// ConsultarCurso
			case 4:
				
				System.out.println("Infome o ID do Curso que deseja consultar");
				
				id = Console.readInt();
				
				
				controller.consultarCurso(id);
				break;
				
			//SortearVagas
			case 5:
				
				controller.sortearVagas();
				break;
				
			//ListarAlunosSelecionados
			case 6:
				System.out.println("Digite o ID do curso em que você deseja listar os sorteados");
				id = Console.readInt();
				controller.listarAlunosSelecionados(id);
				break;
			
			//ListarAlunosReserva
			case 7:
				System.out.println("Listando alunos da Reserva");
				controller.listarAlunosReserva();
				break;
				
			//Sair
			case 8:
				System.out.println("Saindo...");
				System.exit(0);
				break;
			}
		}
	}
}
