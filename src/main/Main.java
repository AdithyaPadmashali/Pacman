package main;

import java.io.File;

import javax.swing.JFrame;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main {
    public static void main (String[] args) throws Exception {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pacman?");

        String soundName = "sound.wav";  
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        

        GamePanel gamePanel = new GamePanel();
        
        

        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

        if(gamePanel.gameOver){
            String soundName1 = "gameover.wav";  
        
            AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(new File(soundName1).getAbsoluteFile());
            Clip clip1 = AudioSystem.getClip();
            clip1.open(audioInputStream1);
            clip1.start();
        }
        
    }
}
