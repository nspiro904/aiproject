package myproject;

import javax.swing.JFrame;
import javax.swing.JPanel;

import myproject.NodeMap.Node;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Iterator;

public class Ui {
    
    static final int NODESIZE = 5;

    JFrame frame = new JFrame( "Drawing Polygons" );
    Graphics g;
    NodeMap map;

    public Ui(NodeMap map) {
        this.map = map;
        g = frame.getGraphics(); 
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize( map.n, map.n ); 
    }

class DrawPath extends JPanel {  

    Node root;
    ArrayList<Polygon> polygons;
    public DrawPath(Node n, ArrayList<Polygon> polygons) {
        root = n;
        this.polygons = polygons;
    }

    public void paintComponent(Graphics g) {
        
        Node current = root;

        Iterator<Polygon> polygonIterator = polygons.listIterator();

        while(polygonIterator.hasNext()) {
            g.fillPolygon(polygonIterator.next());
        }

        g.setColor(Color.red);

        while ( current != null) {
            if(current.origin != null) g.drawLine(current.x, current.y, current.origin.x, current.origin.y);
            current = current.origin;
        }
        
    }
}
    public void drawResult(Node n, ArrayList<Polygon> polygons) {
    frame.add(new DrawPath(n, polygons));
    }

    class DrawFullPath extends JPanel {  

        ArrayList<Node> nodes;
        ArrayList<Polygon> polygons = new ArrayList<>();

        public DrawFullPath(ArrayList<Node> n, ArrayList<Polygon> polygons) {
            nodes = n;
            this.polygons = polygons;
        }
    
        public void paintComponent(Graphics g) {
            
            Iterator<Polygon> polygonIterator = polygons.listIterator();

        while(polygonIterator.hasNext()) {
            g.fillPolygon(polygonIterator.next());
        }

        g.setColor(Color.red);
            for(Node n: nodes) {
            if(n.origin != null) g.drawLine(n.x, n.y, n.origin.x, n.origin.y);
            
            }
            
        }
    }
        public void drawFullResult(ArrayList<Node> n, ArrayList<Polygon> polygons) {
        frame.add(new DrawFullPath(n, polygons));
        }
}





