package main;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class UI {

    GamePanel gp;
    KeyHandler keyH;
    Font arial_40;
    int x;
    int y;

    public UI(GamePanel gp, KeyHandler keyH) {
        this.x = 6 * gp.tileSize;
        this.y = 6 * gp.tileSize;
        this.gp = gp;
        this.keyH = keyH;
        arial_40 = new Font("Cambria", Font.PLAIN, 40);
    }

    public void draw(Graphics2D g2) {

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        // Display the pause screen
        if (gp.paused) {
            this.displayPause(g2);
        }
    }

    public void displayPause(Graphics2D g2) {
        g2.drawString("PAUSED", x, y);
        g2.drawString("press p to resume", x - gp.tileSize, y + gp.tileSize);
    }

}
