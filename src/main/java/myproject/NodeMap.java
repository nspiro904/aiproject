package myproject;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class NodeMap {

    static final int OFFSET = 5;

    HashMap<String,Node> map = new HashMap<String,Node>(); 
    int n;
    public NodeMap(int n) {
        this.n = n;

        for ( int i = 0; i <= n; i += OFFSET) {
            for ( int j = 0; j <= n; j += OFFSET) {
                insertNode(i, j);
            }
        }

        for  (HashMap.Entry<String,Node> entry : map.entrySet()) {
            Node node = entry.getValue();
            node.setNeighbors();
        }
    }

    
    public void insertNode(int x, int y) {
        Node n = new Node(x,y);
        map.put(n.key, n);
    }


    public class Node {
        int x;
        int y;
        String key;
        Node origin = null;
        ArrayList<Node> neighbors = new ArrayList<Node>();

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            key = x + "/" + y;
        }

        public void setNeighbors() {
            neighbors.add(map.get(coords2key(x, y + OFFSET))); //down
            neighbors.add(map.get(coords2key(x, y - OFFSET))); //up
            neighbors.add(map.get(coords2key(x + OFFSET, y))); //right
            neighbors.add(map.get(coords2key(x - OFFSET, y))); //left
            neighbors.add(map.get(coords2key(x + OFFSET, y + OFFSET)));
            neighbors.add(map.get(coords2key(x - OFFSET, y + OFFSET)));
            neighbors.add(map.get(coords2key(x - OFFSET, y - OFFSET)));
            neighbors.add(map.get(coords2key(x + OFFSET, y - OFFSET)));


        }

        public void setOrigin(Node n){
            origin = n;
        }

        public Node getOrigin(){
            return origin;
        }

        
    }

    public int[] key2coords(String k) {
        int[] coords = new int[2];
        String[] split = k.split("/");

        coords[0] = Integer.parseInt(split[0]);
        coords[1] = Integer.parseInt(split[1]);

        return coords;
    }

    public String coords2key(int x, int y) {
        return x + "/" + y;
    }

    public Node getNode(int x, int y) {
         
        Node result = map.get(coords2key(x,y));
        return result;
    }

    public double getDistance(Node n1, Node n2) {
        double diff1 = n2.x - n1.x;
        double diff2 = n2.y - n1.y;
        return Math.sqrt((diff1 * diff1) + (diff2 * diff2));
    }

    public ArrayList<Point> trimNodes(ArrayList<Polygon> polygons) {
        
        ArrayList<String> remove = new ArrayList<>();
        ArrayList<Point> points = new ArrayList<>();

        for(Polygon p: polygons) {

        for (Map.Entry<String,Node> entry : map.entrySet()) {
            Node n = entry.getValue();
            if (p.contains(n.x, n.y)){ 
                remove.add(entry.getKey());
                points.add(new Point(n.x, n.y));
            }
        }
    }

        for (String key: remove) {
            map.remove(key);
        }

        return points;
    }
}
