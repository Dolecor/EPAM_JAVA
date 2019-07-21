package graph;

import javafx.util.Pair;
import java.util.*;

/** main class to represent graph
 * has properties <b>edges</b> and <b>vertices</b>
 * @autor Dmitry Dolenko
*/

public class Graph {
    private Map<Vertex, List<Pair<Vertex, Float>>> edges;
    private Set<Vertex> vertices;

    /** basic constructor without parameters */
    public Graph()
    {
        this.edges = new HashMap<>();
        this.vertices = new HashSet<>();
    }

    /** constructor, which allows to create new object with
     * @param g custom graph
     */
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

    /** function to add new vertex in graph
     * @param id vertex name
     */
    public void addVertex(String id)
    {
        Vertex vNew = new Vertex(id);
        vertices.add(vNew);
        edges.putIfAbsent(vNew, new ArrayList<>());
    }

    /** functions to delete Vertex from graph
     * @param rmV identeficator of vertex
     */
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

    /** functions to delete Vertex from graph
     * @param id metro station name
     */
    public void removeVertex(String id)
    {
        Vertex rmV = getVertexById(id);
        removeVertex(rmV);
    }

    /** fuctions to find vertex in graph
     * @param id metro station name
     * @return {@param v} if in graph exists vertex with transferred name
     * @return {@code null} if there is no such vertex
     */
    public Vertex getVertexById(String id)
    {
        for(Vertex v : vertices){
            if(v.getId().equals(id)){
                return v;
            }
        }
        return null;
    }

    /** function to set the weight of edge
     * @param vSrc identificator of source vertex
     * @param vDest identificator of destination vertex
     * @param weight keeps the distance between vertexes
     */
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

    /** function to set the weight of edge
     * @param idSrc name of source vertex
     * @param idDest name of destination vertex
     * @param weight keeps the distance between vertexes
     */
    public void setWeight(String idSrc, String idDest, Float weight)
    {
        Vertex vSrc = getVertexById(idSrc);
        Vertex vDest = getVertexById(idDest);
        setWeight(vSrc, vDest, weight);
    }

    /** function to get the weight of edge
     * @param vSrc identificator of source vertex
     * @param vDest identificator of destination vertex
     * @return distance between vertexes
     */
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

    /** function to get the weight of edge
     * @param idSrc name of source vertex
     * @param idDest name of destination vertex
     * @return distance between vertexes {@link public float getWeight(Vertex vSrc, Vertex vDest)}
     */
    public float getWeight(String idSrc, String idDest)
    {
        Vertex vSrc = getVertexById(idSrc);
        Vertex vDest = getVertexById(idDest);
        return getWeight(vSrc, vDest);
    }

    /** structure to keep adjacent vertices
     * @param vSrc identificator of source vertex
     */
    public List<Pair<Vertex, Float>> getAdjVertices(Vertex vSrc)
    {
        return edges.get(vSrc);
    }

    /** structure to keep adjacent nodes
     * @param idSrc name of source vertex
     */
    public List<Pair<Vertex, Float>> getAdjVertices(String idSrc)
    {
        return getAdjVertices(getVertexById(idSrc));
    }

    /** structure to keep edges*/
    public Map<Vertex, List<Pair<Vertex, Float>>> getEdges()
    {
        return edges;
    }

    /** structure to keep vertices*/
    public Set<Vertex> getVertices()
    {
        return vertices;
    }
}
