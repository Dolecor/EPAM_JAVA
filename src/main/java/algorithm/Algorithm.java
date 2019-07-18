package algorithm;

import graph.*;
import java.util.ArrayList;

public interface Algorithm {

    /**
     * @param idSrc identificator of source vertex
     * @param idDest identificator of destination vertex
     * @return the list of vertices representing the path from {@code idSrc} to {@code idDest}, or
     *          {@code null} if there is no path from source vertex to destination vertex
     */
    ArrayList<Vertex> calculate(String idSrc, String idDest);
}
