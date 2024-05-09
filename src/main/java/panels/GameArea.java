package panels;

import gameobjects.BodyParts;
import gameobjects.Food;
import utilities.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

//import static panels.CardsPanel.container;

public class GameArea extends JPanel implements ActionListener {

    static Food food = new Food(ThreadLocalRandom.current().nextInt(1, 10 + 1), ThreadLocalRandom.current().nextInt(1, 10 + 1));
    static boolean foodExists = true;
    public static boolean eatingFood = false;
    public static boolean alive = true;
    public static boolean delayDone = false;
    public static boolean foodSpawnCollisionCheck = true;
    public static int fullCheck = 0;

    public static ArrayList<BodyParts> bodyParts = new ArrayList<>();

    private static Timer timer;

    static int snakeLengthIterator = 0;

    public static int delayTime = 300;

    /*  Direction definition:
        0: North
        1: East
        2: South
        3: West
     */
    private static int direction = 1;

    public GameArea() {
        this.setLayout(new BorderLayout());
        addKeyListener(new Input());
        setFocusable(true);
        this.setBackground(Color.GRAY);
        this.setPreferredSize(new Dimension(512, 512));
        this.setMinimumSize(new Dimension(512, 512));
        this.setMaximumSize(new Dimension(512,512));
        bodyParts.add(new BodyParts(0, 0));
        generateNewFood();
        timer = new Timer(delayTime, this);
        timer.start();
    }

    // Draw call
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        if (alive) {
            g2D.setPaint(Color.white);

            // Draw all snake body parts by iterating over body length
            do {
//            g2D.fillRect(bodyParts.get(snakeLengthIterator).xCoord()*31, bodyParts.get(snakeLengthIterator).yCoord()*31, (int) ((this.getWidth()/32)*ScreenSettings.getScreenScaling()), (int) ((this.getHeight()/32)*ScreenSettings.getScreenScaling()));
                g2D.fillRect(bodyParts.get(snakeLengthIterator).xCoord()*16, bodyParts.get(snakeLengthIterator).yCoord()*16, 16, 16);
                snakeLengthIterator++;
            } while (snakeLengthIterator < bodyParts.size());

            snakeLengthIterator = 0;

//        g2D.fillRect(food.xCoord()*31, food.yCoord()*31, (int) ((this.getWidth()/32)*ScreenSettings.getScreenScaling()), (int) ((this.getHeight()/32)*ScreenSettings.getScreenScaling()));
            g2D.fillRect(food.xCoord()*16, food.yCoord()*16, 16, 16);
        } else {
            g2D.setPaint(Color.black);
            g2D.fillRect(0, 0, this.getWidth(), this.getHeight());

            g2D.setPaint(Color.white);
            g2D.drawString("Your score is " + bodyParts.size(), 20, 20);

            g2D.drawString("Press SpaceBar to restart!", 20, 35);
        }



    }

    @Override
    // Game loop
    public void actionPerformed(ActionEvent e) {
        if ((bodyParts.size() % 10 == 0) && !(delayTime <= 10) && !delayDone) {
            delayTime = delayTime - 40;
            timer.setDelay(delayTime);
            delayDone = true;
        }
        if (bodyParts.size() % 10 != 0) {
            delayDone = false;
        }
        updateBodyPart(direction);
        eatFood();
        checkForCollision();
        repaint();
    }

    public void updateBodyPart(int direction) {

        if (bodyParts.getFirst().xCoord() < 32 && bodyParts.getFirst().xCoord() > -1 && bodyParts.getFirst().yCoord() < 32 && bodyParts.getFirst().yCoord() > -1) {
            switch(direction) {
                // North (up)
                case 0:
                    if (bodyParts.getFirst().yCoord() != 0) {
                        bodyParts.addFirst(new BodyParts(bodyParts.getFirst().xCoord(), bodyParts.getFirst().yCoord() - 1));
                        bodyParts.removeLast();
                        Input.lastDirection = 0;
                    } else {
                        gameOver();
                    }
                    break;
                // East (right)
                case 1:
                    if (bodyParts.getFirst().xCoord() != 31) {
                        bodyParts.addFirst(new BodyParts(bodyParts.getFirst().xCoord() + 1, bodyParts.getFirst().yCoord()));
                        bodyParts.removeLast();
                        Input.lastDirection = 1;
                    } else {
                        gameOver();
                    }
                    break;

                // South (down)
                case 2:
                    if (bodyParts.getFirst().yCoord() != 31) {
                        bodyParts.addFirst(new BodyParts(bodyParts.getFirst().xCoord(), bodyParts.getFirst().yCoord() + 1));
                        bodyParts.removeLast();
                        Input.lastDirection = 2;
                    } else {
                        gameOver();
                    }
                    break;

                // West (left)
                case 3:
                    if (bodyParts.getFirst().xCoord() != 0) {
                        bodyParts.addFirst(new BodyParts(bodyParts.getFirst().xCoord() - 1, bodyParts.getFirst().yCoord()));
                        bodyParts.removeLast();
                        Input.lastDirection = 3;
                    } else {
                        gameOver();
                    }
                    break;

            }
        } else {
            gameOver();
        }
        eatingFood = false;
    }

    public static void eatFood() {
        if (bodyParts.getFirst().xCoord() == food.xCoord() && bodyParts.getFirst().yCoord() == food.yCoord()) {
            eatingFood = true;
            foodExists = false;
            generateNewFood();
            bodyParts.addLast(new BodyParts(bodyParts.getLast().xCoord(), bodyParts.getLast().yCoord()));
        }
    }

    public static void generateNewFood() {
        if (!foodExists) {

            do {
                food = new Food((int) (Math.random() * 31), (int) (Math.random() * 31));

                // Iterate over bodyParts array and add to fullCheck integer if food does not collide with bodyParts iteration
                for (BodyParts bodyPart : bodyParts) {
                    if (food.xCoord() != bodyPart.xCoord() && food.yCoord() != bodyPart.yCoord()) {
                        fullCheck++;
                    }
                }

                if (fullCheck == bodyParts.size()) {
                    foodSpawnCollisionCheck = false;
                }

                fullCheck = 0;

            } while (foodSpawnCollisionCheck);

            foodExists = true;
            foodSpawnCollisionCheck = true;
        }
    }

    public static void checkForCollision() {
        for (int i = 1; i < bodyParts.size(); i++) {
            if (bodyParts.size() > 2) {
                if (bodyParts.getFirst().xCoord() == bodyParts.get(i).xCoord() && bodyParts.getFirst().yCoord() == bodyParts.get(i).yCoord()) {
                    gameOver();
                }
            }
        }
    }

    public static void gameOver() {
        alive = false;
        timer.stop();
    }

    public static void resetGame() {
        food = new Food(ThreadLocalRandom.current().nextInt(1, 10 + 1), ThreadLocalRandom.current().nextInt(1, 10 + 1));
        foodExists = true;
        eatingFood = false;
        alive = true;
        bodyParts.clear();
        snakeLengthIterator = 0;
        direction = 1;
        bodyParts.add(new BodyParts(0, 0));
        delayTime = 300;
        timer.setDelay(delayTime);
        generateNewFood();
        timer.start();
    }

    public static int getDirection() {
        return direction;
    }

    public static void setDirection(int direction) {
        GameArea.direction = direction;
    }
}
