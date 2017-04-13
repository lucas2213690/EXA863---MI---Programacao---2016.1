
package br.uefs.ecomp.spalle.controller;

import br.uefs.ecomp.spalle.model.*;
import br.uefs.ecomp.spalle.util.*;
import br.uefs.ecomp.spalle.view.Menu;
import java.util.Random;

/**
 * Classe SpalleController utilizada para implementar o padrão de projetos MVC
 * @author Lucas Alves da Encarnação Oliveira
 *
 */
public class SPalleController {
	
	//Criação da Listas
	Lista ListaAluno = new Lista();
	Lista ListaCurso = new Lista();

    public SPalleController() {
    }
    
    
    /**
     * Método que cadastra um Aluno baseado nos parametros e insere no final da ListaAluno
     * @param nome
     * @param nacionalidade
     * @param pais
     * @param cidade
     * @param bairro
     * @param rua
     * @param numero
     * @param cep
     * @param telefone
     * @param opcao1
     * @param opcao2
     * @return null
     */
    public Aluno cadastrarAluno(String nome, String nacionalidade, String pais, String cidade, String bairro,
            String rua, int numero, int cep,String telefone, String opcao1, String opcao2) {
    	
		int id = ListaAluno.obterTamanho() + 1;
    	
    	Aluno aluno = new Aluno();
    	aluno.setNome(nome);
    	aluno.setNacionalidade(nacionalidade);
    	aluno.setPais(pais);
    	aluno.setCidade(cidade);
    	aluno.setBairro(bairro);
    	aluno.setRua(rua);
    	aluno.setNumero(numero);
    	aluno.setId(id);
    	aluno.setCep(cep);
    	aluno.setTelefone(telefone);
    	aluno.setOpcao1(opcao1);
    	aluno.setOpcao2(opcao2);
    	aluno.setSorteado1Opcao(false);
    	aluno.setSorteado2Opcao(false);
    	aluno.setDesistiu1Opcao(false);
    	aluno.setDesistiu2Opcao(false);
    	
    	ListaAluno.inserirFinal(aluno);
    	
    	
    	//System.out.println("O tamanho atual e :" + ListaAluno.obterTamanho());
    	
    	
        return null;
    }
    
    /**
     * Método utilizado para consultar determinado aluno pelo Id
     * @param id
     * @return null
     */

    public Aluno consultarAluno(Integer id) {
    	
    	Aluno aluno = null;
    	boolean achou = false;
    	
    	Iterador itr = ListaAluno.iterador();
    	
    	while (itr.temProximo()){
    		aluno = (Aluno) itr.obterProximo();
    		if (aluno.getId() == id){
    			Menu.mostrarDadosAluno(aluno);
    			achou = true;
    		}
    	}
    	
    	if (achou != true){
    		Menu.mensagem("Não foi encontrado nenhum aluno com este ID cadastrado no sistema");
    	}
    	
    			

        return null;

    }
    
    /**
     * Método utilizado para cadastrar um novo curso
     * @param id
     * @param nome
     * @param qtdVagas
     * @return null
     */

    public Curso cadastrarCurso(int id, String nome, int qtdVagas) {
    	
    	Curso curso =  new Curso();
    	curso.setId(id);
    	curso.setNome(nome);
    	curso.setQtdVagas(qtdVagas);
    	curso.setQtdVagasRestante(qtdVagas);
    	
    	ListaCurso.inserirFinal(curso);
    	
    	
        return null;
    }
    
    /**
     * Método utilizado para consultar um curso pelo Id
     * @param id
     * @return null
     */

    public Curso consultarCurso(Integer id) {
    	
    	Curso curso = null;
    	
    	boolean achou = false;
    	
    	Iterador itr = ListaCurso.iterador();
    	
    	while (itr.temProximo()){
    		curso = (Curso) itr.obterProximo();
    		if (curso.getId() == id){
    			Menu.mostrarDadosCurso(curso);
    			achou = true;
    		}
    	}
    	
    	if (achou != true){
    		Menu.mensagem("Não foi encontrado nenhum aluno com este ID cadastrado no sistema");
    	}

        return null;

    }
    
    /**
     * Método utilizado para sortear as Vagas entre os alunos participantes de forma randomica
     * @see java.util.Random
     */

