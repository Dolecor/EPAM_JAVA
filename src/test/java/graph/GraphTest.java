package graph;

import javafx.util.Pair;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    private Graph testGraph;

    @BeforeEach
    void setUp() {
        testGraph = new Graph();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Add vertex")
    void addVertex() {
        String id = "A";
        testGraph.addVertex(id);

        assertNotNull(testGraph.getVertexById(id));
    }

    @Test
    @DisplayName("Remove vertex by id")
    void removeVertex1(){
        String id = "A";
        testGraph.addVertex(id);

        testGraph.removeVertex(id);

        assertNull(testGraph.getVertexById(id));
    }

    @Test
    @DisplayName("Remove specified vertex")
    void removeVertex2(){
        String id = "A";
        testGraph.addVertex(id);

        Vertex rm = testGraph.getVertexById(id);

        testGraph.removeVertex(rm);

        assertNull(testGraph.getVertexById(rm.getId()));
    }

    @Test
    @DisplayName("Set weight of edge by vertices id")
    void setGetWeight1(){
        String idSrc = "A";
        String idDest = "B";
        testGraph.addVertex(idSrc);
        testGraph.addVertex(idDest);
        testGraph.setWeight(idSrc, idDest, 1.0f);

        assertFalse(Float.isNaN(testGraph.getWeight(idSrc, idDest)));
    }

    @Test
    @DisplayName("Set weight of edge of specified vertices")
    void setGetWeight2(){
        String idSrc = "A";
        String idDest = "B";
        testGraph.addVertex(idSrc);
        testGraph.addVertex(idDest);

        Vertex vSrc = testGraph.getVertexById(idSrc);
        Vertex vDest = testGraph.getVertexById(idDest);

        testGraph.setWeight(vSrc, vDest, 1.0f);

        assertFalse(Float.isNaN(testGraph.getWeight(vSrc, vDest)));
    }

    @Test
    @DisplayName("Getting adjacent vertices")
    void getAdjVertices() {
        List<String> ids = Arrays.asList("A", "B", "C", "D");

        for (String s : ids) {
            testGraph.addVertex(s);
        }

        // set next edges: A -> B, A -> C, A -> D
        for(int i = 1; i < ids.size(); ++i){
            testGraph.setWeight(ids.get(0), ids.get(i), 0.0f);
        }

        boolean containAB = false,
                containAC = false,
                containAD = false;
        int countAdj = 0;

        for(Pair<Vertex, Float> pair : testGraph.getAdjVertices(ids.get(0))){
            ++countAdj;
            if(pair.getKey().equals(testGraph.getVertexById(ids.get(1)))){
                containAB = true;
            }
            else if(pair.getKey().equals(testGraph.getVertexById(ids.get(2)))){
                containAC = true;
            }
            else if(pair.getKey().equals(testGraph.getVertexById(ids.get(3)))){
                containAD = true;
            }
        }

        assertTrue(containAB&&containAC&&containAD&&(countAdj == 3));
    }
}