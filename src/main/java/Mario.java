// Benjamin A. Worthington
// October 7th, 2021
// CSCE 3193 - Assignment 4
// Mario.java

// Import Statements
import java.awt.image.BufferedImage;

public class Mario {

    // Declaring a static BufferedImage array to hold the mario images
    static BufferedImage[] marioImages;

    // Declaring private final member variable
    private final int groundPos = 400;
    private final int marioImageHeight = 95;

    // Declaring private member variables
    private int xPos;
    private int yPos;
    private int marioJumpCounter;
    private double verticalVelocity;

    // Default constructor
    Mario() {

        xPos = 0;
        yPos = 0;
        marioJumpCounter = 0;
        verticalVelocity = 0.0;

        marioImages = new BufferedImage[5];

        for (int i = 0; i <= 4; i++) {

            if (marioImages[i] == null) {

                marioImages[i] = Model.loadImage("src/main/resources/mario" + (i + 1) + ".png");

            }

        }

    }

    // Getter and Setter methods
    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public double getVerticalVelocity() {
        return verticalVelocity;
    }

    public void setVerticalVelocity(double verticalVelocity) {
        this.verticalVelocity = verticalVelocity;
    }

    public BufferedImage[] getMarioImages() {
        return marioImages;
    }

    public int getMarioJumpCounter() {
        return marioJumpCounter;
    }

    void jump() {

        verticalVelocity -= 6;

    }

    void update() {

        verticalVelocity += 1.2;
        yPos += verticalVelocity;

        if (yPos > (groundPos) - marioImageHeight) {

            verticalVelocity = 0.0;
            yPos = groundPos - marioImageHeight;

        }

        if (yPos == (groundPos - marioImageHeight)) {

            marioJumpCounter = 0;

        } else {

            marioJumpCounter++;

        }

        System.out.println("Jump Count: " + marioJumpCounter + " Mario Y: " + yPos + " Ground: " + groundPos);

    }

}
