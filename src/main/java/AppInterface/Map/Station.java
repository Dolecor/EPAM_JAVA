package AppInterface.Map;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Station{

    private int ID;
    private String name;
    private int lineID;
    private int coordX;
    private int coordY;
    private boolean trigger;
    private ArrayList<Station> neighbours;

    public Station(int ID, String name, int line, int coordX, int coordY){
        this.ID = ID;
        this.name = name;
        this.lineID = line;
        this.coordX = coordX;
        this.coordY = coordY;
        this.trigger = true;
    }


    public void addNeighbour(Station p) {
        if (neighbours.contains(p))
            return;
        neighbours.add(p);
    }

    public void turnOn() {
        trigger = true;
    }

    public void turnOff() {
        trigger = false;
    }

    public boolean isTrigger(){return trigger;}
    public int getCoordX(){return coordX;}
    public int getCoordY(){return coordY;}

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getLineID() {
        return lineID;
    }

    public ArrayList<Station> getNeighbours() {
        return neighbours;
    }
}
