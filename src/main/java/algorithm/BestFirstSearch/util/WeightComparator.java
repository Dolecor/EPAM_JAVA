package algorithm.BestFirstSearch.util;

import graph.Vertex;
import javafx.util.Pair;

import java.util.Comparator;

public class WeightComparator implements Comparator<Pair<Vertex, Float>> {

    @Override
    public int compare(Pair<Vertex, Float> o1, Pair<Vertex, Float> o2) {
        return o1.getValue().compareTo(o2.getValue());
    }
}
