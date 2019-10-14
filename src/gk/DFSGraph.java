package gk;

import java.util.HashMap;
import java.util.LinkedList;

public class DFSGraph {
    private HashMap<Node, LinkedList<Node>> adjacencyMap;
    private boolean directed;

    public DFSGraph(boolean directed) {
        this.directed = directed;
        adjacencyMap = new HashMap<Node, LinkedList<Node>>();
    }

    public void addEdgeHelper(Node a, Node b) {
        LinkedList<Node> tmp = adjacencyMap.get(a);

        if (tmp != null) {
            tmp.remove(b);
        }
        else tmp = new LinkedList<Node>();
        tmp.add(b);
        adjacencyMap.put(a, tmp);
    }

    public void addEdge(Node source, Node destination) {

        // We make sure that every used node shows up in our .keySet()
        if (!adjacencyMap.keySet().contains(source))
            adjacencyMap.put(source, null);

        if (!adjacencyMap.keySet().contains(destination))
            adjacencyMap.put(destination, null);

        addEdgeHelper(source, destination);

        // If a graph is undirected, we want to add an edge from destination to source as well
        if (!directed) {
            addEdgeHelper(destination, source);
        }
    }

    public void depthFirstSearchModified(Node node) {
        depthFirstSearch(node);

        for (Node n : adjacencyMap.keySet()) {
            if (!n.isVisited()) {
                depthFirstSearch(n);
            }
        }
    }

    public void depthFirstSearch(Node node) {
        node.visit();
        System.out.print(node.name + " ");

        LinkedList<Node> allNeighbors = adjacencyMap.get(node);
        if (allNeighbors == null)
            return;

        for (Node neighbor : allNeighbors) {
            if (!neighbor.isVisited())
                depthFirstSearch(neighbor);
        }
    }

    public void resetNodesVisited(DFSGraph graph) {
        for (Node ele : graph.adjacencyMap.keySet()){
            if (ele.visited == true){
                ele.unvisit();
            }
        }

    }
}
