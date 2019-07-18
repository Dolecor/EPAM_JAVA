package graph;

import javafx.util.Pair;

import java.util.*;

public class Graph {
    private Map<Vertex, List<Pair<Vertex, Float>>> edges;
    private Set<Vertex> vertices;

    public Graph()
    {
        this.edges = new HashMap<>();
        this.vertices = new HashSet<>();
    }

    public Graph(Graph g)
    {
        this.edges = new HashMap<>();
        this.vertices = new HashSet<>();

        for(Vertex v : g.getVertices()){
            Vertex vCopy = new Vertex(v);
            this.vertices.add(vCopy);
            this.edges.put(vCopy, new ArrayList<>());
        }
        for(Vertex v : vertices){
            for(Pair<Vertex, Float> e : g.getAdjVertices(v.getId())){
                this.setWeight(v, getVertexById(e.getKey().getId()), e.getValue());
            }
        }
    }

    public void addVertex(String id)
    {
        Vertex vNew = new Vertex(id);
        vertices.add(vNew);
        edges.putIfAbsent(vNew, new ArrayList<>());
    }

    public void removeVertex(Vertex rmV)
    {
        for (List<Pair<Vertex, Float>> e : edges.values()) {
            for(Pair<Vertex, Float> p : e){
                if (p.getKey() == rmV) {
                    e.remove(p);
                }
            }
        }
        edges.remove(rmV);
        vertices.remove(rmV);
    }

    public void removeVertex(String id)
    {
        Vertex rmV = getVertexById(id);
        removeVertex(rmV);
    }

    public Vertex getVertexById(String id)
    {
        for(Vertex v : vertices){
            if(v.getId().equals(id)){
                return v;
            }
        }
        return null;
    }

    public void setWeight(Vertex vSrc, Vertex vDest, Float weight)
    {
        List<Pair<Vertex, Float>> pL = edges.get(vSrc);
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
        Vertex vSrc = getVertexById(idSrc);
        Vertex vDest = getVertexById(idDest);
        setWeight(vSrc, vDest, weight);
    }

    public float getWeight(Vertex vSrc, Vertex vDest)
    {
        float res = Float.NaN;
        for(Pair<Vertex, Float> p : edges.get(vSrc)){
            if (p.getKey().equals(vDest)) {
                res = p.getValue();
            }
        }
        return res;
    }

    public float getWeight(String idSrc, String idDest)
    {
        Vertex vSrc = getVertexById(idSrc);
        Vertex vDest = getVertexById(idDest);
        return getWeight(vSrc, vDest);
    }

    public List<Pair<Vertex, Float>> getAdjVertices(Vertex vSrc)
    {
        return edges.get(vSrc);
    }

    public List<Pair<Vertex, Float>> getAdjVertices(String idSrc)
    {
        return getAdjVertices(getVertexById(idSrc));
    }

    public Map<Vertex, List<Pair<Vertex, Float>>> getEdges()
    {
        return edges;
    }

    public Set<Vertex> getVertices()
    {
        return vertices;
    }
}
