public class Edge {
	float weight;
	int src;
	int dst;

	Edge(float weight, int src, int dst) {
		this.dst = dst;
		this.src = src;
		this.weight = weight;
	}

	Edge() {
	}

	float getWeight() {
		return this.weight;
	}

	int getSrc() {
		return this.src;
	}

	int getDst() {
		return this.dst;
	}

}