package myproject;

import javax.swing.JFrame;
import javax.swing.JPanel;

import myproject.NodeMap.Node;

import java.awt.Graphics;

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
    public DrawPath(Node n) {
        root = n;
    }

    public void paintComponent(Graphics g) {
        
        Node current = root;
        while ( current != null) {
            if(current.origin != null) g.drawLine(current.x, current.y, current.origin.x, current.origin.y);
            current = current.origin;
        }
        
    }
}
    public void drawResult(Node n) {
    frame.add(new DrawPath(n));
    }

}


