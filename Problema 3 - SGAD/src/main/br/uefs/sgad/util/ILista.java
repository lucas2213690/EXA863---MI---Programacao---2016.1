package br.uefs.sgad.util;


public interface ILista {

    public boolean estaVazia();

    public int obterTamanho();

    public void inserirInicio(Object o);

    public void inserirFinal(Object o);

    public void inserir(int index, Object o);

    public Object removerInicio();

    public Object removerFinal();

    public Object recuperar(int index);

    public Iterador iterador();
}
