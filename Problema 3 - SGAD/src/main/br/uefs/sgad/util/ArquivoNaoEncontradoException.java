package br.uefs.sgad.util;

public class ArquivoNaoEncontradoException extends Exception {

	/**
	 * 
	 */
	
	String nomeArquivo = null;
	
	private static final long serialVersionUID = 1L;
	
	public ArquivoNaoEncontradoException(){
		super();
	}
	
	public ArquivoNaoEncontradoException(String msg){
		 super(msg);
		
	}
	@Override
	public String toString(){
		return super.toString();
	}
	
	@Override
	public String getMessage(){
		return super.getMessage() + nomeArquivo;
	}

}
