package myproject;

import javax.swing.JFrame;
import javax.swing.JPanel;

import myproject.NodeMap.Node;

import java.awt.Graphics;
import java.awt.Polygon;
import java.util.HashMap;

public class Ui {
    
    static final int NODESIZE = 5;

    JFrame frame = new JFrame( "Drawing Polygons" );
    NodeMap map;

    public Ui(NodeMap map) {
        this.map = map; 
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize( map.n, map.n );
        PolygonsJPanel nodePanel = new PolygonsJPanel();
        frame.add( nodePanel );
        frame.setVisible( true ); 
    }



class PolygonsJPanel extends JPanel{
 public void paintComponent( Graphics g )
 {
  super.paintComponent( g ); // call superclass's paintComponent

        for (HashMap.Entry<String,Node> entry : map.map.entrySet()) {
            int[] coords = map.key2coords(entry.getKey());
            g.drawOval(coords[0], coords[1], NODESIZE, NODESIZE);
        }
    }
} 
    

}
