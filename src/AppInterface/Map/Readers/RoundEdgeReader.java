package AppInterface.Map.Readers;

import AppInterface.Map.RoundEdge;

import java.io.*;
import java.util.ArrayList;

public class RoundEdgeReader {

    public static void load(ArrayList<RoundEdge> roundEdges)   throws IOException{
        String fileName = "resources/roundEdges.txt";

        String fileStr = new String();
        String[] args;

        int beginID;
        int endID;

        int r;
        int centerX;
        int centerY;

        int startArc;
        int endArc;

        FileInputStream fileIn = null;

        try {
            fileIn = new FileInputStream(fileName);
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(fileIn));
            while((fileStr = buffReader.readLine()) != null){
                args = fileStr.split(" ");
                r = Integer.parseInt(args[0]);
                centerX = Integer.parseInt(args[1]);
                centerY = Integer.parseInt(args[2]);
                for(int i = 3; i < args.length - 2; i += 2){
                    beginID = Integer.parseInt(args[i]);
                    startArc = Integer.parseInt(args[i + 1]);
                    endID = Integer.parseInt(args[i + 2]);
                    endArc = Integer.parseInt(args[i + 3]);
                    roundEdges.add(new RoundEdge(beginID, endID, centerX, centerY, r, startArc, endArc));
                }
            }
        }
        finally {
            if (fileIn != null) {
                fileIn.close();
                }
            }
    }

}
