package algorithm.BestFirstSearch;

import algorithm.Algorithm;
import graph.Graph;
import graph.Vertex;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBestFirstSearch implements Algorithm {

    protected final Graph graph;
    protected Map<Pair<String, String>, ArrayList<Vertex>> cache;

    public AbstractBestFirstSearch(Graph graph)
    {
        this.graph = new Graph(graph);
        this.cache = new HashMap<>();
    }

    public Boolean isCalculated(String idSrc, String idDest)
    {
        return cache.containsKey(new Pair<>(idSrc, idDest));
    }

    protected ArrayList<Vertex> backTrack(Vertex vSrc, Vertex vDest,  Map<Vertex, Vertex> cameFrom)
    {
        ArrayList<Vertex> path = new ArrayList<>();
        Vertex curr = vDest;
        while(curr != null){
            path.add(curr);
            curr = cameFrom.get(curr);
        }
        Collections.reverse(path);

        return path;
    }

    @Override
    public Float getPathWeight(ArrayList<Vertex> path)
    {
        float res = 0;
        if(path.size() < 1){
            res = -1.0f;
        }
        else if(path.size() > 1) {
            for(int i = 0; i != path.size() - 1; i++){
                res+=graph.getWeight(path.get(i), path.get(i+1));
            }
        }
        return res;
    }

}
