package br.uefs.stmserver.model;

import br.uefs.stmserver.util.Fila;
/**
 * Classe que representa um usu�rio do sistema de mensagens
 * @author lucas
 *
 */
public class Usuario {
	
	private String nick;
	private Fila fila;
	
	public Usuario(){
		fila = new Fila();
	}
	
	/**
	 * M�todo que seta o nick
	 * @param nick
	 */
	public void setNick(String nick){
		this.nick = nick;
	}
	
	/**
	 * M�todo que retorna o nick
	 * @return
	 */
	public String getNick(){
		return this.nick;
	}
	
	/**
	 * M�todo que recebe uma mensagem e adiciona ela na fila de mensagens
	 * @param msg
	 */
	public void guardarMensagem(String msg){
		
		Object o = msg;
		fila.inserirFinal(o);
	}
	
	/**
	 * M�todo que retorna a fila de mensagens
	 * @return
	 */
	public Fila getFila(){
		return fila;
	}
	
	@Override
	public String toString(){
		return "Usuario " + this.nick;
	}
	
	

}
