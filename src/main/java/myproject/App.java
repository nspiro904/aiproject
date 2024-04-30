package myproject;

import java.awt.Polygon;
import java.util.ArrayList;

public class App 
{

    //yes I am aware this code is awful, refactoring it would take more time than it is worth
    public static void main( String[] args )
    {
        
        //basic maze test case
        // NodeMap map1 = new NodeMap(500);
        // ArrayList<Polygon> polygons = getPolygonList();
        // Ui ui1 = new Ui(map1);
        // Agent agent1 = new Agent(map1, ui1, polygons);
        // agent1.setStart(50 , 50);
        // agent1.setEnd(440, 420);
        // ui1.setEnvironment(polygons, agent1.rrt());
        // ui1.setOpened(agent1.opened);
        // ui1.drawFullResult();

        //tunnel test case
        // NodeMap map2 = new NodeMap(500);
        // Ui ui2 = new Ui(map2);
        // ArrayList<Polygon> tunnelPolygons = getTunnelPolygons();
        // tunnelPolygons.addAll(getTunnelInvisiblePolygons());
        // Agent agent2 = new Agent(map2, ui2, tunnelPolygons);
        // agent2.setStart(20, 300);
        // agent2.setEnd(470, 300);
        // ui2.setEnvironment(getTunnelPolygons(), agent2.smallTreeRRT());
        // ui2.setPolygonsInvisible(getTunnelInvisiblePolygons());
        // ui2.setOpened(agent2.opened);
        // ui2.drawFullResult();


        //city map test case
        NodeMap map3 = new NodeMap(500);
        map3.setNodeDirectionVertical(getCityVerticalPolygons());
        map3.setNodeDirectionHorizontal(getCityHorizontalPolygons());
        Ui ui3 = new Ui(map3);
        ArrayList<Polygon> cityPolygons = getCityPolygons();
        Agent agent3 = new Agent(map3, ui3, cityPolygons);
        agent3.setStart(14,14);
        // agent3.setEnd(18, 18);
        agent3.setEnd(400, 380);
        ui3.setEnvironment(cityPolygons, agent3.rrt());
        ui3.setOpened(agent3.opened);
        ui3.drawFullResult();
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
    

    public static ArrayList<Polygon> getTunnelPolygons() {

        ArrayList<Polygon> polygons = new ArrayList<>();

        polygons.add(generateRectangle(0, 500, 225, 250));
        polygons.add(generateRectangle(0, 500, 350, 375));

        return polygons;
    }

    public static ArrayList<Polygon> getTunnelInvisiblePolygons() {

        ArrayList<Polygon> polygons = new ArrayList<>();

        polygons.add(generateRectangle(0, 500, 0, 225));
        polygons.add(generateRectangle(0, 500, 375, 500));

        return polygons;
    }


 
    public static ArrayList<Polygon> getCityPolygons() {

        ArrayList<Polygon> polygons = new ArrayList<>();

        Boolean yflag = false;
        Boolean xflag = false;

        for(int x = 0; x < 500; x += 30) {

            for ( int y = 10; y < 500; y += 40) {

                if(yflag && xflag && x + 30 < 500 && y + 40 < 500) polygons.add(generateRectangle(x, x + 30, y, y + 40)); 
                yflag = !yflag;
            }
        xflag = !xflag;
        }

        polygons.add(generateRectangle(0, 500, 470, 500));
        polygons.add(generateRectangle(495, 525, 0, 500));
        return polygons;
    }

    public static ArrayList<Polygon> getCityVerticalPolygons() {

        ArrayList<Polygon> polygons = new ArrayList<>();

        Boolean flag = true;

        for ( int i = 0; i < 500; i += 30) {
            if(flag) polygons.add(generateRectangle(i, i + 30, 0, 500));
            flag = !flag;
        }

        return polygons;
    }

    public static ArrayList<Polygon> getCityHorizontalPolygons() {

        ArrayList<Polygon> polygons = new ArrayList<>();

        Boolean flag = false;
        polygons.add(generateRectangle(-10, 510, -10, 15));
        for ( int i = 10; i < 500; i += 40) {
            if(flag) polygons.add(generateRectangle(-10, 510, i , i + 40));
            flag = !flag;
        }

        return polygons;
    }






    public static Polygon generateRectangle(int x1, int x2, int y1, int y2) {
        int x[] = {x1, x1, x2, x2};
        int y[] = {y1, y2, y2, y1};

        return new Polygon(x, y, 4);
    }
}
