import java.lang.reflect.Array;
import java.util.ArrayList;

public class EWGraph {

    int vertices;
    int sA, sB, sC;
    int edges;
    float [][] graph;

    public EWGraph(int vertices, int edges, int sA, int sB, int sC){
            graph= new float[vertices][vertices];
            for(int i=0; i<vertices; i++){
                for(int j=0; j<vertices; j++){
                    graph[i][j]=Float.POSITIVE_INFINITY;
                }
            }
            this.vertices=vertices;
            this.edges=edges;
            this.sA=sA;
            this.sB=sB;
            this.sC=sC;
    }


    int getVertices(){
        return vertices;
    }

    int getEdges() {
        return edges;
    }

    void addEdge(Edge newEdge){
        graph[newEdge.src][newEdge.dst]=newEdge.weight;
    }

}