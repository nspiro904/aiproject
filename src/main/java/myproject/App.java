package myproject;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

public class App 
{
    public static void main( String[] args )
    {

        NodeMap map = new NodeMap(500);
        ArrayList<Polygon> polygons = getPolygonList();
        Ui ui = new Ui(map);
        Agent agent = new Agent(map, ui, polygons);
        agent.setStart(0, 0);
        agent.setEnd(440, 420);
        // ui.drawResult(agent.rrt(), polygons);
        agent.rrt();
        ui.drawFullResult(agent.opened, polygons);
        ui.frame.setVisible( true );

    }


    public static ArrayList<Polygon> getPolygonList() {
        ArrayList<Polygon> polygons = new ArrayList<>();

        Polygon p1 = new Polygon();
        p1.addPoint(200, 200);
        p1.addPoint(200, 300);
        p1.addPoint(300, 200);
        p1.addPoint(300 , 300);


        Polygon p2 = new Polygon();
        p2.addPoint(100, 100);
        p2.addPoint(150, 150);
        p2.addPoint(100, 150);
        p2.addPoint(150, 100);
        int xValues[] = { 20, 40, 50, 30, 20, 15 }; 
        int yValues[] = { 50, 50, 60, 80, 80, 60 }; 
        Polygon p3 = new Polygon( xValues, yValues, 6 );
        polygons.add(p3);
        polygons.add(p1);
        polygons.add(p2);
        return polygons;
    }
    
}
