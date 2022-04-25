package entity;

import main.KeyHandler;
import java.awt.Graphics2D;
// import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPCPurple extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    Player pacman;
    int r_direction;
    int prev_player_position_x;
    int prev_player_position_y;
    public NPCPurple(GamePanel gp, KeyHandler keyH, Player pacman) {
        this.gp = gp;
        this.keyH = keyH;
        this.pacman = pacman;
        this.setDefaultValues();
        this.getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ghost-purple.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = 200;
        y = 200;
        speed = 3;
    }

    public void update() {
        animCounter += 1;
        if (animCounter == 40) {
            animCounter = 0;
            r_direction = (int) ((Math.random() * (5 - 1)) + 1);
            // System.out.println("REACHED");
        }

        // double r_direction =(int)((Math.random() * (5 - 1)) + 1);
        // 1:up , 2:down , 3:left , 4:right
        

        if (r_direction == 1) {
       
            direction = "up";
            y -= speed;
        }
        if (r_direction == 2) {
           
            direction = "down";
            y += speed;
        }
        if (r_direction == 3) {
        
            direction = "left";
            x -= speed;
        }
        if (r_direction == 4) {
           
            direction = "right";
            x += speed;
        }
        int a = pacman.x;
        int b= pacman.y;
        int player_position_x = a/gp.tileSize;
        int player_position_y = b/gp.tileSize;
        System.out.println("px " + a);
        System.out.println("py " +  b);
        //System.out.println("white x " + x );
        //System.out.println("white y " + y);
        if (player_position_x==(x/gp.tileSize) && player_position_y==(y/gp.tileSize))
        {
           
            if(pacman.moved){            
            pacman.rect = pacman.rect-1; System.out.println("decremented rect in white "+pacman.rect);}
            x=x;
            y=y;
            pacman.moved = false;
            pacman.prev_player_position_x = a;
            pacman.prev_player_position_y = b;
            System.out.println("Caught by white "+pacman.moved);

        }
        System.out.println("prev x "+pacman.prev_player_position_x + " "+a);
        System.out.println("prev y "+pacman.prev_player_position_y + " "+b);
        
        System.out.println((a==pacman.prev_player_position_x));
        System.out.println((b==pacman.prev_player_position_y));
        System.out.println((a==pacman.prev_player_position_x) && (b==player_position_y));
        if((a==pacman.prev_player_position_x) ) 
        {   if(b==player_position_y)
            pacman.moved = false;
            
        }
        else
        {
            System.out.println("set to true");
            pacman.moved = true;
        }

       
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
