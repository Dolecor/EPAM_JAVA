package util;

import graph.Graph;
import graph.WeightedDigraph;

import java.io.*;

public class GraphLoader {

    /**
     *  Loads WeigthedDigraph(WDG) from file
     */
    public static void loadWDG(WeightedDigraph g, String filepath) throws IOException {
        String buf;
        int nbVertices;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)))) {
            buf = br.readLine();
            nbVertices = Integer.parseInt(buf);

            for (int i = 0; i < nbVertices; ++i) {
                buf = br.readLine();

                /*  args[0] - id
                    args[1] - name
                    args[2] - x coordinate
                    args[3] - y coordinate
                    args[4] - line number   */
                String[] args = buf.split("\t");
                g.addVertex(Integer.parseInt(args[0]));
            }

            for (int i = 0; i < nbVertices; ++i) {
                buf = br.readLine();
                String[] args = buf.split("\t");
                Integer idSrc = Integer.parseInt(args[0]);
                for(int j = 1; j < args.length; ++j){
                    String[] pair = args[j].split(" ");
                    g.setWeight(idSrc, Integer.parseInt(pair[0]), Float.parseFloat(pair[1]));
                }
            }
        }
    }

    public static void main(String[] args) {
        WeightedDigraph g = new WeightedDigraph();
        try {
            GraphLoader.loadWDG(g, "D:\\git projects\\EPAM_JAVA\\resources\\graphs\\testgraph.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(g.getVertices());
        System.out.println(g.getEdges());
    }
}
