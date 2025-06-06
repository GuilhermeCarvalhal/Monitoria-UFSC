package grafos.material_professor;

import java.util.LinkedList;

public class FilaEncadeada {
    private LinkedList<Integer> fila;

    // Construtor inicializa a fila
    public FilaEncadeada() {
        fila = new LinkedList<>();
    }

    // Insere no final da fila
    public void insereFinal(int valor) {
        fila.addLast(valor);
    }

    // Remove e retorna o elemento do início da fila
    public int retiraInicio() {
        return fila.removeFirst();
    }

    // Verifica se a fila está vazia
    public boolean vazia() {
        return fila.isEmpty();
    }
}
