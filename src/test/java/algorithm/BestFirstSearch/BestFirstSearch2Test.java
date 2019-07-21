package algorithm.BestFirstSearch;

import algorithm.AlgorithmTest;
import graph.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearch2Test extends AlgorithmTest {

    @BeforeEach
    void setUp() {
        expectedSequence = new ArrayList<>();
    }

    @Test
    @DisplayName("Find path in graph1")
    void calculate1() {
        expectedSequence.add("A");
        boolean exp = true;

        BestFirstSearch2 algo = new BestFirstSearch2(graphs[0],coords.get(0));
        ArrayList<Vertex> res = algo.calculate("A", "A");

        assertTrue(isListEquals(res, expectedSequence));
    }

    @Test
    @DisplayName("Find path in graph2")
    void calculate2() {
        expectedSequence.addAll(Arrays.asList("A", "B"));
        boolean exp = true;

        BestFirstSearch2 algo = new BestFirstSearch2(graphs[1],coords.get(1));
        ArrayList<Vertex> res = algo.calculate("A", "B");

        assertTrue(isListEquals(res, expectedSequence));
    }

    @Test
    @DisplayName("Find path in graph3")
    void calculate3() {
        expectedSequence.addAll(Arrays.asList("A", "B", "C"));
        boolean exp = true;

        BestFirstSearch2 algo = new BestFirstSearch2(graphs[2],coords.get(2));
        ArrayList<Vertex> res = algo.calculate("A", "C");

        assertTrue(isListEquals(res, expectedSequence));
    }

    @Test
    @DisplayName("Find path in graph4")
    void calculate4() {
        expectedSequence.addAll(Arrays.asList("A", "B", "C"));
        boolean exp = true;

        BestFirstSearch2 algo = new BestFirstSearch2(graphs[3],coords.get(3));
        ArrayList<Vertex> res = algo.calculate("A", "C");

        assertTrue(isListEquals(res, expectedSequence));
    }

    @Test
    @DisplayName("Find path in graph5")
    void calculate5() {
        expectedSequence.addAll(Arrays.asList("A", "B", "C", "D"));
        boolean exp = true;

        BestFirstSearch2 algo = new BestFirstSearch2(graphs[4],coords.get(4));
        ArrayList<Vertex> res = algo.calculate("A", "D");

        assertTrue(isListEquals(res, expectedSequence));
    }

    @Test
    @DisplayName("Find path in graph6")
    void calculate6() {
        expectedSequence.addAll(Arrays.asList("A", "F", "E"));
        boolean exp = true;

        BestFirstSearch2 algo = new BestFirstSearch2(graphs[5],coords.get(5));
        ArrayList<Vertex> res = algo.calculate("A", "E");

        assertTrue(isListEquals(res, expectedSequence));
    }

    @Test
    @DisplayName("Find path in graph7")
    void calculate7() {
        expectedSequence.addAll(Arrays.asList("A", "B", "C", "H", "I"));
        boolean exp = true;

        BestFirstSearch2 algo = new BestFirstSearch2(graphs[6],coords.get(6));
        ArrayList<Vertex> res = algo.calculate("A", "I");

        assertTrue(isListEquals(res, expectedSequence));
    }
}