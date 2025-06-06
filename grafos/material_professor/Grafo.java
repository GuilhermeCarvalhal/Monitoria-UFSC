package grafos_apoio.material_professor;

public class Grafo {
    private int[][] matrizAdjacencia; // Matriz que representa as conexões
    private int V; // Número de vértices

    // Construtor: inicializa a matriz com V vértices
    public Grafo(int V) {
        this.V = V;
        this.matrizAdjacencia = new int[V][V];
    }

    // Adiciona uma aresta entre u e v
    public void adicionarAresta(int u, int v) {
        matrizAdjacencia[u][v] = 1;
        //matrizAdjacencia[v][u] = 1; // Para grafo não-direcionado
    }

    // Retorna o número de vértices
    public int getV() {
        return V;
    }

    // Verifica se existe uma aresta entre u e v
    public boolean isAdjacente(int u, int v) {
        return matrizAdjacencia[u][v] == 1;
    }

    // (Opcional) imprimir a matriz
    public void imprimirMatriz() {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(matrizAdjacencia[i][j] + " ");
            }
            System.out.println();
        }
    }
}

