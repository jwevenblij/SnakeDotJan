package utilities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import panels.GameOverScreen;
import gameobjects.BodyParts;
import panels.GameArea;

public class Input implements KeyListener {

    public static int lastDirection = 4;

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyChar()) {
            case 'w':
                if (GameArea.bodyParts.getFirst().yCoord() > 0 && GameArea.getDirection() != 2 && lastDirection != 2) {
                    GameArea.setDirection(0);
                }
                break;
            case 'd':
                if (GameArea.bodyParts.getFirst().xCoord() < 32 && GameArea.getDirection() != 3 && lastDirection != 3) {
                    GameArea.setDirection(1);
                }
                break;
            case 's':
                if (GameArea.bodyParts.getFirst().yCoord() < 32 && GameArea.getDirection() != 0 && lastDirection != 0) {
                    GameArea.setDirection(2);
                }
                break;
            case 'a':
                if (GameArea.bodyParts.getFirst().xCoord() > 0 && GameArea.getDirection() != 1 && lastDirection != 1) {
                    GameArea.setDirection(3);
                }
                break;
        }
        if (e.getKeyCode()==KeyEvent.VK_SPACE) {
            if (!GameArea.alive) {
                System.out.println("SPACEBARRRR");
                GameArea.resetGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

}