    public void sortearVagas() {
  
    	Curso curso = null;
    	
    	Aluno aluno = null;
    	
    	Random random = new Random();
    	
    	int numeroSorteados = 0;
    	
    	int aleatorio = 0;
    	
    	int qtdVagasTotal = 0;
    	
    	Iterador itrCurso1 = ListaCurso.iterador();
    	
    	// Pega o total de vagas de todos os cursos
    	while (itrCurso1.temProximo()){
    		curso = (Curso) itrCurso1.obterProximo();
    		qtdVagasTotal = qtdVagasTotal + curso.getQtdVagas();
    		//System.out.println("Pega o total de vagas de todos os cursos");
    	}
    	
    	// Enquanto o numeroSorteio for menor que o Tamanho da Lista e a Quantidade Total de Vagas
    	while (numeroSorteados < ListaAluno.obterTamanho() && numeroSorteados < qtdVagasTotal){
    			
    			aleatorio = random.nextInt(ListaAluno.obterTamanho());
    			//System.out.println(aleatorio);
    			aluno = (Aluno) ListaAluno.recuperar(aleatorio);
    			Iterador itrCurso = ListaCurso.iterador();
    			if (itrCurso.temProximo()){
    				curso = (Curso) itrCurso.obterProximo();
    				if (aluno.getOpcao1().equals(curso.getNome()) && curso.getQtdVagasRestante() > 0 && aluno.getSorteado1Opcao() != true && aluno.getDesistiu1Opcao() != true){
    					
	    				aluno.setSorteado1Opcao(true);
	    				numeroSorteados++;
	    				curso.decrementaVaga();
	    				System.out.printf("O aluno %s foi sorteado (1 opção) no curso %s", aluno.getNome(), curso.getNome());
    				}
    				else if (aluno.getOpcao2().equals(curso.getNome()) && curso.getQtdVagasRestante() > 0 && aluno.getSorteado2Opcao() != true && aluno.getDesistiu2Opcao() != true){
    					aluno.setSorteado2Opcao(true);
	    				numeroSorteados++;
	    				curso.decrementaVaga();
	    				System.out.printf("O aluno %s foi sorteado (2 opçao) no curso %s", aluno.getNome(), curso.getNome());
    				}
    			}
    		}
    }
    
    /**
     * Método utilizado para listar todos os alunos sorteados de um determinado curso 
     * @return null
     */

    public Iterador listarAlunosSelecionados(Integer id) {
    	
    	String nomeCurso = null;
    	
    	Aluno aluno = null;
    	
    	Curso curso = null;
    	
    	boolean achou = false;
    	
    	Iterador itrCurso = ListaCurso.iterador();
    			
    	while (itrCurso.temProximo() && achou != true){
    		curso = (Curso) itrCurso.obterProximo();
    		
    		if (curso.getId() == id){
    			achou = true;
    			nomeCurso = curso.getNome();
    		}
    	}
    	
    	Iterador itrAluno = ListaAluno.iterador();
    	
    	while (itrAluno.temProximo()){
    		aluno = (Aluno) itrAluno.obterProximo();
    		if (aluno.getSorteado1Opcao() == true && aluno.getOpcao1().equals(nomeCurso)){
    			Menu.mostrarDadosAluno(aluno);
    		}
    		else if (aluno.getSorteado2Opcao() == true && aluno.getOpcao2().equals(nomeCurso)){
    			Menu.mostrarDadosAluno(aluno);
    		}
    		
    	}
    	
        return null;
    }
    
    /**
     * Método utilizado para listar todos os alunos da lista reserva
     * @return null
     */

    public Iterador listarAlunosReserva() {
    	
    	Aluno aluno;
    	
    	Iterador itrAluno = ListaAluno.iterador();
    	
    	while (itrAluno.temProximo()){
    		aluno = (Aluno) itrAluno.obterProximo();
    		if (aluno.getSorteado1Opcao() != true && aluno.getSorteado2Opcao() != true){
    			Menu.mostrarDadosAluno(aluno);
    		}
    	}
        return null;
    }
    
    /**
     * Método que retira um Aluno do curso em que ele foi sorteado
     * @param id
     */

    public void desistir(Integer id) {
    	
    	Aluno aluno;
    	
    	Iterador itr = ListaAluno.iterador();
    	
    	while (itr.temProximo()){
    		aluno = (Aluno) itr.obterProximo();
    		if (aluno.getId() == id){
    			aluno.setSorteado1Opcao(false);
    			aluno.setSorteado2Opcao(false);
    			
    			if (aluno.getSorteado1Opcao())
    				aluno.setDesistiu1Opcao(true);
    			
    			else if (aluno.getSorteado2Opcao())
    				aluno.setDesistiu2Opcao(true);
    			
    			else
    				Menu.mensagem("O aluno não foi sorteado em nenhuma vaga, então não pode desistir");
    		}
    	}

    }
    
}
