// Benjamin A. Worthington
// September 9th, 2021
// CSCE 3193 Assignment 3 (imported from Assignment 2) - A simple map editor where the user can
// draw bricks and scroll left and right
// Controller.java

// Import statements
import java.awt.event.*;
import java.util.Iterator;

class Controller implements MouseListener, KeyListener {

    // Declaring member variables for object references
    View view;
    Model model;

    // Declaring private member variable to track if game is in editing mode (true) or play mode (false)
    private boolean editingMode;

    // Declaring private member variables to hold temp xPos and yPos
    private int tempXPos;
    private int tempYPos;

    // Declaring private member variables for the left and right arrow keys
    private boolean keyLeftArrow;
    private boolean keyRightArrow;
    private boolean keyUpArrow;
    private boolean keySpacebar;

    // Controller constructor
    Controller(Model m) {
        model = m;
        editingMode = false;   // start the game in 'play' mode
    }

    // Getter and Setter methods
    void setView(View v) {
        view = v;
    }

    public boolean isEditingMode() {
        return editingMode;
    }

    public void setEditingMode(boolean editingMode) {
        this.editingMode = editingMode;
    }

    public boolean isColliding(Brick brick) {

        int marioRight = (model.mario.getXPos() + model.mario.getMarioImages()[model.mario.getMarioImageCount()].getWidth());
        int marioToes = (model.mario.getYPos() + model.mario.getMarioImages()[model.mario.getMarioImageCount()].getHeight());
        int brickRight = (brick.getxPos() + brick.getWidth());
        int brickBottom = (brick.getyPos() + brick.getHeight());

        // Left
        if (marioRight < brick.getxPos()) {
            // System.out.println("Not colliding on left");
            return false;
        }

        // Right
        if (model.mario.getXPos() > brickRight) {
            return false;
        }

        // Top
        if (marioToes < brick.getyPos()) {
            return false;
        }

        // Bottom
        if (model.mario.getYPos() > brickBottom) {
            return false;
        }

        return true;

    }

    void getOutOfBrick() {

        for (int i = 0; i < model.getBricks().size(); i++) {

            Brick temp = model.getBricks().get(i);

            int brickLeft = temp.getxPos();
            int brickRight = (temp.getxPos() + temp.getWidth());
            int brickTop = temp.getyPos();
            int brickBottom = (temp.getyPos() + temp.getHeight());

            int marioLeft = model.mario.getXPos();
            int marioRight = (model.mario.getXPos() + model.mario.getMarioImages()[model.mario.getMarioImageCount()].getWidth());
            int marioHead = model.mario.getYPos();
            int marioToes = (model.mario.getYPos() + model.mario.getMarioImages()[model.mario.getMarioImageCount()].getHeight());
            int marioWidth = model.mario.getMarioImages()[model.mario.getMarioImageCount()].getWidth();
            int marioHeight = model.mario.getMarioImages()[model.mario.getMarioImageCount()].getHeight();

            if (isColliding(temp)) {

                // Horizontal Collision Detection
                if (marioRight >= brickLeft && model.mario.getPreviousXPos() + marioWidth <= brickLeft) {
                    model.mario.setXPos((brickLeft - marioWidth) - 1);
                }

                if (marioLeft <= brickRight && model.mario.getPreviousXPos() >= brickRight) {
                    model.mario.setXPos(brickRight);
                }

                // Vertical Collision Detection
                if (marioToes >= brickTop && model.mario.getPreviousYPos() + marioHeight <= brickTop) {

                    model.mario.setYPos(brickTop - marioHeight);
                    model.mario.setVerticalVelocity(0);
                    model.mario.setMarioJumpCounter(0);

                }

                if (marioHead <= brickBottom && model.mario.getPreviousYPos() >= brickBottom) {

                    model.mario.setYPos(brickBottom);
                    model.mario.setVerticalVelocity(0);

                }

            }

        }

        model.mario.setPreviousXPos(model.mario.getXPos());
        model.mario.setPreviousYPos(model.mario.getYPos());

    }

