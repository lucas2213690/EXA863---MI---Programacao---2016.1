package br.uefs.sgad.controller;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import br.uefs.sgad.util.ArquivoNaoEncontradoException;
import br.uefs.sgad.util.Fila;
import br.uefs.sgad.util.NaoEhPastaException;
import br.uefs.sgad.util.PastaNaoEncontradaException;
import br.uefs.sgad.util.TipoNaoEncontradoException;

public class SgadTeste {
	
	SgadController controller = new SgadController();
	

	@Test
	public void testMontarSucesso() throws PastaNaoEncontradaException, NaoEhPastaException{
		Fila a = controller.montarArvore("src/test/br/uefs/sgad/controller/TesteRoot");
		assertEquals(a.obterTamanho(), 18);
	}
	
	@Test(expected = PastaNaoEncontradaException.class)
	public void testeMontarPastaNaoExiste() throws PastaNaoEncontradaException, NaoEhPastaException {
		controller.montarArvore("src/test/br/uefs/sgad/controller/TesteRoot/NaoExiste");
	}
	
	@Test(expected = NaoEhPastaException.class)
	public void testeMontarNaoEPasta() throws PastaNaoEncontradaException, NaoEhPastaException {
		controller.montarArvore("src/test/br/uefs/sgad/controller/TesteRoot/Relatorio.pdf");
	}
	
	@Test
	public void testePesquisaArquivoSucesso() throws ArquivoNaoEncontradoException, NaoEhPastaException, PastaNaoEncontradaException{
		controller.montarArvore("src/test/br/uefs/sgad/controller/TesteRoot");
		Fila a = controller.procuraArquivo("Relatorio.pdf", 1);
		assertEquals((String) a.removerInicio(), "TesteRoot\\Relatorio.pdf");
	}
	
	@Test(expected = ArquivoNaoEncontradoException.class)
	public void testePesquisaArquivoNaoEncontrado() throws ArquivoNaoEncontradoException, NaoEhPastaException, PastaNaoEncontradaException{
		controller.montarArvore("src/test/br/uefs/sgad/controller/TesteRoot");
		controller.procuraArquivo("NaoExiste.pdf", 1);
	}
	
	@Test
	public void testePesquisaExtensaoSucesso() throws TipoNaoEncontradoException, NaoEhPastaException, PastaNaoEncontradaException{
		controller.montarArvore("src/test/br/uefs/sgad/controller/TesteRoot");
		Fila a = controller.procuraExtensao("pdf", 1);
		assertEquals((String) a.removerInicio(), "TesteRoot\\Relatorio.pdf");
		
	}
	
	@Test(expected = TipoNaoEncontradoException.class)
	public void testePesquisaExtensaoNaoEncontrado() throws TipoNaoEncontradoException, NaoEhPastaException, PastaNaoEncontradaException{
		controller.montarArvore("src/test/br/uefs/sgad/controller/TesteRoot");
		controller.procuraExtensao("cpp", 2);	
	}
	
	@Test
	public void testePesquisaPastaSucesso() throws NaoEhPastaException, PastaNaoEncontradaException{
		controller.montarArvore("src/test/br/uefs/sgad/controller/TesteRoot");
		Fila a = controller.procuraPasta("Windows_Insider_Anniversary_PC\\", 1);
		assertEquals((String) a.removerInicio(), "TesteRoot\\Windows_Insider_Anniversary_PC\\");
	}
	
	@Test(expected = PastaNaoEncontradaException.class)
	public void testePesquisaPastaNaoEncontrada() throws NaoEhPastaException, PastaNaoEncontradaException{
		controller.montarArvore("src/test/br/uefs/sgad/controller/TesteRoot");
		controller.procuraPasta("Windows", 1);
	}
	
	@Test
	public void testeGerarArquivoSucesso() throws NaoEhPastaException, PastaNaoEncontradaException, IOException{
		int numeroLinhas = 0;
		BufferedReader reader = null;
		controller.montarArvore("src/test/br/uefs/sgad/controller/TesteRoot");
		controller.gerarArquivo("Windows_Insider_Anniversary_Band\\", "testJunit", "src/test/br/uefs/sgad/controller/");

		reader = new BufferedReader(new FileReader("src/test/br/uefs/sgad/controller/testJunit.txt"));
		String line;
		while ((line = reader.readLine()) != null)
		{
			numeroLinhas++;
		}
		reader.close();
		assertEquals(numeroLinhas, 3);
	}
	
	

}
