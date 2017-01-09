package br.uefs.stmserver.model;

import br.uefs.stmserver.util.Fila;
/**
 * Classe que representa um usuário do sistema de mensagens
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
	 * Método que seta o nick
	 * @param nick
	 */
	public void setNick(String nick){
		this.nick = nick;
	}
	
	/**
	 * Método que retorna o nick
	 * @return
	 */
	public String getNick(){
		return this.nick;
	}
	
	/**
	 * Método que recebe uma mensagem e adiciona ela na fila de mensagens
	 * @param msg
	 */
	public void guardarMensagem(String msg){
		
		Object o = msg;
		fila.inserirFinal(o);
	}
	
	/**
	 * Método que retorna a fila de mensagens
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
