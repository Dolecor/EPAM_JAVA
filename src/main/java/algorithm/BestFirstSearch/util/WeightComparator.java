package algorithm.BestFirstSearch.util;

import graph.BaseVertex;
import graph.Vertex;
import javafx.util.Pair;

import java.util.Comparator;

public class WeightComparator implements Comparator<Pair<BaseVertex, Float>> {

    @Override
    public int compare(Pair<BaseVertex, Float> o1, Pair<BaseVertex, Float> o2) {
        return o1.getValue().compareTo(o2.getValue());
    }
}
