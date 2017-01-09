package br.uefs.sgad.util;

public interface IArvore {
	

	public boolean isEmpty();

	public Element addChild(Element no, Object o);
	
	public int depth(Element o);

	public Iterador iterador();
	

}
