// Benjamin A. Worthington
// October 7th, 2021
// CSCE 3193 - Assignment 4
// Mario.java

public class Mario {

    // Declaring private final member variable
    private final int groundPos = 400;
    private final int marioImageHeight = 95;

    // Declaring private member variables
    private int xPos;
    private int yPos;
    private double verticalVelocity;

    // Default constructor
    Mario() {

        xPos = 0;
        yPos = 0;
        verticalVelocity = 0.0;

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

    void update() {

        verticalVelocity += 1.2;
        yPos += verticalVelocity;

        if (yPos > (groundPos) - marioImageHeight) {

            verticalVelocity = 0.0;
            yPos = groundPos - marioImageHeight;

        }

    }

}
