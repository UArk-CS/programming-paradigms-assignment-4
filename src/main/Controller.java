// Benjamin A. Worthington
// September 9th, 2021
// CSCE 3193 Assignment 3 (imported from Assignment 2) - A simple map editor where the user can
// draw bricks and scroll left and right
// Controller.java

// Import statements
import java.awt.event.*;

class Controller implements MouseListener, KeyListener {

    // Declaring member variables for object references
    View view;
    Model model;

    // Declaring private member variables to hold temp xPos and yPos
    private int tempXPos;
    private int tempYPos;
    private boolean keyLeftArrow;
    private boolean keyRightArrow;

    // Controller constructor
    Controller(Model m) {
        model = m;
    }

    // Setter method for view
    void setView(View v) {
        view = v;
    }

    void update() {

        // If left or right key is pressed, move camera
        if (keyLeftArrow) {
            model.setCameraPosLeft(5);
        }

        if (keyRightArrow) {
            model.setCameraPosRight(5);
        }

    }

    // Mouse Event methods
    public void mousePressed(MouseEvent e) {

        // Get x and y coordinates where mouse is pressed
        tempXPos = e.getX();
        tempYPos = e.getY();

    }

    public void mouseReleased(MouseEvent e) {

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
        model.createBrick(tempXPos + model.getCameraPos(), tempYPos, tempWidth, tempHeight);

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

            // Save map
            case KeyEvent.VK_S:
                System.out.println("Saving map...");
                model.marshal().save("map.json");
                break;

            // Load map
            case KeyEvent.VK_L:
                System.out.println("Loading map...");
                Json file = Json.load("map.json");
                model.unmarshal(file);
                break;

            // If escape key is pressed, exit the program
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

        }

    }

    public void keyTyped(KeyEvent e) {}

}
