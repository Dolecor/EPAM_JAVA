package algorithm.BestFirstSearch;

import graph.Graph;
import graph.Vertex;
import javafx.util.Pair;
import util.WeightComparator;

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

        List<Vertex> close = new ArrayList<>();
        PriorityQueue<Pair<Vertex, Float>> open = new PriorityQueue<>(new WeightComparator());
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
                    open.add(p);
                    p.getKey().setPredecessor(curr);
                }
            }
        }

        return null;
    }
}
