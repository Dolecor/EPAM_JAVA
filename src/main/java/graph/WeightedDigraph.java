package graph;

import javafx.util.Pair;
import lombok.Getter;

import java.util.*;

public class WeightedDigraph implements IGraph<BaseVertex> {

    @Getter
    private Set<BaseVertex> vertices;
    @Getter
    private Map<BaseVertex, ArrayList<Pair<BaseVertex, Float>>> edges;

    public WeightedDigraph(){
        vertices = new HashSet<>();
        edges = new HashMap<>();
    }

    public WeightedDigraph(WeightedDigraph g)
    {
        this();

        for(BaseVertex v : g.getVertices()){
            BaseVertex vCopy = new BaseVertex(v.getId());
            this.vertices.add(vCopy);
            this.edges.put(vCopy, new ArrayList<>());
        }
        for(BaseVertex v : vertices){
            for(Pair<BaseVertex, Float> e : g.getAdjVerticesWithWeights(v.getId())){
                this.setWeight(v.getId(),
                            getVertexById(e.getKey().getId()).getId(),
                            e.getValue());
            }
        }
    }

    @Override
    public boolean addVertex(Integer id) {
        if(id == null){
            throw new NullPointerException();
        }

        if(getVertexById(id) != null) {
            return false;
        }
        BaseVertex vNew = new BaseVertex(id);
        vertices.add(vNew);
        edges.put(vNew, new ArrayList<>());
        return true;
    }

    @Override
    public boolean removeVertex(Integer id) {
        if(id == null){
            throw new NullPointerException();
        }

        BaseVertex rmV = getVertexById(id);
        if(rmV == null) {
            return false;
        }
        for (List<Pair<BaseVertex, Float>> adj : edges.values()) {
            for (Pair<BaseVertex, Float> pair : adj) {
                if (pair.getKey() == rmV) {
                    adj.remove(pair);
                    break;
                }
            }
        }
        edges.remove(rmV);
        vertices.remove(rmV);
        return true;
    }

    @Override
    public BaseVertex getVertexById(Integer id) {
        for(BaseVertex v : vertices){
            if(v.getId().equals(id)){
                return v;
            }
        }
        return null;
    }

    @Override
    public boolean addEdge(Integer id1, Integer id2) {
        if(id1 == null || id2 == null){
            throw new NullPointerException();
        }

        BaseVertex vSrc = getVertexById(id1);
        BaseVertex vDest = getVertexById(id2);
        boolean isEdgeContained = false;

        if(vSrc == null || vDest == null) {
            return false;
        }

        for (Pair<BaseVertex, Float> pair : edges.get(vSrc)) {
            if(pair.getKey().equals(vDest)){
                isEdgeContained = true;
                break;
            }
        }

        //if graph did not already contain edge then setWeight() will set it
        return !isEdgeContained && setWeight(id1, id2, 0.0f);
    }

    @Override
    public boolean removeEdge(Integer id1, Integer id2) {
        if(id1 == null || id2 == null){
            throw new NullPointerException();
        }

        BaseVertex v1 = getVertexById(id1);
        BaseVertex v2 = getVertexById(id2);
        boolean isEdgeContained = false;

        if(v1 == null || v2 == null) {
            return false;
        }
        List<Pair<BaseVertex, Float>> adjV1 = edges.get(v1);
        for(Pair<BaseVertex, Float> pair : adjV1){
            if(pair.getKey() == v2){
                isEdgeContained = true;
                edges.get(v1).remove(pair);
                break;
            }
        }
        return isEdgeContained;
    }

    @Override
    public ArrayList<BaseVertex> getAdjVertices(Integer id) {
        if(id == null){
            throw new NullPointerException();
        }

        ArrayList<BaseVertex> res = new ArrayList<>();
        BaseVertex v = getVertexById(id);
        if(v != null) {
            List<Pair<BaseVertex, Float>> adjV = edges.get(v);
            for (Pair<BaseVertex, Float> pair : adjV) {
                res.add(pair.getKey());
            }
        }
        return res;
    }

    /**
     *
     * @param id vertex from which to find adjacent vertices
     * @return list of pairs "destination vertex - weight", where
     *          destination vertex is adjacent to vertex {@code id}, or
     *          empty list if vertex with specified identificator
     *          have no adjacent vertices
     */
    public ArrayList<Pair<BaseVertex, Float>> getAdjVerticesWithWeights(Integer id){
        if(id == null){
            throw new NullPointerException();
        }

        BaseVertex v = getVertexById(id);

        return (v == null) ? new ArrayList<>() : edges.get(v);
    }

    /**
     * Sets weight to edge, or changes it.
     * @return {@code true} if weight has been set, or
     *          {@code false} if at least one of specified vertices
     *          does not present in graph
     */
    public boolean setWeight(Integer idSrc, Integer idDest, Float weight){
        if(idSrc == null || idDest == null || weight == null){
            throw new NullPointerException();
        }

        BaseVertex vSrc = getVertexById(idSrc);
        BaseVertex vDest = getVertexById(idDest);

        if(vSrc != null && vDest != null) {
            List<Pair<BaseVertex, Float>> adjSrc = edges.get(vSrc);
            boolean hasVDest = false;

            for (Pair<BaseVertex, Float> pair : adjSrc) {
                if (pair.getKey().equals(vDest)) {
                    //edge with vertices vSrc and vDest is present in graph
                    hasVDest = true;
                    if(!weight.equals(pair.getValue())) {
                        adjSrc.remove(pair);
                        adjSrc.add(new Pair<>(vDest, weight));
                    }
                    return true;
                }
            }
            if (!hasVDest) {
                adjSrc.add(new Pair<>(vDest, weight));
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return weight of edge with specified vertices, or
     *          {@code null} if graph have no such edge
     */
    public Float getWeight(Integer idSrc, Integer idDest){
        if(idSrc == null || idDest == null){
            throw new NullPointerException();
        }

        BaseVertex vSrc = getVertexById(idSrc);
        BaseVertex vDest = getVertexById(idDest);
        Float res = null;

        if(vSrc != null && vDest != null) {
            for (Pair<BaseVertex, Float> p : edges.get(vSrc)) {
                if (p.getKey().equals(vDest)) {
                    res = p.getValue();
                    break;
                }
            }
        }
        return res;
    }
}
