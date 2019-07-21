package algorithm;

import graph.*;
import java.util.ArrayList;

public interface Algorithm {

    /**
     * @param idSrc identificator of source vertex
     * @param idDest identificator of destination vertex
     * @return the list of vertices representing the path from {@code idSrc} to {@code idDest}, or
     *          empty list if there is no path from source vertex to destination vertex
     */
    ArrayList<Vertex> calculate(String idSrc, String idDest);

    /**
     *
     * @param path sequence of vertices that represent the path from one vertex to another
     * @return {@code -1.0f} if {@code path} is empty list, or
     *          {@code 0.0f} if path contains one vertex, or
     *          positive float number otherwise.
     */
    Float getPathWeight(ArrayList<Vertex> path);
}
