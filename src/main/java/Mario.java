// Benjamin A. Worthington
// October 7th, 2021
// CSCE 3193 - Assignment 4
// Mario.java

// Import Statements
import java.awt.image.BufferedImage;

public class Mario {

    // Declaring a static BufferedImage array to hold the mario images
    static BufferedImage[] marioImages;
    private int marioImageCount;

    // Declaring private final member variable
    private final int groundPos = 400;

    // Declaring private member variables
    private int xPos;
    private int yPos;
    private int previousXPos;
    private int previousYPos;
    private int marioJumpCounter;
    private int marioLocationOffset;
    private double verticalVelocity;

    // Default constructor
    Mario() {

        xPos = 0;
        yPos = 0;
        marioJumpCounter = 0;
        marioLocationOffset = 100;
        verticalVelocity = 0.0;

        // Loading images into array
        marioImages = new BufferedImage[5];
        marioImageCount = 0;

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

    public void setXPosLeft(int xPos) {
        this.xPos -= xPos;
    }

    public void setXPosRight(int xPos) {
        this.xPos += xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public int getPreviousXPos() {
        return previousXPos;
    }

    public void setPreviousXPos(int previousXPos) {
        this.previousXPos = previousXPos;
    }

    public int getPreviousYPos() {
        return previousYPos;
    }

    public void setPreviousYPos(int previousYPos) {
        this.previousYPos = previousYPos;
    }

    public int getMarioLocationOffset() {
        return marioLocationOffset;
    }

    public void setMarioLocationOffset(int marioLocationOffset) {
        this.marioLocationOffset = marioLocationOffset;
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

    public int getMarioImageCount() {
        return marioImageCount;
    }

    public void setMarioImageCountToZero() {
        this.marioImageCount = 0;
    }

    public void incrementMarioImageCount() {
        this.marioImageCount++;
    }

    public int getMarioJumpCounter() {
        return marioJumpCounter;
    }

    public void setMarioJumpCounter(int marioJumpCounter) {
        this.marioJumpCounter = marioJumpCounter;
    }

    // toString override method
    @Override
    public String toString() {

        return "Mario located at (" + xPos + ", " + yPos + ")\n Width: " + marioImages[0].getWidth() + "\n Height: " + marioImages[0].getHeight();

    }

    // Make Mario jump
    void jump() {

        verticalVelocity = -20;

    }

    // Update method
    void update() {

        final int marioHeight = getMarioImages()[0].getHeight();

        // Simulate physics
        verticalVelocity += 1.2;
        yPos += verticalVelocity;

        // Keep mario on the ground
        if (yPos > (groundPos) - marioHeight) {

            verticalVelocity = 0.0;
            yPos = groundPos - marioHeight;

        }

        // If Mario is on the ground, make the jump counter 0
        if (yPos == (groundPos - marioHeight)) {

            marioJumpCounter = 0;

        } else {

            marioJumpCounter++;

        }


    }

}
