package entity;

import java.awt.image.BufferedImage;

//for all the playable, non playable characters classes
public class Entity {

    public int x, y;
    public int prevX, prevY;
    public int speed;

    public BufferedImage image;
    public BufferedImage right, left, down, up;
    public BufferedImage right1, left1, down1, up1;
    public BufferedImage collidedimg;
    public String direction;
    public int animCounter;

}
