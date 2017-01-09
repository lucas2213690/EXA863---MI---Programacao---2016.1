package br.uefs.sgad.util;

public class Arvore implements IArvore {

	private NoArvore root;



	public Arvore() {
		root = null;
	}

	public Arvore(NoArvore root){
		this.root = root;
	}



	public Element getRoot() {
		return (Element) this.root;

	}

	public Element addChild(Element pai, Object o){
		Element aux = null;
		if (root == null){
			root = new NoArvore(null, o);
			aux = root;
		}
		else{
			aux = new NoArvore((NoArvore)pai, o);
			((NoArvore)pai).add((NoArvore)aux);
		}

		return aux;

	}


	public Element find(Object dataToFind) {
		
		Element aux = null;
		
		String aux2 = null;
		
		Element ret = null;
		
		Iterador itr = iterador();
		
		while (itr.temProximo()){
			aux = (Element) itr.obterProximo();
			aux2 = (String) aux.getData();
			if (aux2.equals(dataToFind)){
				ret = aux; 
			}
		}
		
		return ret;

	}
	
	@Override
	public boolean isEmpty() {

		return (root == null);

	}

	@Override
	public int depth(Element v){

		if (v == root)
			return 0;
		else
			return 1 + depth (((NoArvore)v).getParent());
	}	



	@Override
	public Iterador iterador() {
		// TODO Auto-generated method stub

		return new IteratorArvore();
	}
	
	public Iterador iterador(Element a){
		
		return new IteratorArvore(a);
	}

	public class IteratorArvore implements Iterador{
		private IFila fila = new Fila();
		
		public IteratorArvore(Element customRoot){
			fila.inserirFinal((NoArvore) customRoot);
		}

		public IteratorArvore(){
			fila.inserirFinal(root);
			

			

		}

		public boolean temProximo(){
			return !fila.estaVazia();
		}

		public Object obterProximo(){
			NoArvore proximo = null;
			NoArvore atual = (NoArvore) fila.removerInicio();
			//System.out.println(atual.getData());
			Lista l = atual.getChildren();
			Iterador it = l.iterador();
			while (it.temProximo()){
				proximo = (NoArvore) it.obterProximo();
				fila.inserirFinal(proximo);
			}
			//atual.setDepth(depth(atual) - 1);
			return atual;
		}


	}



}
