package aula12.exemplos.interfaces;

public class Vetor<T> implements Lista<T> {

    private T[] elementos;
    private int tamanho;

    public Vetor(int capacidade) {
        elementos = (T[]) new Object[capacidade];
        tamanho = 0;
    }

    @Override
    public void adiciona(T elemento) {
        elementos[tamanho] = elemento;
        tamanho++;
    }

    @Override
    public T remove() {
        T elemento = elementos[tamanho - 1];
        tamanho--;
        return elemento;
    }

    public int obterTamanho() {
        return tamanho;
    }

    @Override
    public String toString() {
        String retorno = "[";
        if (tamanho > 0) {
            for (int i = 0; i < tamanho - 1; i++) {
                retorno += elementos[i] + ", ";
            }
            retorno += elementos[tamanho - 1];
        }
        return retorno + "]";
    }
}
