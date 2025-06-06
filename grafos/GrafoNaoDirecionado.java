package grafos;

import java.util.*;

public class GrafoNaoDirecionado {
    private int[][] matrizAdjacencia;
    private int numVertices;

    // Construtor
    public GrafoNaoDirecionado(int numVertices) {
        this.numVertices = numVertices;
        matrizAdjacencia = new int[numVertices + 1][numVertices + 1]; 
    }

    // Adiciona uma aresta entre v1 e v2 (não direcionado)
    public void adicionarAresta(int v1, int v2) {
        // Verifica se os vértices são válidos
        if (v1 <= 0 || v1 > numVertices || v2 <= 0 || v2 > numVertices) {
            throw new IllegalArgumentException("Vértice inválido");
        }
        
        // Para grafo não direcionado, a matriz é simétrica
        matrizAdjacencia[v1][v2] = 1;
        matrizAdjacencia[v2][v1] = 1;
    }

    // Remove uma aresta
    public void removerAresta(int v1, int v2) {
        matrizAdjacencia[v1][v2] = 0;
        matrizAdjacencia[v2][v1] = 0;
    }

    // Verifica se existe aresta entre v1 e v2
    public boolean existeAresta(int v1, int v2) {
        return matrizAdjacencia[v1][v2] == 1;
    }

    // Obtém o grau de um vértice
    public int calcularGrau(int vertice) {
        int grau = 0;
        for (int i = 1; i <= numVertices; i++) {
            if (matrizAdjacencia[vertice][i] == 1) {
                grau++;
            }
        }
        return grau;
    }

    // Obtém os vizinhos de um vértice
    public List<Integer> obterVizinhos(int vertice) {
        List<Integer> vizinhos = new ArrayList<>();
        for (int i = 1; i <= numVertices; i++) {
            if (matrizAdjacencia[vertice][i] == 1) { //faz a comparação do vértice escolhido com todos os outros do grafo e retorna uma resposta
                vizinhos.add(i);
            }
        }
        return vizinhos;
    }

    public void imprimirGrafo() {
        // Imprime cabeçalho com rótulos dos vértices
        System.out.print("    ");
        for (int i = 1; i <= numVertices; i++) {
            System.out.printf("v%-3d", i); // Formata como v1, v2, etc.
        }
        System.out.println();
        
        // Imprime linhas separadoras
        System.out.print("   +");
        for (int i = 1; i <= numVertices; i++) {
            System.out.print("----");
        }
        System.out.println();
        
        // Imprime a matriz com rótulos
        for (int i = 1; i <= numVertices; i++) {
            System.out.printf("v%-2d|", i); // Rótulo da linha
            for (int j = 1; j <= numVertices; j++) {
                System.out.printf("%-4d", matrizAdjacencia[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Criando grafo com 5 vértices (como no slide 3)
        GrafoNaoDirecionado grafo = new GrafoNaoDirecionado(5);
        
        // Adicionando arestas (conexões do slide 3)
        grafo.adicionarAresta(1, 2);
        grafo.adicionarAresta(1, 3);
        grafo.adicionarAresta(2, 3);
        grafo.adicionarAresta(3, 4);
        grafo.adicionarAresta(4, 5);
        
        // Testando operações
        System.out.println("Vizinhos de 3: " + grafo.obterVizinhos(3)); // [1, 2, 4]
        System.out.println("Grau do vértice 3: " + grafo.calcularGrau(3)); // 3
        System.out.println("1 e 3 são adjacentes? " + grafo.existeAresta(1, 3)); // true
        System.out.println("1 e 5 são adjacentes? " + grafo.existeAresta(1, 5)); // false
        
        System.out.println("\nGrafo Não Direcionado:");
        grafo.imprimirGrafo();
    }
}