    // Update method
    void update() {

        getOutOfBrick();

        // If left or right key is pressed, move mario
        if (keyLeftArrow) {
            model.mario.setXPosLeft(5);
        }

        if (keyRightArrow) {
            model.mario.setXPosRight(5);
        }

        // If left or right arrow are pressed, move ("animate") mario
        if (keyLeftArrow || keyRightArrow) {

            model.mario.incrementMarioImageCount();

            if (model.mario.getMarioImageCount() > 4) {
                model.mario.setMarioImageCountToZero();
            }

        }

        // If neither left or right key are pressed, reset mario's image
        if (!keyLeftArrow && !keyRightArrow) {

            model.mario.setMarioImageCountToZero();

        }

        if (keyUpArrow || keySpacebar) {

            if (model.mario.getMarioJumpCounter() < 5) {
                model.mario.jump();
            }

        }

    }

    // Mouse Event methods
    public void mousePressed(MouseEvent e) {

        // IF game is in editing mode, then allow creation of bricks
        if (editingMode) {

            // Get x and y coordinates where mouse is pressed
            tempXPos = e.getX();
            tempYPos = e.getY();

        }

    }

    public void mouseReleased(MouseEvent e) {

        // IF game is in editing mode, then allow the creation of bricks
        if (editingMode) {

            // Get x and y coordinates where mouse is released
            int tempFinalXPos = e.getX();
            int tempFinalYPos = e.getY();

            // Calculate width and height
            int tempWidth = tempFinalXPos - tempXPos;
            int tempHeight = tempFinalYPos - tempYPos;

            // Get the absolute value of the width and height
            tempWidth = Math.abs(tempWidth);
            tempHeight = Math.abs(tempHeight);

            // Flip the x and y coordinates if box is drawn instead of top left corner to bottom right corner
            if (tempXPos > tempFinalXPos) {
                tempXPos = tempFinalXPos;
            }

            if (tempYPos > tempFinalYPos) {
                tempYPos = tempFinalYPos;
            }

            // Create new Brick object with proper values
            model.createBrick(tempXPos - model.mario.getMarioLocationOffset() + model.mario.getXPos(), tempYPos, tempWidth, tempHeight);

        } else {

            System.out.println("ERROR: Game is currently in 'play' mode, editing is not allowed");
            System.out.println("Press 'e' to switch modes\n");

        }

    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    // Key Event methods
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            // If left arrow key is pressed, set to true
            case KeyEvent.VK_LEFT:
                keyLeftArrow = true;
                break;

            // If right arrow key is pressed, set to true
            case KeyEvent.VK_RIGHT:
                keyRightArrow = true;
                break;

            // If up arrow key is pressed, set it to true
            case KeyEvent.VK_UP:
                keyUpArrow = true;
                break;

            // If spacebar key is pressed, set it to true
            case KeyEvent.VK_SPACE:
                keySpacebar = true;
                break;

            // Switch between modes 'edit' and 'play' with 'e'
            case KeyEvent.VK_E:
                if (!editingMode) {

                    System.out.println("Switching to edit mode...\n");
                    editingMode = true;

                } else if (editingMode) {

                    System.out.println("Switching to play mode...\n");
                    editingMode = false;

                }

                break;

            // Save map 's'
            case KeyEvent.VK_S:
                System.out.println("Saving map...");
                model.marshal().save("map.json");
                System.out.println("Successfully saved map\n");
                break;

            // Load map 'l'
            case KeyEvent.VK_L:
                System.out.println("Loading map...");
                Json file = Json.load("map.json");
                model.unmarshal(file);
                System.out.println("Successfully loaded map\n");
                break;

            // Clear map 'c'
            case KeyEvent.VK_C:
                if (editingMode) {

                    System.out.println("Clearing bricks from the map...");
                    model.bricks.clear();
                    System.out.println("Successfully cleared bricks\n");

                } else {

                    System.out.println("Clearing can only be done in 'edit' mode");
                    System.out.println("Press 'e' to switch modes\n");

                }


                break;

            // If escape key is pressed, exit the program 'esc'
            case KeyEvent.VK_ESCAPE:
                System.out.println("Exiting program...");
                System.exit(0);
                break;

        }

    }

    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {

            // If left arrow key is released, set to false
            case KeyEvent.VK_LEFT:
                keyLeftArrow = false;
                break;

            // If right arrow is released, set to false
            case KeyEvent.VK_RIGHT:
                keyRightArrow = false;
                break;

            // If up arrow key is pressed, set it to false
            case KeyEvent.VK_UP:
                keyUpArrow = false;
                break;

            // If spacebar key is pressed, set it to false
            case KeyEvent.VK_SPACE:
                keySpacebar = false;
                break;

        }

    }

    public void keyTyped(KeyEvent e) {}

}
