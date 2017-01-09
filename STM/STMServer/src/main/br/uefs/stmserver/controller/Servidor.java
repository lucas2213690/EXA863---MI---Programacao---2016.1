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
				//Escutando a conex�o
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
	/**M�todo que interpreta o comando recebido e chama o m�todo correspondente
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
	 * M�todo que realiza o logoff
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
	 * M�todo que busca os usu�rios e retorna um array de String
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
	 * M�todo que busca um usuario pelo nick e retorna-o
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
	 * M�todo que retorna as mensagens de um usu�rio
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
			filaMensagem[0] = "N�o existem mensagens n�o lidas";
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
	 * M�todo auxiliar que concatena o dia e a hora atual
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
	 * M�todo que envia uma mensagem para um determinado usu�rio, se ele estiver online
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
			saida.writeObject("Usu�rio n�o est� conectado, por favor tente mais tarde");
		}
	}

	/**
	 * M�todo respons�vel por logar um usu�rio, se j� n�o possuir nenhum usu�rio com aquele nick
	 * @param nick
	 * @throws IOException
	 */
	private void logar(String nick) throws IOException{
		Usuario login = buscaUsuario(nick);
		if (login == null || lista.estaVazia()){
			Usuario usuario = new Usuario();
			usuario.setNick(nick);
			lista.inserirFinal(usuario);
			saida.writeObject("Usu�rio logado com sucesso");
		}			
		else{
			saida.writeObject("J� existe um usu�rio logado com este nick");
		}
	}
}
