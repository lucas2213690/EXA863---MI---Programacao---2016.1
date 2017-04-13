package br.uefs.ecomp.spalle.view;

import br.uefs.ecomp.spalle.model.Aluno;
import br.uefs.ecomp.spalle.model.Curso;

/**
 * Classe que representa o Menu
 * @author Lucas Alves da Encarnação Oliveira
 *
 */
public class Menu {
	/**
	 * Método estático utilizado para exibir o Menu
	 */
	public static void exibirMenu(){
		System.out.println("#################");
		System.out.println("1 - Inserir Aluno");
		System.out.println("2 - Consultar Aluno");
		System.out.println("3 - Inserir Curso");
		System.out.println("4 - Consultar Curso");
		System.out.println("5 - Sortear");
		System.out.println("6 - Mostrar dados dos alunos por curso");
		System.out.println("7 - Mostrar dados dos alunos não selecionados");
		System.out.println("8 - Sair");
	}
	
	public static void mostrarDadosAluno(Aluno aluno){
		System.out.println(aluno.toString());
    }
	
	public static void mostrarDadosCurso(Curso curso){
		System.out.println(curso.toString());
	}
	
	public static void mensagem(String s){
		System.out.println(s);
	}
}
