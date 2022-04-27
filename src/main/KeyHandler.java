package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    GamePanel gp;

    KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // For the title screen
        if (gp.atTitleScreen) {
            if (code == KeyEvent.VK_DOWN) {
                gp.ui.optionNumber += 1;
                gp.ui.optionNumber %= gp.ui.numberOfTitleOptions;
            }
            if (code == KeyEvent.VK_UP) {
                if (gp.ui.optionNumber <= 0) {
                    gp.ui.optionNumber = gp.ui.numberOfTitleOptions - 1;
                } else {
                    gp.ui.optionNumber--;
                }
            }
        }

        // for the difficulty screen
        if (gp.atSelectDifficulty) {
            if (code == KeyEvent.VK_DOWN) {
                gp.ui.difficulty += 1;
                gp.ui.difficulty %= gp.ui.numberOfDifficulties;
            }
            if (code == KeyEvent.VK_UP) {
                if (gp.ui.difficulty <= 0) {
                    gp.ui.difficulty = gp.ui.numberOfDifficulties - 1;
                } else {
                    gp.ui.difficulty--;
                }
            }

        }

        // for player movement
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }

        // for pausing the game
        if (code == KeyEvent.VK_P) {
            gp.paused = !gp.paused;
        }

        // leaderboard screen
        if (code == KeyEvent.VK_ENTER && gp.ui.optionNumber == 2) {
            gp.atCongrats = false;
            gp.playing = false;
            gp.atSelectDifficulty = false;
            gp.paused = false;
            gp.gameOver = false;
            gp.atTitleScreen = false;
            gp.toLeaderboard = true;
        }

        // for exiting from the title screen
        if (code == KeyEvent.VK_ENTER && gp.ui.optionNumber == 3) {
            System.exit(0);
        }

        // for starting the new game from the title screen
        if (code == KeyEvent.VK_ENTER && gp.ui.optionNumber == 0) {
            gp.atTitleScreen = false;
            gp.atSelectDifficulty = true;
            // gp.playing = true;
        }

        // Select difficulty after the title screen
        if (gp.atSelectDifficulty) {
            if (code == KeyEvent.VK_SPACE) {
                gp.difficulty = gp.ui.difficulty;
                gp.atSelectDifficulty = false;
                gp.playing = true;
            }
        }

        // for starting the loaded game from the title screen
        if (code == KeyEvent.VK_ENTER && gp.ui.optionNumber == 1) {
            gp.toLoad = true;
            gp.playing = true;
            gp.paused = false;
            gp.atCongrats = false;
            gp.atTitleScreen = false;
        }

        // press esc to return to title screen while playing the game or while paused
        if (code == KeyEvent.VK_ESCAPE) {
            gp.playing = false;
            gp.paused = false;
            gp.gameOver = false;
            gp.atTitleScreen = true;
            gp.toSave = true;
        }

        if (gp.player.score == 240) {
            gp.player.score = 0;
            gp.playing = false;
            gp.atCongrats = true;
            gp.toPushtoLeaderboard = true;
            //gp.enterName=true;
            gp.gameOver = false;
            gp.paused = false;
            gp.atTitleScreen = false;
            
        }

    }

}