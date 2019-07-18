package graph;

import lombok.Data;

@Data
public class Vertex {
    private final String id;
    private Vertex predecessor;

    public Vertex(String id){
        this.id = id;
        this.predecessor = null;
    }

    public Vertex(Vertex v)
    {
        this.id = v.getId();
        this.predecessor = v.getPredecessor();
    }
}
