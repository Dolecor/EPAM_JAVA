package AppInterface;

import AppInterface.Map.DirectEdge;
import AppInterface.Map.LineColor;
import AppInterface.Map.Painters.EdgePainter;
import AppInterface.Map.Painters.RoundEdgePainter;
import AppInterface.Map.Readers.EdgeReader;
import AppInterface.Map.Readers.RoundEdgeReader;
import AppInterface.Map.RoundEdge;
import AppInterface.Map.Station;
import AppInterface.Map.Readers.StationReader;
import AppInterface.Map.Painters.StationPainter;
import AppInterface.UserInterface.Navigation;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Panel extends JPanel implements ActionListener{

    public static int width = 1000;
    public static int height = 860;

    private Navigation navigation;

    private BufferedImage image;
    private Image background;
    private static Graphics2D g;
    private LineColor lineColor;

    private static ArrayList<Station> stations;
    private ArrayList<DirectEdge> directEdges;
    private ArrayList<RoundEdge> roundEdges;

    private Map<Pair<Integer, Integer>, ArrayList<Station>> mapStataionCords;
    private ArrayList<ArrayList<Integer>> ways;
    private static Map<Integer, ArrayList<String>> stationsByWays;

    private boolean loaded;

    Timer mainTimer;

    public Panel() throws IOException {

        super();

        this.setLayout(new GridLayout(5,5));

//        navigation = new Navigation();
//        navigation.set
        //this.add(navigation);

        setFocusable(true);
        requestFocus();
        image = new BufferedImage(Window.width, Window.height, BufferedImage.TYPE_INT_RGB);

        g = (Graphics2D) image.getGraphics();
        background = new ImageIcon("resources/images/Фон.jpg").getImage();

        lineColor = new LineColor();
        stations = new ArrayList<>();
        directEdges = new ArrayList<>();
        roundEdges = new ArrayList<>();
        ways = new ArrayList<>();

        mapStataionCords = new HashMap<>();

        navigation = new Navigation();

        stationsByWays = new HashMap<>();

        loaded = false;


       //stations.add(new Station(stations.size(), "Тест 2", 0,30,15));



        //addInterface();


    }

    public static Map<Integer, ArrayList<String>> getStationsByWays() {
        return stationsByWays;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0,0, null);
    }

    public void load() throws IOException {
        StationReader.load(stations, stationsByWays);
        for (Station station:stations){
            mapStataionCords.putIfAbsent(new Pair<>(station.getCoordX(), station.getCoordY()), new ArrayList<>());
        }
        for (Station station:stations){
            mapStataionCords.get(new Pair<>(station.getCoordX(), station.getCoordY())).add(station);
        }

        EdgeReader.load(directEdges);
        RoundEdgeReader.load(roundEdges);
    }

    public void run(){

        if (!loaded)
        try {
            load();
            loaded = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateSizeInfo();
        g.drawImage(background, (int)0, (int)0, Window.width, Window.height,null);
        drawMap();
        repaint();
            //g.dispose();
    }

    private void drawMap() {

        RoundEdgePainter.drawAllRoundEdges(g, roundEdges, stations);
        EdgePainter.drawAllEdges(g, directEdges, stations);

        for(ArrayList<Station> l: mapStataionCords.values()){
            StationPainter.drawPoint(g, l);
        }

    }

//    private void addInterface(){
//        addFilterLines();
//    }

//    private void addFilterLines(){
//        filterLineA = new JComboBox<String>();
//        filterLineB = new JComboBox<String>();
//
//        for (int i = 0; i < lines.length; i++){
//            filterLineA.addItem(lines[i]);
//            filterLineB.addItem(lines[i]);
//        }
//
//        filterLineA.setSize(200, 25);
//        filterLineB.setSize(200, 25);
//
//        filterLineA.setLocation(500, 100);
//        filterLineB.setLocation(500, 200);
//
//        this.add(filterLineA);
//        this.add(filterLineB);
//    }

    public static ArrayList<Station> getStations() {
        return stations;
    }

    public static void updateSizeInfo(){
        width = Window.getWidth();
        height = Window.getHeight();
    }
}
