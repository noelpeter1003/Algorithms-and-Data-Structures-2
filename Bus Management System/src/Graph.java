import java.util.LinkedList;

public class Graph {
	static int vertices;
	static LinkedList<Edge>[] adjacencylist;

	Graph(int vertices) {
		this.vertices = vertices;
		adjacencylist = new LinkedList[vertices];
		for (int i = 0; i < vertices; i++) {
			adjacencylist[i] = new LinkedList<>();
		}
	}

	public static void addEgde(int source, int destination, float weight) {
		Edge edge = new Edge(weight, destination, source);
		adjacencylist[source].addFirst(edge);
	}

	public static void printGraph() {
		for (int i = 0; i < vertices; i++) {
			LinkedList<Edge> list = adjacencylist[i];
			for (int j = 0; j < list.size(); j++) {
				System.out.println("vertex-" + i  + " is connected to " + list.get(j).dst + " with weight "
						+ list.get(j).weight);
			}
		}
	}
}
