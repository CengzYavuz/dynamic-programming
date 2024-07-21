package Graph;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Vertex implements Comparable<Vertex> {
	private String name;
	private ArrayList<Edge> edges;
	private boolean visited;
	private double personalInterest;
	private double visitorLoad;

	public Vertex(String name, double personalInterest, double visitorLoad) {
		this.name = name;
        this.personalInterest = personalInterest;
		this.visitorLoad = visitorLoad;
        edges = new ArrayList<Edge>();
		visited = false;
	}

	public void addEdge(Edge e) {
		edges.add(e);
	}//O(1)

	public ArrayList<Edge> getEdges() {
		return this.edges;
	}//O(1)

	public String getName() {
		return name;
	}//O(1)

	public void setName(String name) {
		this.name = name;
	}//O(1)

	public void visit() {
		this.visited = true;
	}//O(1)

	public void unvisit() {
		this.visited = false;
	}//O(1)

	public boolean isVisited() {
		return this.visited;
	}//O(1)

	public Vertex getUnvisitedNeighbor() {//O(n)
		Vertex result = null;

		Iterator<Vertex> neighbors = getNeighborIterator();
		while (neighbors.hasNext() && (result == null))
		{
			Vertex nextNeighbor = neighbors.next();
			if (!nextNeighbor.isVisited())
				result = nextNeighbor;
		} // end while

			return result;
	}

	public boolean hasEdge(Vertex neighbor) {//O(n)
		boolean found = false;
		Iterator<Vertex> neighbors = getNeighborIterator();
		while (neighbors.hasNext())
		{
			Vertex nextNeighbor = neighbors.next();
			if (nextNeighbor.getName().equalsIgnoreCase(neighbor.getName()) &&
				nextNeighbor.getVisitorLoad() == neighbor.getVisitorLoad() &&
				nextNeighbor.getPersonalInterest() == neighbor.getPersonalInterest())
			{
				found = true;
				break;
			}
		} // end while

		return found;
	}


	public double getPersonalInterest() {
		return personalInterest;
	}//O(1)

	public void setPersonalInterest(double personalInterest) {
		this.personalInterest = personalInterest;
	}//O(1)

	public double getVisitorLoad() {
		return visitorLoad;
	}//O(1)

	public boolean IsHaveUnvisitedNeighbors() {
		return getNeighborIterator().hasNext();
	}//O(1)
	public void setVisitorLoad(double visitorLoad) {
		this.visitorLoad = visitorLoad;
	}//O(1)
	public Iterator<Vertex> getNeighborIterator()
	{
		return new NeighborIterator();
	} // end getNeighborIterator//O(1)

	public int compareTo(Vertex compare) {//O(1)

		if(this.name.equalsIgnoreCase(compare.getName()) &&
				this.getVisitorLoad() == compare.getVisitorLoad() &&
				this.getPersonalInterest() == compare.getPersonalInterest()) {
			return 0;
		}else if(this.name.equalsIgnoreCase(compare.getName()) &&
				this.getVisitorLoad() > compare.getVisitorLoad() &&
				this.getPersonalInterest() > compare.getPersonalInterest()){
			return 1;
		} else {
			return -1;
		}
	}

	private class NeighborIterator implements Iterator<Vertex>
	{
		int edgeIndex;
		private NeighborIterator()
		{
			edgeIndex = 0;
		} // end default constructor//O(1)


		public boolean hasNext()
		{
			return edgeIndex < edges.size();
		} // end hasNext//O(1)

		public Vertex next()//O(1)
		{
			Vertex nextNeighbor = null;

			if (hasNext())
			{
				nextNeighbor = edges.get(edgeIndex).getDestination();
				edgeIndex++;
			}
			else
				throw new NoSuchElementException();

			return nextNeighbor;
		} // end next

		public void remove()
		{
			throw new UnsupportedOperationException();
		} // end remove//O(1)
	} // end NeighborIterator
}
