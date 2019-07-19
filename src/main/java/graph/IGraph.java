package graph;

import java.util.ArrayList;

public interface IGraph<V extends BaseVertex> {
    /**
     * Creates vertex with identificator {@code id} and adds it to graph
     * if it not already present.
     *
     * @param id identificator of vertex
     * @return {@code true} if graph did not already contain the vertex
     *          with this id
     * @throws NullPointerException if the specified element is null
     */
    boolean addVertex(Integer id);

    /**
     * Removes vertex with id {@code id} from graph if it is present.
     *
     * @param id identificator of vertex
     * @return {@code true} if graph contained the vertex with this id
     * @throws NullPointerException if the specified id is null
     */
    boolean removeVertex(Integer id);

    /**
     * @param id identificator of vertex
     * @return vertex with specified identificator, or
     *          {@code null} if there is no such vertex
     */
    V getVertexById(Integer id);

    /**
     * Creates edge with vertices with identificators {@code id1} and {@code id2}
     * and adds it to graph if it not already present.
     *
     * @param id1 identificator of first vertex
     * @param id2 identificator of second vertex
     * @return {@code true} if graph did not already contain the edge
     *          with vertices with specified identificators, or
     *          {@code false} if this edge already present in graph or
     *          at least one of the vertices does not present in graph
     * @throws NullPointerException if at least one of specified vertices is null
     */
    boolean addEdge(Integer id1, Integer id2);

    /**
     * Removes edge with vertices with vertices {@code v1} and {@code v2}
     * from graph if it is present.
     *
     * @param id1 identificator of first vertex
     * @param id2 identificator of second vertex
     * @return {@code true} if graph contained the edge with this vertices
     * @throws NullPointerException if at least one of specified vertices is null
     */
    boolean removeEdge(Integer id1, Integer id2);

    /**
     * @param id vertex from which to find adjacent vertices
     * @return list of vertices adjacent to vertex {@code id}, or
     *          empty list if vertex with specified identificator
     *          have no adjacent vertices
     */
    ArrayList<V> getAdjVertices(Integer id);
}
