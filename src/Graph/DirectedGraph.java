package Graph;

import java.util.HashMap;
import java.util.Iterator;

public class DirectedGraph {
    private HashMap<String, Vertex> vertices;

    public DirectedGraph() {
        this.vertices = new HashMap<>();
    }

    private String adjustPath(String lastPath, String willAddStr, int whereAdd) {//O(n)

        String[] words = lastPath.split(",");
        String returnedString = "";
        for (int i = 0; i < words.length; i++) {

            if (i == words.length - 1) {
                returnedString += words[i];
            } else {
                returnedString += words[i] + ",";
            }
            if (i == whereAdd) {
                returnedString += willAddStr + ",";
            }
        }
        return returnedString;
    }

    private double getHowMuchGain(Vertex source, Vertex target, Vertex added) throws Exception {//O(n)

        Edge currentEdge = getEdge(source, target);
        Edge addedEdge1 = getEdge(source, added);
        Edge addedEdge2 = getEdge(added, target);

        return addedEdge2.getAttractPoint() + addedEdge1.getAttractPoint() - currentEdge.getAttractPoint();
    }

    public void printMostAttractivePath(DirectedGraph tourMap, int n) throws Exception {//O(n^2)

        String[] memo = new String[n];
        memo[0] = "Hotel,Hotel";
        for (int i = 0; i < n - 1; i++) {
            double minValue = Integer.MIN_VALUE;
            int newVertexLoc = 0;
            Vertex addedVertex = vertices.get("Hotel").getUnvisitedNeighbor();
            addedVertex.visit();
            for (int j = 0; j < i + 1; j++) {

                Vertex currentVertex = tourMap.vertices.get(memo[i].split(",")[j]);
                Edge nextEdge = getEdge(currentVertex.getName(), memo[i].split(",")[j + 1]);
                if (nextEdge != null) {
                    if (getHowMuchGain(currentVertex, nextEdge.getDestination(), addedVertex) > minValue) {
                        minValue = getHowMuchGain(currentVertex, nextEdge.getDestination(), addedVertex);
                        newVertexLoc = j;
                    }
                }
            }
            memo[i + 1] = adjustPath(memo[i], addedVertex.getName(), newVertexLoc);
        }

        System.out.println("Here's the route for our tour : ");
        System.out.println(memo[memo.length - 1].replaceAll(",", " -> ") + "\n");
        double maxAttractivePoints = 0;
        double maxTime = 0;
        String[] finalValues = memo[memo.length - 1].split(",");
        for (int i = 0; i < finalValues.length - 1; i++) {
            Edge edge = getEdge(finalValues[i], finalValues[i + 1]);
            if (edge != null) {
                maxAttractivePoints += edge.getAttractPoint();
                maxTime += edge.getTimeCost();
            }
        }
        System.out.println("Attractiveness Score: " + maxAttractivePoints);
        System.out.println("Time Cost: " + maxTime);
    }

    public boolean IsVertexExist(String label) {//O(1)
        return this.vertices.containsKey(label);
    }

    public boolean IsEdgeExist(String source, String destination) throws Exception {//O(n)

        if (this.IsVertexExist(source) && this.IsVertexExist(destination)) {

            Vertex vertex1 = getVertex(source);
            Vertex vertex2 = getVertex(destination);

            Iterator<Vertex> sourceIterator = vertex1.getNeighborIterator();

            while (sourceIterator.hasNext()) {

                if (sourceIterator.next().compareTo(vertex2) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addVertex(String label, int timeCost, double visitorLoad) {//O(1)
        vertices.put(label, new Vertex(label, timeCost, visitorLoad));
    }

    public void addVertex(String label, String timeCost, String visitorLoad) {//O(1)
        vertices.put(label, new Vertex(label, Double.valueOf(timeCost), Double.valueOf(visitorLoad)));
    }

    public void addVertex(Vertex vertex) {//O(1)
        vertices.put(vertex.getName(), vertex);
    }

    public Vertex getVertex(String label) throws Exception {//O(1)

        if (IsVertexExist(label))
            return vertices.get(label);
        else
            throw new Exception("Vertex not found");
    }

    public Edge getEdge(String source, String destination) throws Exception {//O(n)

        return getEdge(getVertex(source), getVertex(destination));

    }

    public Edge getEdge(Vertex source, Vertex destination) throws Exception {//O(n)

        if (source != null && destination != null) {

            for (Edge edge : source.getEdges()) {
                if (edge.getDestination().compareTo(destination) == 0) {
                    return edge;
                }
            }
            return null;
        } else {
            throw new Exception("Vertexes that provided are invalid");
        }
    }

    public void addEdge(Vertex source, Vertex destination, String timeCost, String attractPoint) {//O(1)
        addEdge(source, destination, Double.valueOf(timeCost), Double.valueOf(attractPoint));
    }

    public void addEdge(Vertex source, Vertex destination, Double timeCost, Double attractPoint) {//O(1)

        if (source != null && destination != null && source.hasEdge(destination)) {
            System.out.println("This edge has already added!");
        } else {
            if (vertices.get(source) == null) {
                vertices.put(source.getName(), source);
            }
            if (vertices.get(destination) == null) {
                vertices.put(destination.getName(), destination);
            }
            double totalAttractiveness = attractPoint * destination.getPersonalInterest() * (1 - destination.getVisitorLoad());
            Edge edge = new Edge(destination, timeCost, totalAttractiveness);
            source.addEdge(edge);
        }
    }

    public void print() {//O(n^2)

        for (Vertex v : vertices.values()) {
            System.out.print(v.getName() + " -> " + v.getPersonalInterest() + " " + v.getVisitorLoad());

            Iterator<Vertex> neighbors = v.getNeighborIterator();
            while (neighbors.hasNext()) {
                Vertex n = neighbors.next();
                System.out.print(n.getName() + " ");
            }
            System.out.println();
        }
    }

    public Iterable<Vertex> vertices() {//O(1)
        return vertices.values();
    }
}
