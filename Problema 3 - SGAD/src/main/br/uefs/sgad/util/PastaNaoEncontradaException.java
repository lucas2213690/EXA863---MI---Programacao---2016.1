package br.uefs.sgad.util;

public class PastaNaoEncontradaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String nomeArquivo = null;
	
	public PastaNaoEncontradaException(){
		super();
	}
	
	public PastaNaoEncontradaException(String msg, String nomeArquivo){
		 super(msg);
		 this.nomeArquivo = nomeArquivo;
		
	}
	@Override
	public String toString(){
		return super.toString();
	}
	
	@Override
	public String getMessage(){
		return super.getMessage() + " " + nomeArquivo;
	}

}
