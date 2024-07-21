package Graph;

public class Edge {
	private Vertex destination;
	private double timeCost;
	private double attractPoint;

	
	public Edge(Vertex destination, double timeCost, double attractPoint) {
		this.destination = destination;
        this.timeCost = timeCost;
        this.attractPoint = attractPoint;
	}
	public Vertex getDestination() {
		return destination;
	}//O(1)
	public void setDestination(Vertex destination) {
		this.destination = destination;
	}//O(1)
	public double getTimeCost() {
		return timeCost;
	}//O(1)
	public void setTimeCost(int timeCost) {
		this.timeCost = timeCost;
	}//O(1)
	public double getAttractPoint() {//O(1)
		return attractPoint;
	}
	public void setAttractPoint(int attractPoint) {
		this.attractPoint = attractPoint;
	}//O(1)

}
