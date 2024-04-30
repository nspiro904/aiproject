package myproject;

import javax.swing.JFrame;
import javax.swing.JPanel;

import myproject.NodeMap.Node;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Ui {
    
    static final int NODESIZE = 2;

    JFrame menu = new JFrame( "Drawing Polygons" );
    Graphics g;
    NodeMap map;
    ArrayList<Polygon> polygons;
    ArrayList<Polygon> polygonsInvisible;
    Node root;
    ArrayList<Node> opened;
    ArrayList<Polygon> test;

    public Ui(NodeMap map) {
        this.map = map;
        g = menu.getGraphics(); 
        menu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        menu.setSize( map.n, map.n);
        menu.setBackground(Color.BLACK);
    }

    public void setEnvironment(ArrayList<Polygon> polygons, Node root ) {
        this.polygons = polygons;
        this.root = root;
    }

    public void setOpened(ArrayList<Node> opened) {
        this.opened = opened;
    }

    public void setPolygonsInvisible(ArrayList<Polygon> polygons) {
        polygonsInvisible = polygons;
    }

    public void setPolygonsTest(ArrayList<Polygon> test) {
        this.test = test;
    }

    class DrawPath extends JPanel {  

        public DrawPath() {
        }

        public void paintComponent(Graphics g) {
            
            Node current = root;

            Iterator<Polygon> polygonIterator = polygons.listIterator();

            g.setColor(Color.WHITE);

            while(polygonIterator.hasNext()) {
                g.fillPolygon(polygonIterator.next());
            }

            g.setColor(Color.RED);
            g.fillOval(current.x - NODESIZE, current.y - NODESIZE, 10, 10);

            g.setColor(Color.YELLOW);

            while ( current != null) {
                if(current.origin != null) g.drawLine(current.x, current.y, current.origin.x, current.origin.y);
                if(current.origin == null) {
                    g.setColor(Color.GREEN);
                    g.fillOval(current.x - NODESIZE, current.y - NODESIZE, 10, 10);
                }
                current = current.origin;
            }

        }

        
    }

    class DrawFullPath extends JPanel {  

        public DrawFullPath() {
        }

        public void paintComponent(Graphics g) {
            
            Iterator<Polygon> polygonIterator = polygons.listIterator();

        while(polygonIterator.hasNext()) {
            g.fillPolygon(polygonIterator.next());
        }

        
        g.setColor(Color.GRAY);
            for(Node n: opened) {
            if(n.origin != null) g.drawLine(n.x, n.y, n.origin.x, n.origin.y);
            
            }

        Node current = root;

        g.setColor(Color.RED);
        g.fillOval(current.x - NODESIZE, current.y - NODESIZE, 10, 10);

        g.setColor(Color.YELLOW);

        while ( current != null) {
            if(current.origin != null) g.drawLine(current.x, current.y, current.origin.x, current.origin.y);
            if(current.origin == null) {
                g.setColor(Color.GREEN);
                g.fillOval(current.x - NODESIZE, current.y - NODESIZE, 10, 10);
            }
            current = current.origin;
        }

        if(polygonsInvisible != null) {

            g.setColor(getBackground());
            Iterator<Polygon> polygonInvisibleIterator = polygonsInvisible.listIterator();

            while(polygonInvisibleIterator.hasNext()) {
                g.fillPolygon(polygonInvisibleIterator.next());
            }
                
        }
         
        if ( test != null) {
            Iterator<Polygon> testPolygonIterator = test.listIterator();

            while(testPolygonIterator.hasNext()) {
                g.fillPolygon(testPolygonIterator.next());
            }
        }

        g.setColor(Color.PINK);

        //test vertical
        // for(Map.Entry<String, NodeMap.Node> entry: map.map.entrySet() ) {
            
        //     Node n = entry.getValue();
        //     int[] coords = map.key2coords(entry.getKey());

        //     if(n.up != null) {
        //     if(n.up) g.fillOval(coords[0], coords[1], 2, 2);
        //     else {
        //         g.setColor(Color.orange);
        //         g.fillOval(coords[0], coords[1], 2, 2);
        //     }

        //     g.setColor(Color.PINK);
        // }
        // }

        g.setColor(Color.GREEN);
        // test horizontal
        // for(Map.Entry<String, NodeMap.Node> entry: map.map.entrySet() ) {
            
        //     Node n = entry.getValue();
        //     int[] coords = map.key2coords(entry.getKey());

        //     if(n.right != null) {
        //     if(n.right) g.fillOval(coords[0], coords[1], 2, 2);
        //     else {
        //         g.setColor(Color.magenta);
        //         g.fillOval(coords[0], coords[1], 2, 2);
        //     }

        //     g.setColor(Color.GREEN);
        // }
        // }
        }
    }

    public void drawResult() {
        DrawPath result = new DrawPath();
        JFrame frame = new JFrame( "Attempt 1" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize( map.n, map.n );
        frame.setBackground(Color.BLACK);
        frame.add(result);
        frame.setVisible(true);
        }

    public void drawFullResult() {
        DrawFullPath result = new DrawFullPath();
        JFrame frame = new JFrame( "Attempt 1" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize( map.n, map.n );
        frame.setBackground(Color.BLACK);
        frame.add(result);
        frame.setVisible(true);
        }
 }

    




