package graph;

import javafx.util.Pair;

import java.util.*;

public class Graph {
    private Map<Vertex, List<Pair<Vertex, Float>>> vertices;

    public Graph()
    {
        vertices = new HashMap<>();
    }

    public void addVertex(String id)
    {
        vertices.putIfAbsent(new Vertex(id), new ArrayList<>());
    }

    public void removeVertex(String id)
    {
        Vertex rmV = new Vertex(id);
        for (List<Pair<Vertex, Float>> v : vertices.values()) {
            for(Pair<Vertex, Float> p : v){
                if (p.getKey() == rmV) {
                    v.remove(p);
                }
            }
        }
        vertices.remove(rmV);
    }

    public void setWeight(String idSrc, String idDist, Float weight)
    {
        Vertex vSrc = new Vertex(idSrc);
        Vertex vDist = new Vertex(idDist);
        List<Pair<Vertex, Float>> pL = vertices.get(vSrc);
        boolean hasVTo = false;
        for(Pair<Vertex, Float> p : pL){
            if (p.getKey().equals(vDist)) {
                hasVTo = true;
                pL.remove(p);
                pL.add(new Pair<>(new Vertex(idDist), weight));
            }
        }
        if(!hasVTo){
            pL.add(new Pair<>(new Vertex(idDist), weight));
        }
    }

    public float getWeight(String idSrc, String idDist)
    {
        float res = Float.NaN;
        Vertex vSrc = new Vertex(idSrc);
        Vertex vDist = new Vertex(idDist);
        for(Pair<Vertex, Float> p : vertices.get(vSrc)){
            if (p.getKey().equals(vDist)) {
                res = p.getValue();
            }
        }
        return res;
    }
}
