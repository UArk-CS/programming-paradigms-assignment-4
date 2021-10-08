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

    // Declaring private member variable for an array of BufferedImages
    // to hold Mario images
    private BufferedImage[] marioImages;

    // View constructor
    View(Controller c, Model m) {

        // Set member variable to object reference
        model = m;

        // Tell the controller what view to use
        c.setView(this);

        // Instantiate marioImages array with a capacity of 5
        marioImages = new BufferedImage[5];

        // Load the mario images into the array
        for (int i = 0; i <= 4; i++) {

            if (marioImages[i] == null) {

                marioImages[i] = model.loadImage("src/main/resources/mario" + (i + 1) + ".png");

            }

        }

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
            g.setColor(slateGray);
            g.fillRect(temp.getxPos() - model.getCameraPos(), temp.getyPos(), temp.getWidth(), temp.getHeight());

        }

        // Draw Mario
        g.drawImage(marioImages[model.getMarioImageCount()], model.mario.getXPos(), model.mario.getYPos(), null);

    }

}
