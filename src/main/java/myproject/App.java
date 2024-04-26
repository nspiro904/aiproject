package myproject;

import java.awt.Polygon;
import java.util.ArrayList;

public class App 
{
    public static void main( String[] args )
    {

        NodeMap map = new NodeMap(500);
        ArrayList<Polygon> polygons = getPolygonList();
        Ui ui = new Ui(map);
        Agent agent = new Agent(map, ui, polygons);
        agent.setStart(50 , 50);
        agent.setEnd(440, 420);
        ui.setEnvironment(polygons, agent.rrt(), agent.opened);
        // agent.rrt();
        // ui.drawFullResult(agent.opened, polygons);
        ui.menu.setVisible( true );

    }


    public static ArrayList<Polygon> getPolygonList() {
        ArrayList<Polygon> polygons = new ArrayList<>();


        int xValues[] = { 20, 40, 50, 30, 20, 15 }; 
        int yValues[] = { 50, 50, 60, 80, 80, 60 }; 
        Polygon p3 = new Polygon( xValues, yValues, 6 );


        int x[] = { 210, 230, 240, 250, 310, 340 }; 
        int y[] = { 170, 140, 80, 70, 60, 40 }; 
        int numberofpoints = 6;
        Polygon p4 = new Polygon(x, y, numberofpoints);
  
        polygons.add(generateRectangle(200, 300, 200, 300));
        polygons.add(generateRectangle(100, 150, 100, 150));
        polygons.add(generateRectangle(400, 450, 300, 350));
        polygons.add(generateRectangle(250, 300, 400, 450));
        polygons.add(generateRectangle(250, 300, 330, 370));
        polygons.add(generateRectangle(320, 350, 370, 420));
        polygons.add(p3);
        polygons.add(p4);
        return polygons;
    }
    

    public static Polygon generateRectangle(int x1, int x2, int y1, int y2) {
        int x[] = {x1, x1, x2, x2};
        int y[] = {y1, y2, y2, y1};

        return new Polygon(x, y, 4);
    }
}
