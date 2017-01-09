package br.uefs.sgad.util;

public class NoArvore implements Element {
	
	private Object data;

    private Lista children;

    private NoArvore parent;

    
    public NoArvore(NoArvore parent, Object data) {
    	
    	children = new Lista();
    	
    	this.parent = parent;
    	
    	this.data = data;

    }



    public NoArvore getParent() {

        return this.parent;

    }

    public Lista getChildren() {

        return this.children;

    }

    private void setChildren(Lista children) {


    	Iterador itr = children.iterador();
    	
    	while (itr.temProximo()){
    		NoArvore o = (NoArvore) itr.obterProximo();
    		o.parent = this;
    	}

        this.children = children;
    }



    public void add(NoArvore child) {

        //child.parent = parent;

        children.inserirFinal(child);

    }

    public Object getData() {

        return this.data;

    }



    public void setData(Object o) {

        this.data = o;

    }



    public String toString() {

        return getData().toString();

    }
}
