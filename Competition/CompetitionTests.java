import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CompetitionTests {

	@Test
	public void testGraphStuff() {
		EWGraph test = new EWGraph(8, 15, 56, 60, 70);
		Edge testEdge = new Edge(2, 0, 1);
		int dst = testEdge.getDst();
		int src = testEdge.getSrc();
		float weight = testEdge.getWeight();
		test.addEdge(testEdge);
		int edg = test.getEdges();
		assertEquals("testing get edge", 15, edg);
		int vert = test.getVertices();
		assertEquals("testing vert", 8, vert);

	}

	@Test
	public void testDijkstraConstructor() {

		new CompetitionDijkstra("tinyEWD.txt", 56, 60, 70);
	}

	@Test
	public void testTimeForCompDij() {
		// normal
		CompetitionDijkstra newG = new CompetitionDijkstra("tinyEWD.txt", 56, 60, 70);
		int actualResult = newG.timeRequiredforCompetition();
		assertEquals("Expecting 34", 34, actualResult);

		EWGraph empty = null;
		float result = newG.findLongestDistanceDij(empty);
		assertEquals("testing null graph", -1.0f, result);

		newG.graph = null;
		int newResult = newG.timeRequiredforCompetition();
		assertEquals("Testing null graph tie=me", -1, newResult);

	}

	@Test
	public void testFWConstructor() {
		new CompetitionFloydWarshall("tinyEWD.txt", 56, 60, 70);
	}

	@Test
	public void testTimeForCompFW() {
		CompetitionFloydWarshall newG = new CompetitionFloydWarshall("tinyEWD.txt", 56, 60, 70);
		int actualResult = newG.timeRequiredforCompetition();
		assertEquals("Expecting 34", 34, actualResult);

//		EWGraph empty = null;
//		float result = newG.findLongestDistance(empty);
//		assertEquals("testing null graph", -1.0f, result);
//
//		newG.graph = null;
//		int newResult = newG.timeRequiredforCompetition();
//		assertEquals("Testing null graph tie=me", -1, newResult);
	}

	// TODO - more tests

}