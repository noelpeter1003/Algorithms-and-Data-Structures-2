import java.util.*;

class Graph {
	public static int vertices;
	public static int edges;
	public static List<List<Node>> adjList = new ArrayList<List<Node>>();

	Graph(int vertices, int edges) {
		Graph.vertices = vertices;
		Graph.edges = edges;
		for (int i = 0; i < Graph.vertices; i++) {
			adjList.add(new ArrayList<Node>());
		}
	}

	public static class Node implements Comparator<Node> {
		int index;
		double weight;

		Node() {
		}

		Node(int index, double weight) {
			this.index = index;
			this.weight = weight;
		}

		// The type Graph.Node must implement the inherited abstract method
		// Comparator<Graph.Node>.compare(Graph.Node, Graph.Node)
		public int compare(Node node1, Node node2) {
			if (node1.weight < node2.weight) {
				return -1;
			} else if (node1.weight > node2.weight) {
				return 1;
			}
			return 0;
		}
	}

	void addNode(int src, int dst, double weight) {
		Node newNode = new Node(dst, weight);
		adjList.get(src).add(newNode);
	}

	static String[] djikstrasAlgorithm(int src, int des) {
		boolean nodesAdded[] = new boolean[vertices];
		Arrays.fill(nodesAdded, false);
		nodesAdded[src] = true;
		double distTo[] = new double[vertices];
		Arrays.fill(distTo, Double.POSITIVE_INFINITY);
		distTo[src] = 0;
		int prev[] = new int[vertices];
		prev[src] = src;
		Arrays.fill(prev, -1);
		PriorityQueue<Node> nodeQueue = new PriorityQueue<Node>(vertices, new Node());
		nodeQueue.add(new Node(src, 0));
		while (nodeQueue.size() > 0) {
			Node currentNode = nodeQueue.poll();
			for (int i = 0; i < adjList.get(currentNode.index).size(); i++) {
				Node newNode = adjList.get(currentNode.index).get(i);
				if (!nodesAdded[newNode.index]) {
					if (distTo[currentNode.index] + newNode.weight < distTo[newNode.index]) {
						distTo[newNode.index] = distTo[currentNode.index] + newNode.weight;
						prev[newNode.index] = currentNode.index;
					}
					nodesAdded[newNode.index] = true;
					nodeQueue.add(new Node(newNode.index, distTo[newNode.index]));
				}
			}
		}
		int stopsInBetween = 0;
		for (int stopID = des; stopID != src; stopID = prev[stopID]) {
			stopsInBetween++;
		}
		String[] stops = new String[stopsInBetween + 3];
		int index = 3;
		for (int stopID = des; stopID != src; stopID = prev[stopID]) {
			stops[index] = prev[stopID] + " -> " + stopID + "	cost: " + (distTo[stopID] - distTo[prev[stopID]]);
			index++;
		}
		stops[0] = "***********************************";
		stops[1] = src + " -> " + des + "	Total Cost: " + distTo[des];
		stops[2] = "***********************************";
		String[] stopsReversed = new String[stopsInBetween + 3];
		for (int i = 0; i < stopsReversed.length; i++) {
			stopsReversed[stopsReversed.length - 1 - i] = stops[i];
		}
		stops = stopsReversed;
		return stops;
	}

}
