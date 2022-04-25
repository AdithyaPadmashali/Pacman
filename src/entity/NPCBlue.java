package entity;
import java.util.*;
import main.KeyHandler;
import java.awt.Graphics2D;
// import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.Board;
public class NPCBlue extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    Player pacman;
    int r_direction;
    public int[][] board = new Board().getBoard();

    public NPCBlue(GamePanel gp, KeyHandler keyH, Player pacman) {
        this.gp = gp;
        this.keyH = keyH;
        this.pacman = pacman;
        this.r_direction = 0;
        this.setDefaultValues();
        this.getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ghost-blue.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = gp.tileSize*14;
        y = gp.tileSize*10;
        //speed = gp.tileSize;
        speed = 3;
    }

    public void update() {
        // System.out.println("detected pacman at x=" + pacman.x + " and y=" +
        // pacman.y);
        //System.out.println("out");
        direction =getNextPosition(pacman);
        //System.out.println(x +"  "+y);
        
        
        if(direction.equals("up"))
        {   pacman.a_star_moved = true;
            y-=speed;
        }
        if(direction.equals("down"))
        {
            pacman.a_star_moved = true;
            y+=speed;
        }
        if(direction.equals("left"))
        {
            pacman.a_star_moved = true;
            x-=speed;
        }
        if(direction.equals("right"))
        {
            pacman.a_star_moved = true;
            x+=speed;
        }
        if(direction.equals("caught"))
        {   if(pacman.a_star_moved)
            pacman.rect = pacman.rect-1;
            x=x;
            y=y;
            pacman.a_star_moved = false;
            System.out.println(pacman.moved);
        }
        if(direction.equals("b"))
        {
            
            x=x;
            y=y;
        }

     

    }

    public  String getNextPosition(Player pacman){ 
        int c= 0;

        //frontier queue
        PriorityQueue<Node> openNodeList = new PriorityQueue<Node>((x,y)->{
            return (int)(x.distance-y.distance  );

        });
        //visited array
       List<Node> closeNodeList= new ArrayList<Node>();
       
        
      
        Node startNode  = new Node();
        int row = x/gp.tileSize;
        int column = y/gp.tileSize;

        startNode.setRow(column);
        startNode.setColumn(row);
        //System.out.println("stnode"+startNode.getRow() +" "+ startNode.getColumn());
        Node TargetNode  = new Node();
        row = pacman.x/gp.tileSize;
        column = pacman.y/gp.tileSize;

        TargetNode.setRow(column);
        TargetNode.setColumn(row);
        // push start node into open queue
       //System.out.println("Target node"+column + " " + row);
       if (startNode.equals(TargetNode)  ){
           return "caught";
       }
        Node q;
        openNodeList.add(startNode);
       
        
        while(openNodeList.size() > 0 && !closeNodeList.contains(TargetNode) )
        {     c++;
              q = openNodeList.poll();
              closeNodeList.add(q);
            
             List<Node> successors  = new ArrayList<Node>();
             successors = getSuccessors(q);
            // System.out.println(successors.size());

            
           
             
            for (Node adjacentNode : successors) {
                if (!closeNodeList.contains(adjacentNode)) {
                    adjacentNode.setParentColumn(q.getColumn());
                    adjacentNode.setParentRow(q.getRow());
                    if (!openNodeList.contains(adjacentNode)) // Open list
                    {   int d = (Math.abs(adjacentNode.getColumn() - TargetNode.getColumn()) * 10)
                        + (Math.abs(adjacentNode.getRow() - TargetNode.getRow()) * 10);

                       // System.out.println("prow"+q.getRow()+"pcolumn"+q.getColumn()+"row"+adjacentNode.getRow()+"column"+adjacentNode.getColumn()+" "+d);
                        adjacentNode.setDistance(d);
                      // System.out.println("Node added to openNodesList  "+adjacentNode.getRow()+" "+ adjacentNode.getColumn()+" "+adjacentNode.getDistance());
                        openNodeList.add(adjacentNode); 
                    }
                }
            }

           
            // System.out.println(openNodeList.size() > 0 && !closeNodeList.contains(TargetNode));
          // if (c>2)
          // break;

        } 
        //c++;
       // System.out.println("count"+c);

        
        return getNextPosition(startNode, getNextMove(startNode, TargetNode, closeNodeList));
        
        




    }


    private static String  getNextPosition(Node startNode, Node nextNode) {
        String position = "b";
        if (nextNode != null) {
            if (startNode.getColumn() > nextNode.getColumn()) {
                position = "left";
            } else if (startNode.getColumn() < nextNode.getColumn()) {
                position = "right";
            } else if (startNode.getRow() > nextNode.getRow()) {
                position = "up";
            } else if (startNode.getRow() < nextNode.getRow()) {
                position = "down";
            }
        }
        return position;
    }
    private static Node getNextMove(Node startNode, Node targetNode,
    List<Node> closeNodeList) {
    int column = targetNode.getColumn();
    int row = targetNode.getRow();
    Node node = null;
    while ((node = findNode(column, row, closeNodeList)) != null) {
        if (node.getParentColumn() == startNode.getColumn()
            && node.getParentRow() == startNode.getRow()) {
            break;
        } else {
            column = node.getParentColumn();
            row = node.getParentRow();
        }
    }
   // if(node == null)
   // System.out.println("null");
    //if(node != null)
    //System.out.println("move returned"+ node.getRow() +" "+ node.getColumn());
    return node;
}

