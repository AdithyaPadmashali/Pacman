package entity;

import main.KeyHandler;
import java.awt.image.BufferedImage;

import java.util.*;
import java.awt.Graphics2D;
// import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.Board;

import main.GamePanel;

public class NPCBlue extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    Player pacman;
    BufferedImage test;
    // boolean toChangeLife;
    // public int reliefCounter;

    String[] d_list = { "up", "down", "left", "right" };
    int r_direction;
    int[][] board = new Board().getBoard();

    public NPCBlue(GamePanel gp, KeyHandler keyH, Player pacman) {
        this.gp = gp;
        this.keyH = keyH;
        this.pacman = pacman;
        this.collidedWithEntity = false;
        this.direction = "left";
        this.r_direction = 0;
        this.setDefaultValues();
        this.getPlayerImage();
        try {
            test = ImageIO.read(getClass().getResourceAsStream("/test.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPlayerImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ghost-blue.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = 6 * gp.tileSize;
        y = 3 * gp.tileSize;
        speed = 4;
    }

    private void updatePositionRandom() {

        this.collidedWithWall = false;
        gp.cChecker.checkWall2(this);
        // System.out.println(collidedWithWall);

        if (!collidedWithWall) {
            int check;
            switch (direction) {
                case "up":
                    check = y - speed;
                    if (check > 0)
                        y -= speed;
                    break;
                case "down":
                    check = y + speed;
                    if (check < gp.screenHeight - gp.tileSize)
                        y += speed;
                    break;
                case "left":
                    check = x - speed;
                    if (check > 0)
                        x -= speed;
                    break;
                case "right":
                    check = x + speed;
                    if (check < gp.screenWidth - gp.tileSize)
                        x += speed;
                    break;
            }
        } else {
            switch (direction) {
                case "up":
                    String[] possibleDirections = { "left", "right" };
                    direction = possibleDirections[0 + (int) (Math.random() * 1)];

                    break;
                case "down":
                    String[] possibleDirections1 = { "left", "right" };
                    direction = possibleDirections1[0 + (int) (Math.random() * 1)];
                    break;
                case "left":
                    String[] possibleDirections2 = { "down", "up" };
                    direction = possibleDirections2[0 + (int) (Math.random() * 1)];
                    break;
                case "right":
                    String[] possibleDirections3 = { "down", "up" };
                    direction = possibleDirections3[0 + (int) (Math.random() * 1)];
                    break;
            }
        }
    }

    private void updatePositionHard() {
        // System.out.println("detected pacman at x=" + pacman.x + " and y=" +
       // pacman.y);
       //System.out.println("out");
       direction =getNextPosition(board, pacman);

       System.out.println("D "+direction);
     
       
       if(direction.equals("up"))
       {   
          /* int rem = y%gp.tileSize;
           
           if (rem>speed || rem==0)
           y-=speed;
           else
           y-=rem;*/

           y-=speed;
           int currRow = y/gp.tileSize;
           int currColumn = x/gp.tileSize;
           if(x%gp.tileSize != 0){
           if (board[currRow][currColumn+1] == 1)
           {
               x=gp.tileSize*currColumn;
               y=gp.tileSize*currRow;
           }}
       }
       if(direction.equals("down"))
       {   
           /*int rem = gp.tileSize-(y%gp.tileSize);
          
           if (rem > speed || rem ==0)
           y+=speed;
           else
           y+=rem;*/
           y+=speed;
           int currRow = (y+gp.tileSize)/gp.tileSize;
           int currColumn = x/gp.tileSize;
           if(x%gp.tileSize != 0){
           if (board[currRow][currColumn] == 1)
           {
               y=gp.tileSize * (currRow-1);
               x=gp.tileSize*currColumn;
           }
           else if (board[currRow][currColumn-1] == 1)
           {
               y=gp.tileSize * (currRow-1);
               x=gp.tileSize*currColumn;
           }}
       }
       if(direction.equals("left"))
       {  
          

           
        /*   int rem = x%gp.tileSize;
           if(rem>speed || rem==0)
           x-=speed;
           else
           x-=rem;*/

           x-=speed;
           int currRow = y/gp.tileSize;
           int currColumn = x/gp.tileSize;
           if(y%gp.tileSize != 0){
           if (board[currRow+1][currColumn]==1)
           {
               x=gp.tileSize * currColumn;
               y=gp.tileSize * currRow;
           }}
       }
       if(direction.equals("right"))
       {
          
           /*int rem = gp.tileSize-(x%gp.tileSize);
           if (rem>speed || rem ==0)
           x+=speed;
           else
           x+=rem;*/

           x+=speed;
           int currRow = y/gp.tileSize;
           int currColumn = (x+gp.tileSize)/gp.tileSize;
           if(y%gp.tileSize != 0){
           if (board[currRow][currColumn] == 1)
           {  
               x=gp.tileSize*(currColumn - 1);
               y=gp.tileSize*currRow;

           }
           else if (board[currRow+1][currColumn] == 1)
           {  
               x=gp.tileSize*(currColumn -1);
               y=gp.tileSize*currRow;

           }
       }
           





       }
       if(direction.equals("caught"))
       {   
           x=x;
           y=y;
         
           //System.out.println(pacman.moved);
       }
       if(direction.equals("b"))
       {
           
           x=x;
           y=y;
       }
       
      // System.out.println("x position "+x);
      // System.out.println("y position"+y);
    
   }

   
    public String getNextPosition(int board[][], Player pacman) {
        int c = 0;
        // frontier queue
        PriorityQueue<Node> openNodeList = new PriorityQueue<Node>((x, y) -> {
            return (int) (x.distance - y.distance);

        });
        // visited array
        List<Node> closeNodeList = new ArrayList<Node>();

        Node startNode = new Node();
        int row = (x) / gp.tileSize;
        int column = (y) / gp.tileSize;

        startNode.setRow(column);
        startNode.setColumn(row);

        // System.out.println(startNode);

        Node TargetNode = new Node();
        row = (pacman.x) / gp.tileSize;
        column = (pacman.y) / gp.tileSize;

        TargetNode.setRow(column);
        TargetNode.setColumn(row);

        // System.out.println(TargetNode);

        if (startNode.equals(TargetNode)) {
            // System.out.println("caught");
            return "caught";
        }
        Node q;
        openNodeList.add(startNode);

        while (openNodeList.size() > 0 && !closeNodeList.contains(TargetNode)) {
            c++;
            q = openNodeList.poll();
            closeNodeList.add(q);

            List<Node> successors = new ArrayList<Node>();
            successors = getSuccessors(q);
            for (Node adjacentNode : successors) {
                if (!closeNodeList.contains(adjacentNode)) {
                    adjacentNode.setParentColumn(q.getColumn());
                    adjacentNode.setParentRow(q.getRow());
                    if (!openNodeList.contains(adjacentNode)) // Open list
                    {
                        int d = (Math.abs(adjacentNode.getColumn() - TargetNode.getColumn()) * 10)
                                + (Math.abs(adjacentNode.getRow() - TargetNode.getRow()) * 10);

                        adjacentNode.setDistance(d);

                        openNodeList.add(adjacentNode);
                    }
                }
            }
        }
        return getNextPosition(startNode, getNextMove(startNode, TargetNode, closeNodeList));
    }

    private static String getNextPosition(Node startNode, Node nextNode) {
        String position = "caught";
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

    public List<Node> getSuccessors(Node q) {
        List<Node> adjacentNodes = new ArrayList<Node>();
        int q_row = q.getRow();
        int q_column = q.getColumn();

        // left successor

        if ((q_column - 1) > 0 && (q_column) < 16 && (q_row) < 12) {
            if (board[q_row][q_column - 1] != 1) {
                Node q_left = new Node();
                q_left.setRow(q_row);
                q_left.setColumn(q_column - 1);
                // q_left.setParentRow(q_row);
                // q_left.setParentColumn(q_column);
                adjacentNodes.add(q_left);

            }
        }
        // right successor

        if ((q_column + 1) < 16 && (q_column) < 16 && (q_row) < 12) {
            if (board[q_row][q_column + 1] != 1) {
                Node q_right = new Node();
                q_right.setRow(q_row);
                q_right.setColumn(q_column + 1);
                // q_right.setParentRow(q_row);
                // q_right.setParentColumn(q_column);
                adjacentNodes.add(q_right);

            }
        }

        // check upwards for successor

        if ((q_row - 1) > 0 && (q_column) < 16 && (q_row) < 12) {
            if (board[q_row - 1][q_column] != 1) {
                Node q_up = new Node();
                q_up.setRow(q_row - 1);
                q_up.setColumn(q_column);
                // q_up.setParentRow(q_row);
                // q_up.setParentColumn(q_column);
                adjacentNodes.add(q_up);

            }
        }

        // check downwards for successor
        if ((q_row + 1) < 12 && (q_column) < 16 && (q_row) < 12) {
            if (board[q_row + 1][q_column] != 1) {
                Node q_down = new Node();
                q_down.setRow(q_row + 1);
                q_down.setColumn(q_column);
                // q_down.setParentRow(q_row);
                // q_down.setParentColumn(q_column);
                adjacentNodes.add(q_down);

            }
        }
        return adjacentNodes;

    }

    private static Node getNextMove(Node startNode, Node targetNode, List<Node> closeNodeList) {
        int column = targetNode.getColumn();
        int row = targetNode.getRow();
        Node node = null;
        while ((node = findNode(column, row, closeNodeList)) != null) {
            if (node.getParentColumn() == startNode.getColumn() &&
                    node.getParentRow() == startNode.getRow()) {
                break;
            } else {
                column = node.getParentColumn();
                row = node.getParentRow();
            }
        }
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

    public void update() {
        animCounter += 1;
        if (animCounter == 30) {
            animCounter = 0;
            direction = d_list[(int) ((Math.random() * (5 - 1)) + 0)];
        }

        this.collidedWithEntity = false;
        gp.cChecker.checkEntityCollision(gp.player, this);

        if (collidedWithEntity) {
            if (gp.player.rect <= 0) {
                gp.playing = false;
                gp.gameOver = true;
                gp.player.rect=4;
            }
            this.collidedWithEntity = false;
            gp.player.setDefaultValues();
            gp.player.rect -= 1;
        }

        if (gp.difficulty == 0) {
            updatePositionRandom();
        } else if (gp.difficulty == 2) {
            updatePositionHard();
        }
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
