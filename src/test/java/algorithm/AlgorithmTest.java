package algorithm;

import graph.Graph;
import graph.Vertex;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgorithmTest {

    protected static int numGraphs = 7;
    protected static Graph[] graphs = new Graph[numGraphs];
    protected static ArrayList<Map<String, Pair<Integer, Integer>>> coords = new ArrayList<>();
    protected List<String> expectedSequence;
    protected Float expectedWeight;

    @BeforeAll
    public static void init(){
        for(int i = 0; i < numGraphs; ++i){
            graphs[i] = new Graph();
        }

        initGraph1();
        initGraph2();
        initGraph3();
        initGraph4();
        initGraph5();
        initGraph6();
        initGraph7();
    }

    private static void initGraph1(){
        graphs[0].addVertex("A");

        coords.add(new HashMap<>());
        coords.get(0).put("A", new Pair<>(1, 1));
    }

    private static void initGraph2(){
        Graph g = graphs[1];

        g.addVertex("A");
        g.addVertex("B");

        g.setWeight("A", "B", 1.0f);

        coords.add(new HashMap<>());
        Map<String, Pair<Integer, Integer>> coord = coords.get(1);
        coord.put("A", new Pair<>(1, 1));
        coord.put("B", new Pair<>(2, 1));
    }

    private static void initGraph3(){
        Graph g = graphs[2];

        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");

        g.setWeight("A", "B", 1.0f);
        g.setWeight("B", "C", 1.0f);

        coords.add(new HashMap<>());
        Map<String, Pair<Integer, Integer>> coord = coords.get(2);
        coord.put("A", new Pair<>(1, 1));
        coord.put("B", new Pair<>(2, 1));
        coord.put("C", new Pair<>(3, 1));
    }

    private static void initGraph4(){
        Graph g = graphs[3];

        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");

        g.setWeight("A", "B", 1.0f);
        g.setWeight("B", "C", 3.0f);
        g.setWeight("B", "D", 1.0f);
        g.setWeight("D", "C", 1.0f);

        coords.add(new HashMap<>());
        Map<String, Pair<Integer, Integer>> coord = coords.get(3);
        coord.put("A", new Pair<>(1, 1));
        coord.put("B", new Pair<>(2, 1));
        coord.put("C", new Pair<>(5, 1));
        coord.put("D", new Pair<>(3, 0));
    }

    private static void initGraph5(){
        Graph g = graphs[4];

        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addVertex("E");

        g.setWeight("A", "B", 1.0f);
        g.setWeight("B", "C", 5.0f);
        g.setWeight("B", "E", 7.0f);
        g.setWeight("C", "D", 6.0f);
        g.setWeight("E", "D", 1.0f);

        coords.add(new HashMap<>());
        Map<String, Pair<Integer, Integer>> coord = coords.get(4);
        coord.put("A", new Pair<>(1, 1));
        coord.put("B", new Pair<>(2, 1));
        coord.put("C", new Pair<>(3, 1));
        coord.put("D", new Pair<>(4, 1));
        coord.put("E", new Pair<>(3, 0));
    }

    private static void initGraph6(){
        Graph g = graphs[5];

        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addVertex("E");
        g.addVertex("F");

        g.setWeight("A", "B", 1.0f);
        g.setWeight("A", "F", 2.0f);
        g.setWeight("B", "C", 1.0f);
        g.setWeight("C", "D", 1.0f);
        g.setWeight("D", "E", 1.0f);
        g.setWeight("F", "E", 1.0f);

        coords.add(new HashMap<>());
        Map<String, Pair<Integer, Integer>> coord = coords.get(5);
        coord.put("A", new Pair<>(1, 1));
        coord.put("B", new Pair<>(2, 1));
        coord.put("C", new Pair<>(3, 1));
        coord.put("D", new Pair<>(4, 1));
        coord.put("E", new Pair<>(5, 1));
        coord.put("F", new Pair<>(3, 0));
    }

    private static void initGraph7(){
        Graph g = graphs[6];

        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addVertex("E");
        g.addVertex("F");
        g.addVertex("G");
        g.addVertex("H");
        g.addVertex("I");

        g.setWeight("A", "B", 10.0f);
        g.setWeight("B", "C", 5.0f);
        g.setWeight("C", "D", 6.0f);
        g.setWeight("C", "G", 3.0f);
        g.setWeight("C", "H", 7.0f);
        g.setWeight("D", "E", 8.0f);
        g.setWeight("G", "F", 1.0f);
        g.setWeight("H", "I", 1.0f);

        coords.add(new HashMap<>());
        Map<String, Pair<Integer, Integer>> coord = coords.get(6);
        coord.put("A", new Pair<>(1, 2));
        coord.put("B", new Pair<>(2, 2));
        coord.put("C", new Pair<>(3, 2));
        coord.put("D", new Pair<>(4, 2));
        coord.put("E", new Pair<>(5, 2));
        coord.put("F", new Pair<>(3, 4));
        coord.put("G", new Pair<>(3, 3));
        coord.put("H", new Pair<>(3, 1));
        coord.put("I", new Pair<>(3, 0));
}

    protected static boolean isListEquals(List<Vertex> list1, List<String> list2){
        for(int i = 0; i < list1.size(); ++i){
            if(!list1.get(i).getId().equals(list2.get(i))){
                return false;
            }
        }
        return true;
    }
}
