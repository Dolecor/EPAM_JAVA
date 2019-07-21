package AppInterface.Map;

import AppInterface.Panel;

public class RoundEdge {

    private int beginStationID;
    private int endStationID;

    private int centerX;
    private int centerY;

    private int startAngle;
    private int endAngle;

    private int r;

    private int lineID;

    public RoundEdge(int beginStationID, int endStationID, int centerX, int centerY, int r, int startAngle, int endAngle){
        this.beginStationID = beginStationID;
        this.endStationID = endStationID;
        this.centerX = centerX;
        this.centerY = centerY;
        this.startAngle = startAngle;
        this.endAngle = endAngle;
        this.r = r;
        this.lineID = Panel.getStations().get(beginStationID).getLineID();
    }

    public int getBeginStationID() {
        return beginStationID;
    }

    public int getEndStationID() {
        return endStationID;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getStartAngle() {
        return startAngle;
    }

    public int getEndAngle() {
        return endAngle;
    }

    public int getR() {
        return r;
    }

    public int getLineID() {
        return lineID;
    }
}
