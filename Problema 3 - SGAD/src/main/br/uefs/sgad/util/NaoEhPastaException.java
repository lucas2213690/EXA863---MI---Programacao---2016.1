package br.uefs.sgad.util;

public class NaoEhPastaException extends Exception {

	/**
	 * 
	 */
	
	String nomePasta = null;
	
	private static final long serialVersionUID = 1L;
	
	public NaoEhPastaException(){
		super();
	}
	
	public NaoEhPastaException(String msg, String nomePasta){
		 super(msg);
		 this.nomePasta = nomePasta;
		
	}
	@Override
	public String toString(){
		return super.toString();
	}
	
	@Override
	public String getMessage(){
		return super.getMessage() + " " + nomePasta;
	}
}
