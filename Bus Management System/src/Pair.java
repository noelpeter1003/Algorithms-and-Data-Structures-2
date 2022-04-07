class Pair implements Comparable<Pair> {
  String tripId;
  String stops;

  public Pair(String tpid) {
    tripId = tpid;
    stops = "";
  }

  public Pair(String tpid, String stps) {
    tripId = tpid;
    stops = stps;
  }

  @Override
  public int compareTo(Pair o) {
    return this.tripId.compareTo(o.tripId);
  }
}