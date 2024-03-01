package myproject;

public class App 
{
    public static void main( String[] args )
    {

        NodeMap map = new NodeMap(500);
        Ui ui = new Ui(map);
        Agent agent = new Agent(map, ui);
        agent.setStart(500, 15);
        agent.setEnd(440, 420);
        ui.drawResult(agent.rrt());
        ui.frame.setVisible( true );

    }

    
}
