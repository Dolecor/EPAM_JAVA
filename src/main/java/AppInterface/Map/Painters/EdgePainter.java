package AppInterface.Map.Painters;

import AppInterface.Map.DirectEdge;
import AppInterface.Map.LineColor;
import AppInterface.Map.Station;

import java.awt.*;
import java.util.ArrayList;

public class EdgePainter {

    public static void drawAllEdges(Graphics2D g, ArrayList<DirectEdge> arrayE, ArrayList<Station> arrayS) {

        g.setStroke(new BasicStroke(5.0f));

        Station tempBegin;
        Station tempEnd;

        for (DirectEdge directEdge : arrayE) {

            tempBegin = arrayS.get(directEdge.getBeginStationID());
            tempEnd = arrayS.get(directEdge.getEndStationID());

            for (int i = 0; i < directEdge.getSpecialCordX().size() - 1; i++) {

//                g.setColor(Color.BLACK);
//                g.setStroke(new BasicStroke(7.0f));
//                g.drawLine(directEdge.getSpecialCordX().get(i), directEdge.getSpecialCordY().get(i),
//                        directEdge.getSpecialCordX().get(i + 1), directEdge.getSpecialCordY().get(i + 1));

                g.setColor(LineColor.getColor(tempBegin.getLineID(), tempBegin.isTrigger() && tempEnd.isTrigger()));
                g.setStroke(new BasicStroke(5.0f));
                g.drawLine(directEdge.getSpecialCordX().get(i), directEdge.getSpecialCordY().get(i),
                        directEdge.getSpecialCordX().get(i + 1), directEdge.getSpecialCordY().get(i + 1));

            }


        }

    }
}