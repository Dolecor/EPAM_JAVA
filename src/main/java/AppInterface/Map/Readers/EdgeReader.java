package AppInterface.Map.Readers;

import AppInterface.Map.DirectEdge;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class EdgeReader {

    public static void load(ArrayList<DirectEdge> directEdges) throws IOException{
        String fileName = "resources/directEdges.txt";

        String fileStr = new String();
        String[] args;

        int beginID;
        int endID;

        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();

        int mod;

        FileInputStream fileIn = null;

        try{
            fileIn = new FileInputStream(fileName);
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(fileIn));
            while((fileStr = buffReader.readLine()) != null){
                args = fileStr.split(" ");

                for (int i = 0; i < args.length - 1; i++){
                    mod = 0;
                    x.clear();
                    y.clear();
                    beginID = Integer.parseInt(args[i]);
                    if (args[i + 1].equals("[")){
                        mod = 2;
                        do {
                            x.add(Integer.parseInt(args[i + mod]));
                            y.add(925 - Integer.parseInt(args[i + mod + 1]));
                            mod += 2;
                        } while(!args[i + mod].equals("]"));
                    }
                    endID = Integer.parseInt(args[i + mod + 1]);
                    directEdges.add(new DirectEdge(beginID, endID));
                    directEdges.get(directEdges.size() - 1).addCords(x, y);
                    i += mod;
                }
            }
            x.clear();
            y.clear();
        }
        finally {
            if (fileIn != null) {
                fileIn.close();
            }
        }
    }

}
