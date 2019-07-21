package algorithm.BestFirstSearch;

import graph.Graph;
import graph.Vertex;
import javafx.util.Pair;
import algorithm.BestFirstSearch.util.WeightComparator;

import java.util.*;

/**
 * The class represents the algorithm for finding the path from
 * the source vertex to the destination vertex.
 * Principle: select the next vertex on the principle
 * of least weight.
 */
public class BestFirstSearch extends AbstractBestFirstSearch/*implements Algorithm*/ {

    public BestFirstSearch(Graph graph)
    {
        super(graph);
    }

    @Override
    public ArrayList<Vertex> calculate(String idSrc, String idDest)
    {
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

        Map<Vertex, Vertex> cameFrom = new HashMap<>();
        PriorityQueue<Pair<Vertex, Float>> open = new PriorityQueue<>(new WeightComparator());
        open.add(new Pair<>(curr, -1.0f));
        cameFrom.put(curr, null);

        while(!open.isEmpty()) {
            curr = open.poll().getKey();
            if(curr.equals(dest)){
                path = backTrack(src, dest, cameFrom);
                cache.put(new Pair<>(idSrc, idDest), path);
                break;
            }
            for(Pair<Vertex, Float> adj : graph.getAdjVertices(curr)) {
                if(cameFrom.get(adj.getKey()) == null){
                    open.add(adj);
                    cameFrom.put(adj.getKey(), curr);
                }
            }
        }

        return path;
    }
}