private static Node findNode(int column, int row, List<Node> closeNodeList) {
    for (Node node : closeNodeList) {
        if (node.getColumn() == column && node.getRow() == row) {
            return node;
        }
    }
    return null;
}


public List<Node> getSuccessors(Node q)
{  
    List<Node> adjacentNodes  = new ArrayList<Node>();
    //int board[][]  = m.board;
    int q_row = q.getRow();
    int q_column = q.getColumn();
    //System.out.println("length"+board[0].length);
    
    //left successor
    
    if((q_column-1) >0 && (q_column)<16 && (q_row)<12){
   // if (board[q_row][q_column-1] != 1)
    if(!collidedWithWall)
    {   Node q_left = new Node();
        q_left.setRow(q_row);
        q_left.setColumn(q_column - 1);
        //q_left.setParentRow(q_row);
        //q_left.setParentColumn(q_column);
        adjacentNodes.add( q_left);
                
    }
}
    //right successor


    if((q_column+1) < 16 && (q_column)<16 && (q_row)<12){
        //if (board[q_row][q_column+1] != 1)
        if(!collidedWithWall)
        {   Node q_right = new Node();
            q_right.setRow(q_row);
            q_right.setColumn(q_column + 1);
           // q_right.setParentRow(q_row);
           // q_right.setParentColumn(q_column);
            adjacentNodes.add( q_right);
                    
        }
    }

        // check upwards for successor

    

        if((q_row-1) > 0 && (q_column)<16 && (q_row)<12){
            //if (board[q_row-1][q_column] != 1)
            if(!collidedWithWall)
            {   Node q_up = new Node();
                q_up.setRow(q_row-1);
                q_up.setColumn(q_column);
               // q_up.setParentRow(q_row);
               // q_up.setParentColumn(q_column);
                adjacentNodes.add( q_up);
                        
            }
        }


        // check downwards for successor

        

        if((q_row+1 )< 12 && (q_column)<16 && (q_row)<12){
            //if (board[q_row+1][q_column] != 1)
            if(!collidedWithWall)
            {   Node q_down = new Node();
                q_down.setRow(q_row+1);
                q_down.setColumn(q_column);
               // q_down.setParentRow(q_row);
               // q_down.setParentColumn(q_column);
                adjacentNodes.add( q_down);
                        
            } 
        }  return adjacentNodes;

    
    
   
}

    public void draw(Graphics2D g2) {

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
