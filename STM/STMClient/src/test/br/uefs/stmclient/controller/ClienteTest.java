package br.uefs.stmclient.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

//import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClienteTest {

	private Cliente cliente;
	
	@Before
	public void setUp() {
		cliente  = new Cliente();
	}
	
	@Test
	public void testLoginSucesso() throws IOException, InterruptedException, ClassNotFoundException{
		assertEquals("Usuário logado com sucesso",cliente.login("Thiago"));
		cliente.logoff();
	}
	
	@Test
	public void testLoginNickJaLogado() throws IOException, InterruptedException, ClassNotFoundException{
		cliente.login("Thiago");
		assertEquals("Usuário já está logado",cliente.login("Thiago"));
		cliente.logoff();
	}
	
	@Test
	public void testAtualizaListaUsuariosSucesso() throws IOException, InterruptedException, ClassNotFoundException{
		cliente.login("Thiago");
		String[] usuarios =  cliente.atualizaUsuarios();
		assertEquals(1,usuarios.length);
		assertEquals("Thiago",usuarios[0]);
		cliente.logoff();
	}
	
	@Test
	public void testAtualizaListaUsuariosVazia() throws IOException, InterruptedException, ClassNotFoundException{
		String[] usuarios =  cliente.atualizaUsuarios();
		assertEquals(0,usuarios.length);
	}
	
	@Test
	public void testMandaMensagem() throws ClassNotFoundException, IOException, InterruptedException{
		cliente.login("Thiago");
		cliente.enviaMensagem("Test", "Thiago");
		String[] mensagem = cliente.recebeMensagens();
		assertEquals(1,mensagem.length);
		assertEquals("Test", mensagem[0]);
		cliente.logoff();
	}
	
	@Test
	public void testMandaMensagemNaoExiste() throws ClassNotFoundException, IOException, InterruptedException{
		cliente.login("Thiago");
		assertEquals("Usuário não está conectado, por favor tente mais tarde",cliente.enviaMensagem("Test", "NaoExiste"));
		cliente.logoff();
	}
	
	@Test
	public void testLogoffSucesso() throws ClassNotFoundException, IOException, InterruptedException{
		cliente.login("Thiago");
		assertEquals("logoff efetuado com sucesso",cliente.logoff());
	}
	
	@Test
	public void testRecebeMensagensVazia() throws ClassNotFoundException, IOException, InterruptedException{
		String[] mensagem;
		cliente.login("Thiago");
		mensagem = cliente.recebeMensagens();
		assertEquals("Não existem mensagens não lidas",mensagem[0]);
		cliente.logoff();
	}
	
	
	
}
