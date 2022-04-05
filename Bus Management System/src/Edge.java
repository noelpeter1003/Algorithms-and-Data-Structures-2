public class Edge {
	float weight;
	int source;
	int destination;

	Edge(float weight, int src, int dst) {
		this.destination = dst;
		this.source = src;
		this.weight = weight;
	}

	Edge() {
	}

	float getWeight() {
		return this.weight;
	}

	int getSource() {
		return this.source;
	}

	int getDestination() {
		return this.destination;
	}

}