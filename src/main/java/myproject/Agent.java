package myproject;

import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Random;

import myproject.NodeMap.Node;

public class Agent {
  
  Random rand = new Random();

  ArrayList<Polygon> polygons;
  ArrayList<Node> opened = new ArrayList<Node>();
  NodeMap map;
  Ui ui;
  Node start;
  Node end;

  public Agent(NodeMap map, Ui ui, ArrayList<Polygon> polygons) {
    this.map = map;
    this.ui = ui;
    this.polygons = polygons;
  }

  public void setStart(int x, int y){
    start = map.getNode(x, y);
  }

  public void setEnd(int x, int y) {
    end = map.getNode(x, y);
  }
  

  public Node rrt() {
    opened = new ArrayList<Node>();
    opened.add(start);
    Node current = start;
    map.trimNodes(polygons);
    while ( current != end) {
      Node sample = sample();
      Node nearest = getClosestOpen(sample);
      Node next = extend(nearest,sample);

      if(next != null){
      next.setOrigin(nearest);
      current = next;
      opened.add(current);
      }
    }
    System.out.println("nodes opened: " + opened.size());
    return current;
  }

  public Node smallTreeRRT() {
    Node result;
    long start = System.currentTimeMillis();
    do {
      result = rrt();
    } while( opened.size() > 5000);

    long time = System.currentTimeMillis() - start;

    System.out.println("done in " + time + "ms");
    return result;
  }

  public Node sample() {

    Node result;

    do {
      result = map.getNode(randNum(), randNum());
    } while( isOpen(result) || result == null);

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


  public Node cityRRT() {
    opened = new ArrayList<Node>();
    opened.add(start);
    Node current = start;
    map.trimNodes(polygons);
    while ( current != end) {
      Node sample = sample();
      Node nearest = getClosestOpen(sample);
      Node next = extend(nearest,sample);
      if(next != null && isValid(nearest, next)){
      next.setOrigin(nearest);
      current = next;
      opened.add(current);
      }
    }
    System.out.println("nodes opened: " + opened.size());
    return current;
  }

  public Boolean isValid(Node origin, Node destination) {

    System.out.println(origin.x + "," + origin.y + "  /  " + destination.x + "," + destination.y + "  /  " +  origin.up + " , " + origin.right);

    int diffX = destination.x - origin.x;
    int diffY = destination.y - origin.y;

    if(destination.up != null & destination.right != null) {
      if(destination.up == false && destination.right == false) return false;
    }
    if ( origin.up != null) {
      System.out.println(diffY);
      if ( diffY == 0) return false;
      else if(diffY > 0 && origin.up) return false;
      else if(diffY < 0 && !origin.up) return false;
      else return true; 
    }


    if ( origin.right != null) {

      if ( diffX == 0) return false;
      else if (diffX > 0 && !origin.right) return false;
      else if (diffX < 0 && origin.right) return false;
      else return true;
    }
    System.out.println("validation failed");
    return false;
  }

}
