package entity;
import java.util.*;


public class A_star {
    public int board[][];
    public NPCBlue ghost;
    public Player pacman;
    public Node output;
    A_star(int board[][], NPCBlue ghost, Player pacman){
        this.ghost = ghost;
        this.pacman = pacman;
        this.board = board;
        output = new Node();
    }
    public  Node getNextPosition(){
        PriorityQueue<Node> openNodeList = new PriorityQueue<Node>((x,y)->{
            return (int)(y.distance - x.distance);

        });
        List<Node> closeNodeList = new ArrayList<Node>();
        Node startNode  = new Node();
        startNode.setRow(ghost.x);
        startNode.setColumn(ghost.y);

        Node TargetNode  = new Node();
        TargetNode.setRow(pacman.x);
        TargetNode.setColumn(pacman.y);
        // push start node into open queue
        Node q;
        openNodeList.add(startNode);
        while(!openNodeList.isEmpty())
        {
              q = openNodeList.poll();
              PriorityQueue<Node> successors = new PriorityQueue<Node>((x,y)->{
                return (int)(y.distance - x.distance);
    
            });
           // successors = getSuccessors(q);

        } return startNode;
        




    }
  /*  public PriorityQueue<Node> getSuccessors(Node q)
    {     int q_row = q.getRow();
        int q_column = q.getColumn();
         // if (board[q_row][q_column-1])
    }*/
    
}
