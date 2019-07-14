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

    public void removeVertex(Vertex rmV)
    {
        for (List<Pair<Vertex, Float>> v : vertices.values()) {
            for(Pair<Vertex, Float> p : v){
                if (p.getKey() == rmV) {
                    v.remove(p);
                }
            }
        }
        vertices.remove(rmV);
    }

    public void removeVertex(String id)
    {
        Vertex rmV = new Vertex(id);
        removeVertex(rmV);
    }
    public void setWeight(Vertex vSrc, Vertex vDest, Float weight)
    {
        List<Pair<Vertex, Float>> pL = vertices.get(vSrc);
        boolean hasVTo = false;
        for(Pair<Vertex, Float> p : pL){
            if (p.getKey().equals(vDest)) {
                hasVTo = true;
                pL.remove(p);
                pL.add(new Pair<>(vDest, weight));
            }
        }
        if(!hasVTo){
            pL.add(new Pair<>(vDest, weight));
        }
    }

    public void setWeight(String idSrc, String idDest, Float weight)
    {
        Vertex vSrc = new Vertex(idSrc);
        Vertex vDest = new Vertex(idDest);
        setWeight(vSrc, vDest, weight);
    }

    public float getWeight(Vertex vSrc, Vertex vDest)
    {
        float res = Float.NaN;
        for(Pair<Vertex, Float> p : vertices.get(vSrc)){
            if (p.getKey().equals(vDest)) {
                res = p.getValue();
            }
        }
        return res;
    }

    public float getWeight(String idSrc, String idDest)
    {
        Vertex vSrc = new Vertex(idSrc);
        Vertex vDest = new Vertex(idDest);
        return getWeight(vSrc, vDest);
    }

    public List<Pair<Vertex, Float>> getAdjVertices(String idSrc)
    {
        return vertices.get(new Vertex(idSrc));
    }

}
