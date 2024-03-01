package myproject;

import java.util.ArrayList;
import java.util.Random;

import myproject.NodeMap.Node;

public class Agent {
  
  Random rand = new Random();

  ArrayList<Node> opened = new ArrayList<Node>();
  NodeMap map;
  Ui ui;
  Node start;
  Node end;

  public Agent(NodeMap map, Ui ui) {
    this.map = map;
    this.ui = ui;  
  }

  public void setStart(int x, int y){
    start = map.getNode(x, y);
  }

  public void setEnd(int x, int y) {
    end = map.getNode(x, y);
  }
  

  public Node rrt() {
    opened.add(start);
    Node current = start;

    while ( current != end) {
      Node sample = sample();
      Node nearest = getClosestOpen(sample);
      Node next = extend(nearest,sample);
      next.setOrigin(nearest);
      current = next;
      opened.add(current);
    }
    System.out.println("Done!");
    return current;
  }

  public Node sample() {

    Node result;

    do {
      result = map.getNode(randNum(), randNum());
    } while( isOpen(result) );

    return result;
  }

  public int randNum() {
    
    int factor = map.n / map.OFFSET;

    return rand.nextInt(factor + 1) * map.OFFSET;
  }

  //POF
  private Boolean isOpen(Node n) {
    for ( Node node: opened) {
      if(node == n) return true;
    }
    return false;
  }

  private Node getClosestOpen(Node target){
    Node result = null;
    double minDistance = 9999;

    for ( Node node: opened){
      double distance = map.getDistance(target, node);
      if ( distance < minDistance) {
        result = node;
        minDistance = distance;
      }
    }
    return result;
  }
  
  private Node extend(Node n, Node target){

    double distance = map.getDistance(n, target);

    double x = target.x - n.x;
    double y = target.y - n.y;

    x /= distance;
    y /= distance;

    int xn = (int) Math.round(x) * map.OFFSET;
    int yn = (int) Math.round(y) * map.OFFSET;

    return map.getNode(n.x + xn, n.y + yn);    
  }

}
