package AppInterface.Map.Painters;

import AppInterface.Map.LineColor;
import AppInterface.Map.RoundEdge;
import AppInterface.Map.Station;

import java.awt.*;
import java.util.ArrayList;

public class RoundEdgePainter {

    public static void drawAllRoundEdges(Graphics2D g, ArrayList<RoundEdge> arrayE, ArrayList<Station> arrayS){

        Station tempBegin;
        Station tempEnd;

        int x;
        int y;

        int r;

        int startAngle;
        int arcAngle;

        for (RoundEdge edge: arrayE){

            tempBegin = arrayS.get(edge.getBeginStationID());
            tempEnd = arrayS.get(edge.getEndStationID());

            g.setColor(LineColor.getColor(tempBegin.getLineID(), tempBegin.isTrigger() && tempEnd.isTrigger()));

            r = edge.getR();
            r *= 2;
            x = edge.getCenterX() - r/2;
            y = edge.getCenterY() - r/2;
            startAngle = edge.getStartAngle();
            arcAngle = edge.getEndAngle() - startAngle;

            g.fillArc(x,y,r,r,startAngle,arcAngle);

            r -= 8;

            x = edge.getCenterX() - r/2;
            y = edge.getCenterY() - r/2;

            g.setColor(Color.WHITE);
            g.fillArc(x,y,r,r,startAngle,arcAngle);

        }

    }
}
