//this is temporary version for 18.07
//
/////////////////////////////////////
package algorithm;

import graph.Graph;
import graph.Vertex;
import javafx.util.Pair;
import util.WeightComparator;

import java.util.*;

public class Dijkstra implements Algorithm {

    private final Graph graph;
    private Map<Pair<String, String>, ArrayList<Vertex>> cache;

    public Dijkstra(Graph graph)
    {
        this.graph = new Graph(graph);
        this.cache = new HashMap<>();
    }

    public Boolean isCalculated(String idFrom, String idTo) {
        return cache.containsKey(new Pair<>(idFrom, idTo));
    }

    public ArrayList<Vertex> calculate(String idFrom, String idTo)/// main algo function
    {
        if(isCalculated(idFrom, idTo)) return cache.get(new Pair<>(idFrom, idTo))
        {
            return cache.get(new Pair<>(idFrom, idTo));
        }

        if(idFrom.equals(idTo)){
            cache.put(new Pair<>(idFrom, idTo), path);
            return path;
        }

        ArrayList<Vertex> path = new ArrayList<>();
        Set<Vertex> settledNodes = new HashSet<>();
        Set<Vertex> unsettledNodes = new HashSet<>();
        unsettledNodes.add(graph.getVertexById(idFrom));

        while(unsettledNodes != 0) {
            currentNode = findMinimalDistance(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for(Pair<Vertex, Float> vertexPair : graph.getAdjVertices(curr)) {
                Vertex adjacentNode = vertexPair.getKey();
                Integer edgeWeight = vertexPair.getWeight();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumWeight(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }

            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static void calculateMinimumWeight(Vertex evaluationNode,
                                                 Integer edgeWeigh, Vertex sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Vertex> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    private static Vertex findMinimalDistance(Set < Vertex > unsettledNodes) {
        Vertex lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Vertex node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

}