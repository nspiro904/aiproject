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
    Graphics g;
    NodeMap map;

    public Ui(NodeMap map) {
        this.map = map;
        g = frame.getGraphics(); 
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize( map.n, map.n );
        NodePanel nodePanel = new NodePanel();
        drawNode(map.getNode(15,15));
        frame.add( nodePanel );
        frame.setVisible( true ); 
    }

    public void drawNode(Node n) { 
        g.drawOval(n.x, n.y, NODESIZE, NODESIZE);
    }

class NodePanel extends JPanel{
    public void paintComponent( Graphics g ){
        // for (HashMap.Entry<String,Node> entry : map.map.entrySet()) {
        //     int[] coords = map.key2coords(entry.getKey());
        //     g.drawOval(coords[0], coords[1], NODESIZE, NODESIZE);
        // }
    }

   

       

} 
    

}
