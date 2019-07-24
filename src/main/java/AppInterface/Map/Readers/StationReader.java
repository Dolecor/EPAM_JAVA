package AppInterface.Map.Readers;

import AppInterface.Map.Station;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StationReader {

    public static void load(ArrayList<Station> stations, Map<Integer, ArrayList<String>> stationsByWays) throws IOException{
        String fileName = "resources/stations.txt";

        String fileStr;
        String[] args;

        String name;
        int line;
        int x;
        int y;

        FileInputStream fileIn = null;

        try{
            fileIn = new FileInputStream(fileName);
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(fileIn));
            while((fileStr = buffReader.readLine()) != null){
                args = fileStr.split("\t");
                name = args[0];
                line = Integer.parseInt(args[1]);
                x = Integer.parseInt(args[2]);
                y = Integer.parseInt(args[3]);
                stations.add(new Station(stations.size(), name, line, x, y));

                if (stationsByWays.get(line) == null)
                    stationsByWays.putIfAbsent(line, new ArrayList<String>());
                stationsByWays.get(line).add(name);
            }

        }
        finally {
            if (fileIn != null) {
                fileIn.close();
            }
        }

    }

}
