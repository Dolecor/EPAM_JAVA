//started 23:30 7.18.19 AS

package algorithm;

import graph.*;
import javafx.util.Pair;

import java.util.*;
/** class - realisation of algorithm BestFistSearch
 * has properties <b>graph</b> and <b>cache</b>.
 */
public class BFS implements Algorithm {

    private final Graph graph;
    private Map<Pair<String, String>, ArrayList<Vertex>> cache;

    /** constructor, which allows to create new object with
     * @param graph custom graph
     */
    public BFS(Graph graph) {
        this.graph = new Graph(graph);
        this.cache = new HashMap<>();
    }

    /** funtion for verification
     * @param idSrc name of source vertex
     * @param idDest name of destination vertex
     * @return {@code true} if there is already counted path between vertexes
     */
    private Boolean isCalculated(String idSrc, String idDest) {
        return cache.containsKey(new Pair<>(idSrc, idDest));
    }

    /** main algo function, which finds best path
     * @param idSrc name of source vertex
     * @param idDest name of destination vertex
     * @return {@code res} the list of vertices representing the path from {@code idSrc} to {@code idDest}
     * @return empty list if there is no path from source vertex to destination vertex
     * */
    @Override
    public ArrayList<Vertex> calculate(String idSrc, String idDest) {
        if(isCalculated(idSrc, idDest)) {
            return cache.get(new Pair<>(idSrc, idDest));
        }

        if(graph.getVertexById(idSrc) == null) return null;
        if(graph.getVertexById(idDest) == null) return null;

        ArrayList<Vertex> res = new ArrayList<>();
        HashMap<Vertex, Vertex> visited = new HashMap<>();
        LinkedList<Vertex> stack = new LinkedList<>();

        Vertex tVertex = graph.getVertexById(idSrc);
        visited.put(tVertex, null);
        stack.push(tVertex);

        if(idSrc.equals(idDest)){
            res.add(tVertex);
            cache.put(new Pair<>(idSrc, idDest), res);
            return res;
        }

        while(!stack.isEmpty()) {

            tVertex = stack.element();

            List<Pair<Vertex, Float>> vList = graph.getAdjVertices(tVertex.getId());

            Boolean newChild = false;

            for (Pair<Vertex, Float> pair : graph.getAdjVertices(tVertex)) {
                if (visited.putIfAbsent(pair.getKey(), tVertex) == null) {
                    if(pair.getKey().getId().equals(idDest)) {
                        tVertex = pair.getKey();

                        while(!tVertex.getId().equals(idSrc)){
                            res.add(tVertex);
                            tVertex = visited.get(tVertex);
                        }

                        res.add(tVertex);
                        Collections.reverse(res);

                        break;
                    }

                    else {
                        newChild = true;
                        stack.push(pair.getKey());
                    }
                }
            }

            if(!newChild) {
                stack.pop();
            }
        }
        return res;
    }

    /** additional agorithm function
     * @param path sequence of vertices that represent the path from one vertex to another
     * @return {@code -1.0f} if {@code path} is empty list, or
     *          {@code 0.0f} if path contains one vertex, or
     *          positive float number otherwise.
     */
    @Override
    public Float getPathWeight(ArrayList<Vertex> path)
    {
        float res = 0;
        if(path.size() < 1){
            res = -1.0f;
        }
        else if(path.size() > 1) {
            for(int i = 0; i != path.size() - 1; i++){
                res+=graph.getWeight(path.get(i), path.get(i+1));
            }
        }
        return res;
    }
}

//Simple test of BFS (Put in App.Java)
        /*
        Graph graph = new Graph();
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");
        graph.addVertex("6");
        graph.addVertex("7");
        graph.addVertex("8");
        graph.addVertex("9");
        graph.addVertex("10");

        graph.setWeight("1", "2", 9.0f);
        graph.setWeight("1", "6", 10.0f);
        graph.setWeight("2", "3", 2.0f);
        graph.setWeight("2", "4", 4.0f);
        graph.setWeight("2", "10", 4.0f);
        graph.setWeight("6", "8", 2.0f);
        graph.setWeight("8", "7", 3.0f);
        graph.setWeight("7", "9", 2.0f);
        graph.setWeight("3", "5", 1.0f);
        graph.setWeight("5", "10", 1.0f);
        graph.setWeight("4", "10", 1.0f);

        //graph.setWeight("9", "10", 2.0f);

        BFS bfs = new BFS(graph);

        ArrayList<Vertex> arr = bfs.calculate("1", "10");

        for(Vertex var : arr) {
            logger.debug(var.getId() + " ");
        }
         */
