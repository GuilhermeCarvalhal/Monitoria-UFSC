package grafos;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;


public class BuscaGrafoMatriz { //Busca em largura recursiva
    private int[][] matrizAdjacencia;
    private int numVertices;
    private boolean direcionado;

    public BuscaGrafoMatriz(int numVertices, boolean direcionado) {
        this.numVertices = numVertices;
        this.direcionado = direcionado;
        matrizAdjacencia = new int[numVertices + 1][numVertices + 1];
    }

    public void adicionarAresta(int v1, int v2) {
        if (v1 <= 0 || v1 > numVertices || v2 <= 0 || v2 > numVertices) {
            throw new IllegalArgumentException("Vértice inválido");
        }
        matrizAdjacencia[v1][v2] = 1;
        if (!direcionado) { //verifica se escolhemos que o grafo seja indirecionado
            matrizAdjacencia[v2][v1] = 1;
        }
    }

    public List<Integer> encontrarCaminho(int inicio, int destino) {
        Queue<Integer> fila = new LinkedList<>(); //Cria uma fila para fazer uma Busca em Largura
        int[] pais = new int[numVertices + 1]; //Vetor para armazenar quem é o "pai" de cada vértice no caminho, usado depois para reconstruir o trajeto.
        boolean[] visitados = new boolean[numVertices + 1]; //Vetor usado para marcar quem já foi visitado
        
        fila.add(inicio); //Começa a busca a partir do vértice inicio, marcando como visitado e dizendo que ele não tem pai (pois é o início).
        visitados[inicio] = true;
        pais[inicio] = -1;
        
        while (!fila.isEmpty()) { //Enquanto a fila não estiver vazia, remove o primeiro da fila (FIFO). First-in-First-Out
            int atual = fila.poll();
            
            if (atual == destino) { //Se o vértice atual é o destino, termina a busca e reconstrói o caminho.
                return reconstruirCaminho(pais, destino);
            }
            
            for (int vizinho = 1; vizinho <= numVertices; vizinho++) { //Para cada vizinho do vértice atual:
                if (matrizAdjacencia[atual][vizinho] == 1 && !visitados[vizinho]) { //Se há uma aresta (matrizAdjacencia[atual][vizinho] == 1)
                    visitados[vizinho] = true; // e ele ainda não foi visitado, marca como visitado, armazena o pai e adiciona na fila.
                    pais[vizinho] = atual;
                    fila.add(vizinho);
                }
            }
        }
        return Collections.emptyList(); //Se a fila acabar sem encontrar o destino, retorna uma lista vazia (não há caminho).
    }
    
    private List<Integer> reconstruirCaminho(int[] pais, int destino) { //Após o caminho ser encontrado, ele será reconstruído
        List<Integer> caminho = new ArrayList<>();
        int atual = destino;
        while (atual != -1) { //Enquanto não chegar no início (pai == -1), adiciona cada vértice no início da lista (ordem correta do caminho).
            caminho.add(0, atual);
            atual = pais[atual];
        }
        return caminho; //Valor retornado no encontrar caminho
    }
    
    public boolean temCiclo() {
        boolean[] visitados = new boolean[numVertices + 1]; //marca os vértices já explorados.
        boolean[] naPilha = new boolean[numVertices + 1]; //marca os vértices atualmente na pilha de recursão, usado para detectar ciclos em grafos direcionados.
        
        for (int vertice = 1; vertice <= numVertices; vertice++) {
            if (!visitados[vertice] && temCicloDFS(vertice, visitados, naPilha, -1)) { //Para cada vértice não visitado, executa DFS recursiva. Se detectar ciclo, retorna true.
                return true;
            }
        }
        return false;
    }
    
    private boolean temCicloDFS(int vertice, boolean[] visitados, boolean[] naPilha, int pai) {
        visitados[vertice] = true; //Marca o vértice como visitado e indica que ele está na pilha de recursão.
        naPilha[vertice] = true;
        
        for (int vizinho = 1; vizinho <= numVertices; vizinho++) { //Para cada vizinho conectado ao vértice atual:
            if (matrizAdjacencia[vertice][vizinho] == 1) { //Se o vizinho ainda não foi visitado, chama recursivamente.
                if (!visitados[vizinho]) {
                    if (temCicloDFS(vizinho, visitados, naPilha, direcionado ? -1 : vertice)) {
                        return true;
                    }
                } else if ((direcionado && naPilha[vizinho]) || //Direcionado: se o vizinho já estiver na pilha → existe ciclo.
                          (!direcionado && vizinho != pai)) { //Não direcionado: existe ciclo se o vizinho já foi visitado e não é o pai.
                    return true;
                }
            }
        }
        
        if (direcionado) {
            naPilha[vertice] = false; //Ao sair da recursão, remove o vértice da pilha (apenas se for direcionado).
        }
        return false;
    }

    public void imprimirMatrizRotulada() {
        System.out.print("    ");
        for (int i = 1; i <= numVertices; i++) {
            System.out.printf("v%-3d", i);
        }
        
        // Substituição do repeat() por loop
        System.out.print("\n   +");
        for (int i = 1; i <= numVertices; i++) {
            System.out.print("----");
        }
        System.out.println();
        
        for (int i = 1; i <= numVertices; i++) {
            System.out.printf("v%-2d|", i);
            for (int j = 1; j <= numVertices; j++) {
                System.out.printf("%-4d", matrizAdjacencia[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BuscaGrafoMatriz grafo = new BuscaGrafoMatriz(5, true);
        
        grafo.adicionarAresta(1, 2);
        grafo.adicionarAresta(3, 1); //Remova para entender
        grafo.adicionarAresta(2, 3);
        grafo.adicionarAresta(3, 4);
        grafo.adicionarAresta(4, 5);
        grafo.adicionarAresta(5, 5); //Self-Loof conta como ciclo
        
        System.out.println("Matriz de Adjacência:");
        grafo.imprimirMatrizRotulada();
        
        System.out.println("\nCaminho de 3 para 5: " + grafo.encontrarCaminho(3, 5));
        System.out.println("\nCaminho de 3 para 5: " + grafo.encontrarCaminho(5, 5));
        System.out.println("O grafo tem ciclos? " + grafo.temCiclo());
    }
}