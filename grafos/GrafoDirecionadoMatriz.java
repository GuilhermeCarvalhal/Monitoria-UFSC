package grafos_apoio;

public class GrafoDirecionadoMatriz {
    private int[][] matrizAdjacencia;
    private int numVertices;

    public GrafoDirecionadoMatriz(int numVertices) {
        this.numVertices = numVertices;
        matrizAdjacencia = new int[numVertices + 1][numVertices + 1];
    }

    public void adicionarArestaDirecionada(int origem, int destino) { //Direcionado, logo faremos um caminho de um verice orignem parao v destino
        if (origem <= 0 || origem > numVertices || destino <= 0 || destino > numVertices) {
            throw new IllegalArgumentException("Vértice inválido");
        }
        matrizAdjacencia[origem][destino] = 1;
    }

    public boolean existeCaminho(int inicio, int destino) {
        boolean[] visitados = new boolean[numVertices + 1];
        return dfs(inicio, destino, visitados);
    }

    private boolean dfs(int atual, int destino, boolean[] visitados) { //DFS – Depth-First Search Profundidade | Recursividade
        if (atual == destino) return true; //Verifica se chegou ao destino
        visitados[atual] = true; //Marca o vértice atual como visitado
        
        for (int vizinho = 1; vizinho <= numVertices; vizinho++) { //Percorre todos os possíveis vizinhos
            if (matrizAdjacencia[atual][vizinho] == 1 && !visitados[vizinho]) { // Verifica se há aresta e se o vizinho ainda não foi visitado
                if (dfs(vizinho, destino, visitados)) { //Chamada recursiva (DFS)
                    return true; //Se for encontrado um caminho será retornado true
                }
            }
        }
        return false; //Se não for encontrado, retornará false
    }

    public void imprimirMatrizRotulada() {
        System.out.print("    ");
        for (int i = 1; i <= numVertices; i++) {
            System.out.printf("v%-3d", i);
        }
        
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
        GrafoDirecionadoMatriz grafo = new GrafoDirecionadoMatriz(5);
        
        grafo.adicionarArestaDirecionada(1, 2);
        grafo.adicionarArestaDirecionada(2, 3);
        grafo.adicionarArestaDirecionada(1, 3);
        grafo.adicionarArestaDirecionada(3, 4);
        grafo.adicionarArestaDirecionada(4, 5);
        grafo.adicionarArestaDirecionada(5, 5);
        
        System.out.println("Grafo Direcionado:");
        grafo.imprimirMatrizRotulada();
        
        System.out.println("\nExiste caminho de 3 para 5? " + grafo.existeCaminho(3, 5));
        System.out.println("Existe caminho de 5 para 3? " + grafo.existeCaminho(5, 3));
        System.out.println("Existe caminho de 5 para 5? " + grafo.existeCaminho(5, 5));
    }
}