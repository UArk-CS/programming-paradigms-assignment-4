// Benjamin A. Worthington
// September 9th, 2021
// CSCE 3193 Assignment 3 (imported from Assignment 2) - A simple map editor where the user can
// draw bricks and scroll left and right
// View.java

// Import statements
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;

class View extends JPanel {

    // Declaring static BufferedImage to hold background image
    static BufferedImage backgroundImage;

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

        backgroundImage = Model.loadImage("src/main/resources/background.png");

    }

    // View.repaint() (update method)
    public void paintComponent(Graphics g) {

        // Draw background image, move it as mario moves at a fraction of the rate
        g.drawImage(backgroundImage, 0 - (model.mario.getMarioLocationOffset() / 10), 0, this.getWidth() + 100, this.getHeight(), null);

        Iterator<Brick> iterator = model.getBricks().iterator();

        while(iterator.hasNext()) {

            Brick temp = iterator.next();
            g.drawImage(temp.getBrickImage(), temp.getxPos() - model.mario.getXPos() + model.mario.getMarioLocationOffset(), temp.getyPos(), temp.getWidth(), temp.getHeight(), null);

        }

        // Draw Mario
        g.drawImage(model.mario.getMarioImages()[model.mario.getMarioImageCount()], model.mario.getXPos() - model.mario.getXPos() + model.mario.getMarioLocationOffset(), model.mario.getYPos(), null);

    }

}
