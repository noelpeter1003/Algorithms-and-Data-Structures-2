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
 * This class implements the competition using Floyd-Warshall algorithm
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CompetitionFloydWarshall {
	EWGraph graph;
	int sA, sB, sC;

	float[][] floydWarshall(EWGraph graphObj) {
		float[][] graphFW = graphObj.graph;
		int V = graphObj.vertices;
		float dist[][] = new float[V][V];
		int i, j, k;

		for (i = 0; i < V; i++) {
			// System.out.println("me again");
			for (j = 0; j < V; j++) {
				dist[i][j] = graphFW[i][j];
			}
		}
		for (i = 0; i < V; i++) { // initialising distance from v to v as 0 to override infinity
			if (dist[i][i] >= 0.0)
				dist[i][i] = 0;

		}

		for (k = 0; k < V; k++) {
			for (i = 0; i < V; i++) {
				for (j = 0; j < V; j++) {
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
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
	CompetitionFloydWarshall(String filename, int sA, int sB, int sC) {
		// reading in file input
		if (filename != null) {
			try {
				FileReader fr = new FileReader(filename);
				BufferedReader br = new BufferedReader(fr);

				int N = Integer.parseInt(br.readLine());
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
			int slowestSpeed = findSlowestWalker(graph.sA, graph.sB, graph.sC);
			System.out.println(slowestSpeed);
			float longestDistance = findLongestDistance(graph);
			if (longestDistance == -1)
				return -1;
			System.out.println(longestDistance);
			return (int) Math.ceil((longestDistance * 1000) / slowestSpeed);
		} else
			return -1;
	}

	float findLongestDistance(EWGraph graph) {
		if (graph != null) {
			float longestDist = 0;
			float[][] distances = floydWarshall(graph);
			for (int i = 0; i < distances.length; i++) {
				for (int j = 0; j < distances.length; j++) {
					if (distances[i][j] > longestDist)
						longestDist = distances[i][j];
					if (distances[i][j] == Float.POSITIVE_INFINITY && i != j)
						return -1;
				}
			}
			return longestDist;
		} else
			return -1;
	}

	// method to find slowest walker
	int findSlowestWalker(int sA, int sB, int sC) {

		return Math.min(sA, Math.min(sB, sC));
	}

}