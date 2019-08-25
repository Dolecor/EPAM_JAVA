package algorithm.BestFirstSearch;

import algorithm.Algorithm;
import graph.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBestFirstSearch implements Algorithm {

    protected final WeightedDigraph graph;
    protected Map<Pair<Integer, Integer>, ArrayList<BaseVertex>> cache;

    public AbstractBestFirstSearch(WeightedDigraph graph)
    {
        this.graph = new WeightedDigraph(graph);
        this.cache = new HashMap<>();
    }

    public Boolean isCalculated(Integer idSrc, Integer idDest)
    {
        return cache.containsKey(new Pair<>(idSrc, idDest));
    }

    protected ArrayList<BaseVertex> backTrack(BaseVertex vSrc, BaseVertex vDest, Map<BaseVertex, BaseVertex> cameFrom)
    {
        ArrayList<BaseVertex> path = new ArrayList<>();
        BaseVertex curr = vDest;
        while(curr != null){
            path.add(curr);
            curr = cameFrom.get(curr);
        }
        Collections.reverse(path);

        return path;
    }

    @Override
    public Float getPathWeight(ArrayList<BaseVertex> path)
    {
        float res = 0;
        if(path.size() < 1){
            res = -1.0f;
        }
        else if(path.size() > 1) {
            for(int i = 0; i != path.size() - 1; i++){
                res+=graph.getWeight(path.get(i).getId(), path.get(i+1).getId());
            }
        }
        return res;
    }

}
