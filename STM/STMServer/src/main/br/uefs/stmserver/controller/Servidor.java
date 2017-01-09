package br.uefs.stmserver.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import br.uefs.stmserver.model.Usuario;
import br.uefs.stmserver.util.*;

/**
 * Classe que representa o servidor
 * @author lucas
 *
 */
public class Servidor {

	private Lista lista;
	private ServerSocket servidor;
	private Socket cliente;
	private ObjectOutputStream saida;
	private ObjectInputStream entrada;

	public Servidor(){
		cliente = null;
		saida = null;
		entrada = null;
		lista = null;
		servidor = null;
	}


	public void iniciaServidor() {

		String msgRecebida = "";



		try {

			//Cria a instancia do servidor
			servidor = new ServerSocket(1234);
			lista = new Lista();

			while (true){
				//Escutando a conexão
				cliente = servidor.accept();

				saida = new ObjectOutputStream(cliente.getOutputStream());
				//saida.flush();

				entrada = new ObjectInputStream(cliente.getInputStream());

				msgRecebida = (String) entrada.readObject();

				//System.out.println(msgRecebida);

				interpreta(msgRecebida);

				entrada.close();
				saida.close();
			}


		}
		catch (Exception e){
			System.out.println("Erro no Servidor:" + e.getMessage());
		}

	}
	/**Método que interpreta o comando recebido e chama o método correspondente
	 * 
	 * @param msgRecebida
	 * @throws IOException
	 */
	private void interpreta(String msgRecebida) throws IOException{
		String[] msgRecebidaSplit = msgRecebida.split("#");

		switch (msgRecebidaSplit[0]){

		case "login":
			logar(msgRecebidaSplit[1]);
			break;
		case "send":
			send(msgRecebidaSplit[1],msgRecebidaSplit[2],msgRecebidaSplit[3]);
			break;
		case "receive":
			buscaMensagens(msgRecebidaSplit[1]);
			break;
		case "refresh":
			buscaUsuarios();
			break;
		case "logoff":
			logoff(msgRecebidaSplit[1]);
			break;
		}

	}

	/**
	 * Método que realiza o logoff
	 * 
	 * @param nick
	 * @throws IOException
	 */
	private void logoff(String nick) throws IOException {

		int count = 0;
		boolean achou = false;
		Iterador itr = lista.iterador();
		Usuario aux2 = null;
		Usuario aux = null;
		while (itr.temProximo()){

			aux = (Usuario) itr.obterProximo();
			if (aux.getNick().equals(nick)){

				aux2 = (Usuario) lista.remover(count);
				achou = true;
			}
			count++;
		}
		if (achou)
			saida.writeObject("logoff efetuado com sucesso");
	}

	/** 
	 * Método que busca os usuários e retorna um array de String
	 * 
	 * @throws IOException
	 */
	private void buscaUsuarios() throws IOException {

		String[] listaUsuarios = new String[lista.obterTamanho()];

		int cont = 0;
		Iterador itr = lista.iterador();
		Usuario aux = null;


		//Ordena a lista
		for (int i = 0; i < lista.obterTamanho(); i++){
			Usuario a = null;
			Usuario b = null;
			for (int j = 0; j < lista.obterTamanho(); j++){
				a = (Usuario) lista.recuperar(i);
				b = (Usuario) lista.recuperar(j);
				if (a.getNick().compareToIgnoreCase(b.getNick()) > 0){
					lista.swap(i, j);
				}
			}
		}


		// Coloca os nicks em um array
		while (itr.temProximo()){
			aux = (Usuario) itr.obterProximo();
			listaUsuarios[cont] = aux.getNick();
			cont++;
		}

		saida.writeObject(listaUsuarios);

	}

	/**
	 * Método que busca um usuario pelo nick e retorna-o
	 * @param nick
	 * @return
	 */
	private Usuario buscaUsuario(String nick){
		Iterador itr = lista.iterador();
		Usuario aux = null;
		Usuario encontrado = null;
		while (itr.temProximo()){
			aux = (Usuario) itr.obterProximo();
			if (aux.getNick().equals(nick)){

				encontrado = aux;
			}
		}
		//System.out.println(encontrado);
		return encontrado;

	}

	/**
	 * Método que retorna as mensagens de um usuário
	 * @param nick
	 * @throws IOException
	 */
	private void buscaMensagens(String nick) throws IOException {
		Usuario usuario = buscaUsuario(nick);
		int i = 0;
		Fila fila =  usuario.getFila();
		String[] filaMensagem;
		if (fila.estaVazia()){
			filaMensagem = new String[fila.obterTamanho() + 1];
			filaMensagem[0] = "Não existem mensagens não lidas";
		}
		else{
			filaMensagem = new String[fila.obterTamanho()];
		}

		while (!fila.estaVazia()){
			filaMensagem[i] = (String) fila.removerInicio();
			i++;
		}

		saida.writeObject(filaMensagem);


	}

	/**
	 * Método auxiliar que concatena o dia e a hora atual
	 * @return data
	 */
	private String getDataHora(){
		String data = "";
		SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
		data = formata.format(new Date());							
		formata = new SimpleDateFormat("hh:mm");
		data = data + " - "+formata.format(new Date());
		return data;
	}

	/**
	 * Método que envia uma mensagem para um determinado usuário, se ele estiver online
	 * @param nickFrom
	 * @param nickTo
	 * @param msg
	 * @throws IOException
	 */
	private void send(String nickFrom, String nickTo, String msg) throws IOException {
		Iterador itr = lista.iterador();
		Usuario aux = null;
		Usuario destinatario = null;
		boolean estaOnline = false;

		while (itr.temProximo()){

			aux = (Usuario) itr.obterProximo();
			if (aux.getNick().equals(nickTo)){
				estaOnline = true;
				destinatario = aux;
			}
		}

		if (estaOnline){
			destinatario.guardarMensagem(nickFrom + " " + getDataHora() +" - " + msg);
			saida.writeObject("Mensagem enviada com sucesso");
		}
		else{
			saida.writeObject("Usuário não está conectado, por favor tente mais tarde");
		}
	}

	/**
	 * Método responsável por logar um usuário, se já não possuir nenhum usuário com aquele nick
	 * @param nick
	 * @throws IOException
	 */
	private void logar(String nick) throws IOException{
		Usuario login = buscaUsuario(nick);
		if (login == null || lista.estaVazia()){
			Usuario usuario = new Usuario();
			usuario.setNick(nick);
			lista.inserirFinal(usuario);
			saida.writeObject("Usuário logado com sucesso");
		}			
		else{
			saida.writeObject("Já existe um usuário logado com este nick");
		}
	}
}
