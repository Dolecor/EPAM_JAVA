package AppInterface.Map;

import AppInterface.Panel;

import java.util.ArrayList;

public class DirectEdge implements Edge{

    // TODO: change id to station
    private int beginStationID;
    private int endStationID;

    private ArrayList<Integer> specialCordX;
    private ArrayList<Integer> specialCordY;

    private int lineID;


    public DirectEdge(int beginStationID, int endStationID){
        this.beginStationID = beginStationID;
        this.endStationID = endStationID;
        this.specialCordX = new ArrayList<>();
        this.specialCordY = new ArrayList<>();
        this.lineID = Panel.getStations().get(beginStationID).getLineID();
    }

    public void addCords(ArrayList<Integer> specialCordX, ArrayList<Integer> specialCordY){
        this.specialCordX.add(Panel.getStations().get(beginStationID).getCoordX());
        this.specialCordX.addAll(specialCordX);
        this.specialCordX.add(Panel.getStations().get(endStationID).getCoordX());

        this.specialCordY.add(Panel.getStations().get(beginStationID).getCoordY());
        this.specialCordY.addAll(specialCordY);
        this.specialCordY.add(Panel.getStations().get(endStationID).getCoordY());
    }

    public int getBeginStationID() {
        return beginStationID;
    }

    public int getEndStationID() {
        return endStationID;
    }

    public ArrayList<Integer> getSpecialCordX() {
        return specialCordX;
    }

    public ArrayList<Integer> getSpecialCordY() {
        return specialCordY;
    }

    public int getLineID() {
        return lineID;
    }
}
