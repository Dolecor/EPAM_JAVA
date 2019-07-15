package algorithm;

import graph.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;


public class JPS implements Algorithm {

    private Graph graph;
    private HashMap<Pair<String, String>, ArrayList<Vertex>> cache;


    public JPS(Graph graph) {
        setGraph(graph);
    }

    @Override
    public void setGraph(Graph graph) {
        this.cache = new HashMap<>();
        this.graph = graph;
    }

    @Override
    public Boolean isCalculated(String idSrc, String idDest) {
        return cache.containsKey(new Pair<>(idSrc, idDest));
    }

    @Override
    public ArrayList<Vertex> calculate(String idSrc, String idDest)
    {
        if(isCalculated(idSrc, idDest)) return cache.get(new Pair<>(idSrc, idDest));


        return null;//It will be changed, when I get how JPS works
    }
}