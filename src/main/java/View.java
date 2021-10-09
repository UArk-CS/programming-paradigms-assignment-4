// Benjamin A. Worthington
// September 9th, 2021
// CSCE 3193 Assignment 3 (imported from Assignment 2) - A simple map editor where the user can
// draw bricks and scroll left and right
// View.java

// Import statements
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class View extends JPanel {

    // Declaring final (un-modifiable) member variables holding Color objects
    private final Color slateGray = new Color(112, 128, 144);
    private final Color skyBlue = new Color(135, 206, 235);
    private final Color dirtBrown = new Color(155, 118, 83);
    private final Color grassGreen = new Color(0, 154, 23);

    // Declaring member variable for object reference to Model
    Model model;

    // View constructor
    View(Controller c, Model m) {

        // Set member variable to object reference
        model = m;

        // Tell the controller what view to use
        c.setView(this);

    }

    // View.repaint() (update method)
    public void paintComponent(Graphics g) {

        // Set background color
        g.setColor(skyBlue);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Draw dirt
        g.setColor(dirtBrown);
        g.fillRect(0, this.getHeight() - 70, this.getWidth(), 70);

        // Draw grass
        g.setColor(grassGreen);
        g.fillRect(0, this.getHeight() - 100, this.getWidth(), 30);

        // Draw all bricks in the array to the screen
        for (int i = 0; i < model.getBricks().size(); i++) {

            // Get brick, set color, and paint to the screen
            Brick temp = model.getBricks().get(i);
            g.drawImage(temp.getBrickImage(), temp.getxPos() - model.getCameraPos(), temp.getyPos(), temp.getWidth(), temp.getHeight(), null);

        }

        // Draw Mario
        g.drawImage(model.mario.getMarioImages()[model.getMarioImageCount()], model.mario.getXPos(), model.mario.getYPos(), null);

    }

}
