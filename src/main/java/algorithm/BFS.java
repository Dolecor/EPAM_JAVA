//started 23:30 7.18.19 AS

package algorithm;

import graph.*;
import javafx.util.Pair;

import java.util.*;
/** class - realisation of algorithm BestFistSearch
 * has properties <b>graph</b> and <b>cache</b>.
 */
public class BFS implements Algorithm {

    private final WeightedDigraph graph;
    private Map<Pair<Integer, Integer>, ArrayList<BaseVertex>> cache;

    /** constructor, which allows to create new object with
     * @param graph custom graph
     */
    public BFS(WeightedDigraph graph) {
        this.graph = new WeightedDigraph(graph);
        this.cache = new HashMap<>();
    }

    /** funtion for verification
     * @param idSrc name of source vertex
     * @param idDest name of destination vertex
     * @return {@code true} if there is already counted path between vertexes
     */
    private Boolean isCalculated(Integer idSrc, Integer idDest) {
        return cache.containsKey(new Pair<>(idSrc, idDest));
    }

    /** main algo function, which finds best path
     * @param idSrc name of source vertex
     * @param idDest name of destination vertex
     * @return {@code res} the list of vertices representing the path from {@code idSrc} to {@code idDest}
     * @return empty list if there is no path from source vertex to destination vertex
     * */
    @Override
    public ArrayList<BaseVertex> calculate(Integer idSrc, Integer idDest) {
        if(isCalculated(idSrc, idDest)) {
            return cache.get(new Pair<>(idSrc, idDest));
        }

        if(graph.getVertexById(idSrc) == null) return null;
        if(graph.getVertexById(idDest) == null) return null;

        ArrayList<BaseVertex> res = new ArrayList<>();
        HashMap<BaseVertex, BaseVertex> visited = new HashMap<>();
        LinkedList<BaseVertex> stack = new LinkedList<>();

        BaseVertex tVertex = graph.getVertexById(idSrc);
        visited.put(tVertex, null);
        stack.push(tVertex);

        if(idSrc.equals(idDest)){
            res.add(tVertex);
            cache.put(new Pair<>(idSrc, idDest), res);
            return res;
        }

        while(!stack.isEmpty()) {

            tVertex = stack.element();

            List<Pair<BaseVertex, Float>> vList = graph.getAdjVerticesWithWeights(tVertex.getId());

            Boolean newChild = false;

            for (Pair<BaseVertex, Float> pair : graph.getAdjVerticesWithWeights(tVertex.getId())) {
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
    public Float getPathWeight(ArrayList<BaseVertex> path)
    {
        float res = 0;
        if(path.size() < 1){
            res = -1.0f;
        }
        else if(path.size() > 1) {
            for(int i = 0; i != path.size() - 1; i++){
                res+=graph.getWeight(path.get(i).getId(), path.get(i+1).getId());
            }
        }
        return res;
    }
}
