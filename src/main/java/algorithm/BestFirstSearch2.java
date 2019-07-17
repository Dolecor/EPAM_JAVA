package algorithm;

import graph.Graph;
import graph.Vertex;
import javafx.util.Pair;
import util.DistanceComparator;

import java.util.*;

/**
 * The class represents the algorithm for finding the path from
 * the source vertex to the destination vertex.
 * Principle: select the next vertex on the principle
 * of least distance between current vertex and destination vertex.
 */
public class BestFirstSearch2 implements Algorithm {

    private final Graph graph;
    private Map<Pair<String, String>, ArrayList<Vertex>> cache;
    private final Map<String, Pair<Integer, Integer>> coords;

    public BestFirstSearch2(Graph graph, Map<String, Pair<Integer, Integer>> coords)
    {
        this.graph = new Graph(graph);
        this.cache = new HashMap<>();
        this.coords = coords;
    }

    @Override
    public ArrayList<Vertex> calculate(String idSrc, String idDest) {
        if(isCalculated(idSrc, idDest)){
            return cache.get(new Pair<>(idSrc, idDest));
        }

        ArrayList<Vertex> path = new ArrayList<>();
        Vertex dest = graph.getVertexById(idDest);
        Vertex src = graph.getVertexById(idSrc);
        Vertex curr = src;

        if(idSrc.equals(idDest)){
            path.add(curr);
            cache.put(new Pair<>(idSrc, idDest), path);
            return path;
        }

        List<Vertex> close = new ArrayList<>();
        PriorityQueue<Pair<Vertex, Float>> open = new PriorityQueue<>(new DistanceComparator());
        open.add(new Pair<>(curr, -1.0f));

        while(!open.isEmpty()){
            curr = open.poll().getKey();
            close.add(curr);
            if(curr.equals(dest)){
                path = backTrack(src, dest);
                cache.put(new Pair<>(idSrc, idDest), path);
                return path;
            }
            for(Pair<Vertex, Float> p : graph.getAdjVertices(curr)){
                if(!close.contains(p.getKey()) && p.getKey().getPredecessor() == null) {
                    open.add(new Pair<>(p.getKey(), distanceToDest(p.getKey(), dest)));
                    p.getKey().setPredecessor(curr);
                }
            }
        }

        return null;
    }

    public Boolean isCalculated(String idSrc, String idDest)
    {
        return cache.get(new Pair<>(idSrc, idDest)) != null;
    }

    private ArrayList<Vertex> backTrack(Vertex vSrc, Vertex vDest)
    {
        ArrayList<Vertex> path = new ArrayList<>();
        Vertex curr = vDest;
        while(!curr.equals(vSrc)){
            path.add(curr);
            curr = curr.getPredecessor();
        }
        Collections.reverse(path);

        return path;
    }

    public Float getPathWeight(ArrayList<Vertex> path)
    {
        float res = 0;
        for(int i = 0; i != path.size() - 2; i++){
            res+=graph.getWeight(path.get(i), path.get(i+1));
        }
        return res;
    }

    private Float distanceToDest(Vertex v, Vertex vDest){
        Pair<Integer, Integer> coordsV = coords.get(v.getId());
        Pair<Integer, Integer> coordsDest = coords.get(vDest.getId());
        return (float)Math.sqrt(Math.pow(coordsV.getKey() - coordsDest.getKey(), 2) +
                                Math.pow(coordsV.getValue() - coordsDest.getValue(), 2));
    }
}
