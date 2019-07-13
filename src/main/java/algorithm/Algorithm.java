package algorithm;

import graph.*;
import java.util.ArrayList;

public interface Algorithm {
    void setGraph(Graph graph);
    //Calculate if isCalculated() is false and return the answer w/o calculation when isCalculated() is true
    ArrayList<Vertex> calculate();
    Boolean isCalculated();
}
