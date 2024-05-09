package panels;

import utilities.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameOverScreen extends JPanel implements KeyListener {

    static int score = 0;

    JLabel gameOverLabel;
    static JLabel finalScoreLabel;
    JLabel pressSpaceBar;

    public static boolean isDead = false;

    GridBagConstraints gbc = new GridBagConstraints();

    public GameOverScreen() {
        addKeyListener(new Input());
        this.setLayout(new GridBagLayout());

        gameOverLabel = new JLabel("Game Over!!!");
        finalScoreLabel = new JLabel("Your final score is: " + score);
        pressSpaceBar = new JLabel("Press SpaceBar to start a new game.");

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;

        gbc.gridy = 0;
        this.add(gameOverLabel, gbc);

        gbc.gridy = 1;
        this.add(finalScoreLabel, gbc);

        gbc.gridy = 2;
        this.add(pressSpaceBar, gbc);
    }


    public static void setScore(int finalScore) {
        score = finalScore;
    }

    public static void fillScoreLabel() {
        finalScoreLabel.setText("Your final score is: " + score);
    }

    public static void setIsDead(boolean setIsDead) {
        isDead = setIsDead;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_SPACE) {
//            if (GameOverScreen.isDead) {
            System.out.println("SPACEBARRRR");
//            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
