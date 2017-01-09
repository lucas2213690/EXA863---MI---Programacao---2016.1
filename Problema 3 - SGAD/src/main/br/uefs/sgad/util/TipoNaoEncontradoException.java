package br.uefs.sgad.util;

public class TipoNaoEncontradoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String nomeTipo = null;
	
	public TipoNaoEncontradoException(){
		super();
	}
	
	public TipoNaoEncontradoException(String msg){
		 super(msg);
		
	}
	@Override
	public String toString(){
		return super.toString();
	}
	
	@Override
	public String getMessage(){
		return super.getMessage() + nomeTipo;
	}

}
