package algorithm.BestFirstSearch;

import graph.BaseVertex;
import graph.Vertex;
import graph.WeightedDigraph;
import javafx.util.Pair;
import algorithm.BestFirstSearch.util.WeightComparator;

import java.util.*;

/**
 * The class represents the algorithm for finding the path from
 * the source vertex to the destination vertex.
 * Principle: select the next vertex on the principle
 * of least weight.
 */
public class BestFirstSearch extends AbstractBestFirstSearch {

    public BestFirstSearch(WeightedDigraph graph)
    {
        super(graph);
    }

    @Override
    public ArrayList<BaseVertex> calculate(Integer idSrc, Integer idDest)
    {
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
        PriorityQueue<Pair<BaseVertex, Float>> open = new PriorityQueue<>(new WeightComparator());
        open.add(new Pair<>(curr, -1.0f));
        cameFrom.put(curr, null);

        while(!open.isEmpty()) {
            curr = open.poll().getKey();
            if(curr.equals(dest)){
                path = backTrack(src, dest, cameFrom);
                cache.put(new Pair<Integer, Integer>(idSrc, idDest), path);
                break;
            }
            for(Pair<BaseVertex, Float> adj : graph.getAdjVerticesWithWeights(curr.getId())) {
                if(cameFrom.get(adj.getKey()) == null){
                    open.add(adj);
                    cameFrom.put(adj.getKey(), curr);
                }
            }
        }

        return path;
    }
}
