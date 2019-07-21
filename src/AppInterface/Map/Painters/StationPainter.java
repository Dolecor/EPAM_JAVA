package AppInterface.Map.Painters;

import AppInterface.Map.LineColor;
import AppInterface.Map.Station;

import java.awt.*;
import java.util.ArrayList;

public class StationPainter {

    public static void drawPoint(Graphics2D g , ArrayList<Station> arraySt){

        Station temp = arraySt.get(0);

        int countOfStations = arraySt.size();

        int r = 10 * (countOfStations < 3 ? countOfStations : 3);
        int abrysR = r + 4;
        int scaleR = abrysR + 1;

        int rad = 360 / countOfStations;

        int x = temp.getCoordX() - r/2;
        int y = temp.getCoordY() - r/2;

        g.setColor(Color.BLACK);
        g.fillArc(x-2, y-2, abrysR, abrysR,0, 360);

        g.setColor(new Color(0,0,0,64));
        g.fillArc(x-1, y-1, scaleR, scaleR,0, 360);

        g.setColor(LineColor.getColor(temp.getLineID(), temp.isTrigger()));
        g.fillArc(x, y, r, r,0, rad);

        for(int i = 1; i < countOfStations; i++){
            temp = arraySt.get(i);
            g.setColor(LineColor.getColor(temp.getLineID(), temp.isTrigger()));
            g.fillArc(x, y, r, r,i*rad, rad);
        }

       }

}
