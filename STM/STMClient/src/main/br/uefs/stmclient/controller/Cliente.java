package br.uefs.stmclient.controller;

import java.net.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Cliente {

	private String nomeUsuario;
	private Boolean estaLogado;
	private Socket socket;
	private ObjectOutputStream saida;
	private ObjectInputStream entrada;

	public Cliente() {
		estaLogado = false;
		socket = null;
		saida = null;
		entrada = null;
	}


	public String login(String nick) throws IOException, InterruptedException, ClassNotFoundException{

		String s = "";
		if (!estaLogado){
			nomeUsuario = nick;
			estabelecerConexao();
			saida.writeObject("login#" + nomeUsuario);
			s = (String) entrada.readObject();
			saida.flush();
			estaLogado = true;
		}
		else{
			s = "Usuário já está logado";
		}
		return s;
	}

	public String enviaMensagem(String msg, String to) throws IOException, ClassNotFoundException{

		String s = "";

		if (estaLogado){
			estabelecerConexao();

			saida.writeObject("send#" + nomeUsuario + "#" + to + "#" + msg);
			s = (String) entrada.readObject();
			saida.flush();
		}
		else{
			s = "Usuário não está logado";
		}
		return s;
	}

	public String[] recebeMensagens() throws IOException, ClassNotFoundException{

		String[] mensagens = null;

		if (estaLogado){
			estabelecerConexao();
			saida.writeObject("receive#" + nomeUsuario);
			mensagens = (String[]) entrada.readObject();
		}
		return mensagens;
	}

	public String[] atualizaUsuarios() throws IOException, InterruptedException, ClassNotFoundException{

		String[] usuarios = null;

		estabelecerConexao();
		saida.writeObject("refresh#");
		usuarios = (String[]) entrada.readObject();
		return usuarios;
	}

	public String logoff() throws IOException, ClassNotFoundException{
		String mensagem = "";
		if (estaLogado){

			estabelecerConexao();

			saida.writeObject("logoff#" + nomeUsuario);
			mensagem = (String) entrada.readObject();
			estaLogado = false;
			
		}
		
		return mensagem;

	}
	
	private void estabelecerConexao() throws UnknownHostException, IOException{
		socket = new Socket("127.0.0.1", 1234);
		saida = new ObjectOutputStream(socket.getOutputStream());
		entrada = new ObjectInputStream(socket.getInputStream());
	}

}
