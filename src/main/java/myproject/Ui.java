package myproject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import myproject.NodeMap.Node;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class Ui {
    
    static final int NODESIZE = 2;

    JFrame menu = new JFrame( "Drawing Polygons" );
    Graphics g;
    NodeMap map;
    ArrayList<Polygon> polygons;
    Node root;
    ArrayList<Node> opened;

    public Ui(NodeMap map) {
        this.map = map;
        g = menu.getGraphics(); 
        menu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        menu.setSize( map.n, map.n );
        drawMenuButton(menu); 
        menu.setBackground(Color.BLACK);
    }

    public void setEnvironment(ArrayList<Polygon> polygons, Node root ) {
        this.polygons = polygons;
        this.root = root;
    }

    public void setOpened(ArrayList<Node> opened) {
        this.opened = opened;
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
    
    
        
    public void drawMenuButton(JFrame f) {
        JButton b=new JButton("Test Case 1");  
        b.setBounds(250, 250,95,30);
        
        b.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {

                drawFullResult();
            }

        });
        f.add(b);
    }
    
 }

    




