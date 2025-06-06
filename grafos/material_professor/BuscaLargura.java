package grafos.material_professor;

import java.util.ArrayList;
import java.util.Collections;

public class BuscaLargura {

    // Constantes para representar cores dos vértices
    private static final int BRANCO = 0; // Não visitado
    private static final int CINZA = 1;  // Visitado, mas ainda com vizinhos a explorar
    private static final int PRETO = 2;  // Totalmente visitado

    private int origem;                 // Vértice de origem da busca
    private Grafo grafo;               // Referência ao grafo que será percorrido
    private int cor[];                 // Vetor de cores para o estado dos vértices
    private int distancia[];           // Vetor de distâncias desde a origem
    private int predecessor[];         // Vetor que armazena o pai de cada vértice

    // Construtor que recebe o grafo e inicializa os vetores de controle
    public BuscaLargura(Grafo g){
        this.grafo = g;
        this.cor = new int[g.getV()];
        this.distancia = new int[g.getV()];
        this.predecessor = new int[g.getV()];
    }

    // Método principal da busca em largura (BFS)
    public void busca(int o){
        this.origem = o; // Define o vértice de origem
        FilaEncadeada fila = new FilaEncadeada(); // Cria fila auxiliar para a BFS

        // Inicializa todos os vértices como não visitados
        for (int i = 0; i < grafo.getV(); i++){
            this.cor[i] = BRANCO;
            this.distancia[i] = 0;
            this.predecessor[i] = -1;
        }

        // Começa pela origem
        fila.insereFinal(origem);
        System.out.println("inseriu origem " + origem);

        // Enquanto a fila não estiver vazia
        while (!fila.vazia()){
            int atual = fila.retiraInicio(); // Remove o primeiro da fila
            System.out.println("atual " + atual);

            // Verifica todos os vizinhos do vértice atual
            for (int i = 0; i < grafo.getV(); i++){
                if (grafo.isAdjacente(atual, i)){ // Se há aresta atual → i
                    if (cor[i] == BRANCO){ // Se o vértice ainda não foi visitado
                        cor[i] = CINZA; // Marca como descoberto
                        predecessor[i] = atual; // Define o pai
                        distancia[i] = distancia[atual] + 1; // Calcula distância
                        fila.insereFinal(i); // Adiciona à fila para visitar seus vizinhos depois
                    }
                }
            }
            cor[atual] = PRETO; // Marca o vértice como totalmente processado
        }
    }

    // Imprime o caminho da origem até o vértice i
    public void getPath(int i){
        int temp = i;
        ArrayList<Integer> lista = new ArrayList<>();
        lista.add(temp); // Começa pelo destino

        // Caminha até a origem usando os predecessores
        while (predecessor[temp] != -1){
            temp = predecessor[temp];
            lista.add(temp);
        }

        // Imprime o caminho
        System.out.print("Origem = "+ this.origem + " Destino = "+ i +
                         " | distancia = " + distancia[i] + " | caminho = ");
        Collections.reverse(lista); // Inverte para mostrar da origem até o destino
        for (int j: lista){
            System.out.print(j + "->");
        }
        System.out.println();
    }

    // Imprime os caminhos da origem até todos os outros vértices
    void getAllPaths(){
        for (int i = 0 ; i < grafo.getV(); i++)
            getPath(i);
    }

    // Método para retornar a representação dos vetores internos
    @Override
    public String toString(){
        String aux = "cores\n";
        for (int i = 0; i < grafo.getV(); i++){
            aux += cor[i] + " ";
        }
        aux += "\npredecessores\n";
        for (int i = 0; i < grafo.getV(); i++){
            aux += predecessor[i] + " ";
        }
        aux += "\ndistancia\n";
        for (int i = 0; i < grafo.getV(); i++){
            aux += distancia[i] + " ";
        }
        aux += "\n";
        return aux;
    }
}
