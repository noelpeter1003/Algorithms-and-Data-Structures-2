
/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */
import java.io.*;

public class CompetitionDijkstra {

	int sA, sB, sC;
	EWGraph graph;

	int minDistance(float dist[], Boolean sptSet[], int V) {
		// Initialize min value
		float min = Integer.MAX_VALUE;
		int min_index = -1;

		for (int v = 0; v < V; v++)
			if (sptSet[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}

		return min_index;
	}

	float[] dijkstra(EWGraph graphObj, int src) {
		int V = graphObj.vertices;
		float[][] graph = graphObj.graph;

		float dist[] = new float[V]; // output: shortest path src to i
		Boolean included[] = new Boolean[V]; // vertex shortest path found
		// initialisation
		for (int i = 0; i < V; i++) {
			dist[i] = Float.POSITIVE_INFINITY;
			included[i] = false;
		}
		dist[src] = 0;

		// Find shortest path for all vertices
		for (int count = 0; count < V - 1; count++) {
			int temp = minDistance(dist, included, V);

			// put u in included
			if (temp != -1) {
				included[temp] = true;
				// Update dist value temp to other vertices
				for (int v = 0; v < V; v++) {
					if (!included[v] && graph[temp][v] != Float.POSITIVE_INFINITY
							&& dist[temp] != Float.POSITIVE_INFINITY && dist[temp] + graph[temp][v] < dist[v]) {
						dist[v] = dist[temp] + graph[temp][v];
					}
				}

			}
		}

		return dist;
	}

	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA,       sB, sC: speeds for 3 contestants
	 */
	CompetitionDijkstra(String filename, int sA, int sB, int sC) {
		// reading in file input
		int N = 0;
		if (filename != null) {
			try {
				FileReader fr = new FileReader(filename);
				BufferedReader br = new BufferedReader(fr);

				N = Integer.parseInt(br.readLine());
				int S = Integer.parseInt(br.readLine());
				graph = new EWGraph(N, S, sA, sB, sC);
				Edge temp = new Edge();
				for (int i = 0; i < S; i++) {
					String line = br.readLine();
					String[] parameters = line.trim().split("\\s+");
					temp.src = Integer.parseInt(parameters[0]);
					temp.dst = Integer.parseInt(parameters[1]);
					temp.weight = Float.parseFloat(parameters[2]);
					graph.addEdge(temp);

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	float findLongestDistanceDij(EWGraph graph) {
		if (graph != null) {
			float longestDist = 0;
			float[] distances;
			for (int i = 0; i < graph.vertices; i++) {
				distances = dijkstra(graph, i);
				for (int j = 0; j < distances.length; j++) {
					if (distances[j] > longestDist)
						longestDist = distances[j];
					if (distances[j] == Float.POSITIVE_INFINITY && i != j)
						return -1;
				}
			}
			return longestDist;
		} else
			return -1;
	}

	// method to find slowest walker
	int findSlowestWalkerDij(int sA, int sB, int sC) {
		return Math.min(sA, Math.min(sB, sC));
	}

	/**
	 * @return int: minimum minutes that will pass before the three contestants can
	 *         meet
	 */
	public int timeRequiredforCompetition() {
		if (graph != null) {
			if (graph.sA < 50 || graph.sB < 50 || graph.sC < 50)
				return -1;
			if (graph.sA > 100 || graph.sB > 100 || graph.sC > 100)
				return -1;
			if (graph.getVertices() == 0)
				return -1;
			if (graph.getEdges() == 0)
				return -1;
			int slowestSpeed = findSlowestWalkerDij(graph.sA, graph.sB, graph.sC);
			System.out.println(slowestSpeed);
			float longestDistance = findLongestDistanceDij(graph);
			if (longestDistance == -1)
				return -1;
			System.out.println(longestDistance);
			return (int) Math.ceil((longestDistance * 1000) / slowestSpeed);
		} else
			return -1;

	}
}