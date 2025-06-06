package grafos_apoio.material_professor;

public class Main {
    public static void main(String[] args) {
        // Criando grafo com 6 vértices (índices 0 a 5)
        Grafo g = new Grafo(6);

        // Adicionando arestas conforme seu grafo visual
        g.adicionarAresta(1, 3);
        g.adicionarAresta(1, 2);
        g.adicionarAresta(3, 4);
        g.adicionarAresta(4, 5);

        // Inicia a busca a partir do vértice 1
        BuscaLargura bl = new BuscaLargura(g);
        bl.busca(1); //Origem da busca
        bl.getAllPaths();
    }
}
