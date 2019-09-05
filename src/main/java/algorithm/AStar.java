package algorithm;

import algorithm.BestFirstSearch.util.DistanceComparator;
import graph.BaseVertex;
import graph.WeightedDigraph;
import javafx.util.Pair;

import java.util.*;

public class AStar implements Algorithm {
    private final WeightedDigraph graph;
    private Map<Pair<Integer, Integer>, ArrayList<BaseVertex>> cache;
    private final Map<Integer, Pair<Integer, Integer>> coords;

    public AStar(WeightedDigraph graph, Map<Integer, Pair<Integer, Integer>> coords)
    {
        this.graph = new WeightedDigraph(graph);
        this.cache = new HashMap<>();
        this.coords = coords;
    }

    @Override
    public ArrayList<BaseVertex> calculate(Integer idSrc, Integer idDest) {
        if(isCalculated(idSrc, idDest)){
            return cache.get(new Pair<>(idSrc, idDest));
        }

        ArrayList<BaseVertex> path = new ArrayList<>();
        BaseVertex dest = graph.getVertexById(idDest);
        BaseVertex src = graph.getVertexById(idSrc);
        BaseVertex curr = src;

        if(idSrc.equals(idDest)){
            path.add(curr);
            cache.put(new Pair<>(idSrc, idDest), path);
            return path;
        }

        Map<BaseVertex, BaseVertex> cameFrom = new HashMap<>();
        PriorityQueue<Pair<BaseVertex, Float>> open = new PriorityQueue<>(new DistanceComparator());
        Map<BaseVertex, Float> currWeight = new HashMap<>();
        open.add(new Pair<>(curr, -1.0f));
        cameFrom.put(curr, null);
        currWeight.put(curr, 0f);

        while(!open.isEmpty()) {
            curr = open.poll().getKey();
            if(curr.equals(dest)){
                path = backTrack(src, dest, cameFrom);
                cache.put(new Pair<>(idSrc, idDest), path);
                break;
            }
            for(Pair<BaseVertex, Float> adj : graph.getAdjVerticesWithWeights(curr.getId())) {
                Float newWieght = currWeight.get(curr) + graph.getWeight(curr.getId(), dest.getId());
                if(currWeight.get(adj.getKey()) == null || newWieght < currWeight.get(adj.getKey())){
                    currWeight.put(adj.getKey(), newWieght);
                    open.add(new Pair<>(adj.getKey(), distanceToDest(adj.getKey(), dest)));
                    cameFrom.put(adj.getKey(), curr);
                }
            }
        }

        return path;
    }

    @Override
    public Float getPathWeight(ArrayList<BaseVertex> path) {
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

    public Boolean isCalculated(Integer idSrc, Integer idDest)
    {
        return cache.containsKey(new Pair<>(idSrc, idDest));
    }

    private ArrayList<BaseVertex> backTrack(BaseVertex vSrc, BaseVertex vDest, Map<BaseVertex, BaseVertex> cameFrom)
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

    private Float distanceToDest(BaseVertex v, BaseVertex vDest){
        Pair<Integer, Integer> coordsV = coords.get(v.getId());
        Pair<Integer, Integer> coordsDest = coords.get(vDest.getId());
        return (float)Math.sqrt(Math.pow(coordsV.getKey() - coordsDest.getKey(), 2) +
                Math.pow(coordsV.getValue() - coordsDest.getValue(), 2));
    }
}
