package entity;

import java.awt.image.BufferedImage;

//for all the playable, non playable characters classes
public class Entity {

    public int x, y;
    public int speed;

    public boolean collidedWithWall;
    public boolean collidedWithEntity;

    public BufferedImage image;
    public BufferedImage right, left, down, up;
    public BufferedImage right1, left1, down1, up1;
    public String direction;
    public int animCounter;
}
