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

    // for title screen
    int numberOfTitleOptions;
    int optionNumber;

    // for select difficulty screen
    int numberOfDifficulties;
    int difficulty;

    public UI(GamePanel gp, KeyHandler keyH) {
        this.x = 3 * gp.tileSize;
        this.y = 3 * gp.tileSize;
        this.gp = gp;
        this.optionNumber = 0;
        this.numberOfTitleOptions = 3;
        this.numberOfDifficulties = 3;
        this.difficulty = 0;
        this.keyH = keyH;
        arial_40 = new Font("Cambria", Font.PLAIN, 40);
    }

    public void draw(Graphics2D g2) {

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        // Display the pause screen
        if (gp.paused) {
            this.displayPause(g2);
        } else if (gp.atTitleScreen) {
            this.displayTitleScreen(g2);
        } else if (gp.atSelectDifficulty) {
            this.displaySelectDifficulty(g2);
        }
    }

    private void displaySelectDifficulty(Graphics2D g2) {
        // Draw the title in bold
        g2.drawString("Select Difficulty", x + 3 * gp.tileSize, y);

        // draw options
        g2.drawString("Easy", x + 3 * gp.tileSize, y + gp.tileSize * 3);
        if (difficulty == 0) {
            g2.drawString(">", x + 2 * gp.tileSize, y + gp.tileSize * 3);
        }

        g2.drawString("Medium", x + 3 * gp.tileSize, y + gp.tileSize * 4);
        if (difficulty == 1) {
            g2.drawString(">", x + 2 * gp.tileSize, y + gp.tileSize * 4);
        }

        g2.drawString("Hard", x + 3 * gp.tileSize, y + gp.tileSize * 5);
        if (difficulty == 2) {
            g2.drawString(">", x + 2 * gp.tileSize, y + gp.tileSize * 5);
        }
    }

    private void displayTitleScreen(Graphics2D g2) {

        // Draw the title in bold
        g2.drawString("PACMAN", x + 3 * gp.tileSize, y);
        g2.drawString("PACMAN", x + 3 * gp.tileSize + 2, y + 2);

        // draw options
        g2.drawString("New Game", x + 3 * gp.tileSize, y + gp.tileSize * 3);
        if (optionNumber == 0) {
            g2.drawString(">", x + 2 * gp.tileSize, y + gp.tileSize * 3);
        }

        g2.drawString("Load Game", x + 3 * gp.tileSize, y + gp.tileSize * 4);
        if (optionNumber == 1) {
            g2.drawString(">", x + 2 * gp.tileSize, y + gp.tileSize * 4);
        }

        g2.drawString("Exit", x + 3 * gp.tileSize, y + gp.tileSize * 5);
        if (optionNumber == 2) {
            g2.drawString(">", x + 2 * gp.tileSize, y + gp.tileSize * 5);
        }
    }

    public void displayPause(Graphics2D g2) {
        g2.drawString("PAUSED", x + 4 * gp.tileSize, y);
        g2.drawString("press p to resume", x + 2 * gp.tileSize, y + gp.tileSize);
    }

}
