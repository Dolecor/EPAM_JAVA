package algorithm;

import graph.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;



public class JPS extends Algorithm {

    private Graph graph_;
    private HashMap<Pair<String, String>, ArrayList<Vertex>> cache_;


    public JPS(Graph graph) {
        setGraph(graph);
    }

    @Override
    public void setGraph(Graph graph) {
        cache_ = new HashMap<>();
        graph_ = graph;
    }

    @Override
    public Boolean isCalculated(String idSrc, String idDest) {
        return cache_.containsKey(new Pair<>(idSrc, idDest));
    }

    @Override
    public ArrayList<Vertex> calculate(String idSrc, String idDest)
    {
        if(isCalculated(idSrc, idDest)) return cache_.get(new Pair<>(idSrc, idDest));

        return null;//It will be changed, when I get how JPS works
    }
}
