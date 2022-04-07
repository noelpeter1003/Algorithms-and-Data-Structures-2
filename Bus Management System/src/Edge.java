public class Edge {
	int from;
	int to;
	float weight;

	Edge(int from, int to, float weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	Edge() {
	}

	float getWeight() {
		return this.weight;
	}

	int getFrom() {
		return this.from;
	}

	int getTo() {
		return this.to;
	}
}
