package main;

import entity.NPCBlue;
import entity.NPCGreen;
import entity.NPCPurple;
import entity.NPCWhite;
import entity.Player;

public class CollisionCheck {

    GamePanel gp;
    int[][] board = new Board().getBoard();

    public CollisionCheck(){
        
    }

    public void check( Player player, NPCBlue b, NPCGreen g, NPCPurple p, NPCWhite w){

        if((Math.abs(b.x-player.x)<16 && Math.abs(b.y-player.y)<16)){
            //System.out.println("collided");
            player.collided=true;
        }
        if((Math.abs(g.x-player.x)<16 && Math.abs(g.y-player.y)<16)){
            //System.out.println("collided");
            player.collided=true;
        }
        if((Math.abs(p.x-player.x)<16 && Math.abs(p.y-player.y)<16)){
            //System.out.println("collided");
            player.collided=true;
        }
        if((Math.abs(w.x-player.x)<16 && Math.abs(w.y-player.y)<16)){
            //System.out.println("collided");
            player.collided=true;
        }
        if(board[(player.y+2)/48][(player.x+2)/48]==1){
            //System.out.println("Colliding with Wall");
            player.collided=true;
        }
        else{
            player.collided=false;
            //System.out.println("Not Colliding with Wall");
            //System.out.println("("+player.x+", "+player.y+")");
        }
        //System.out.println("("+player.x+", "+player.y+")");
    }

    public boolean walled(int x, int y){
        if(board[(y+2)/48][(x+2)/48]==1){
            return true;
        }
        return false;
    }

}
