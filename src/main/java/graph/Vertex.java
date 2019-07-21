package graph;
import lombok.Data;

/** class to represent vertex
 * has properties <b>id</b> and <b>predecessor</b>
 * @autor Dmitry Dolenko
 * */
@Data
public class Vertex {
    private final String id;
    private Vertex predecessor;

    /** constructor, which allows to create new object with
     * @param id name of vertex
     */
    public Vertex(String id){
        this.id = id;
        this.predecessor = null;
    }

    /** constructor, which allows to create new object with
     * @param v identificator of vertex
     */
    public Vertex(Vertex v)
    {
        this.id = v.getId();
        this.predecessor = v.getPredecessor();
    }
}
