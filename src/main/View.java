// Benjamin A. Worthington
// September 9th, 2021
// CSCE 3193 Assignment 3 (imported from Assignment 2) - A simple map editor where the user can
// draw bricks and scroll left and right
// View.java

// Import statements
import javax.swing.*;
import java.awt.*;

class View extends JPanel {

    // Declaring member variables
    Model model;
    Color brickRed = new Color(203, 65, 84);

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
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (int i = 0; i < model.getBricks().size(); i++) {

            // Get brick, set color, and paint to the screen
            Brick temp = model.getBricks().get(i);
            g.setColor(brickRed);
            g.fillRect(temp.getxPos() - model.getCameraPos(), temp.getyPos(), temp.getWidth(), temp.getHeight());

        }

    }

}
