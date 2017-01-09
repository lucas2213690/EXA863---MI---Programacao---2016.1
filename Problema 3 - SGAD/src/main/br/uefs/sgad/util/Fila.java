package br.uefs.sgad.util;

public class Fila implements IFila {
	
	private Celula head;
	private Celula tail;
	private int size;

	@Override
	public boolean estaVazia() {
		// TODO Auto-generated method stub
		return head == null;
	}

	@Override
	public int obterTamanho() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void inserirFinal(Object o) {
		// TODO Auto-generated method stub
		if (estaVazia())
			head = tail = new Celula(o);
		else{
			Celula temp = tail;
			tail = new Celula(o);
			temp.setProxima(tail);
		}
		size++;

	}

	@Override
	public Object removerInicio() {
		Object ret = null;
		if (!estaVazia()){
			ret = head.getElemento();
			if (head == tail){
				head = tail = null;
			}
			else{
				head = head.getProxima();
			}
			size--;
			
		}
		
		return ret;
	}

	@Override
	public Object recuperarInicio() {
		// TODO Auto-generated method stub
		if (estaVazia())
			return null;
		else
			return head.getElemento();
	}

}
