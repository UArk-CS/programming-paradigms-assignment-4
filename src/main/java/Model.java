// Benjamin A. Worthington
// September 9th, 2021
// CSCE 3193 Assignment 3 (imported from Assignment 2) - A simple map editor where the user can
// draw bricks and scroll left and right
// Model.java

// Import Statements
import java.util.ArrayList;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Model {

    // Declaring member variable for object reference to Mario
    Mario mario;

    // Declare ArrayList of Brick objects
    ArrayList<Brick> bricks;

    // Declare private member variables
    private int cameraPos;
    private int marioImageCount;

    // Default constructor
    Model() {

        mario = new Mario();
        bricks = new ArrayList<>();
        cameraPos = 0;
        marioImageCount = 0;

    }

    // Marshal Model obj
    Json marshal() {

        Json obj = Json.newObject();

        obj.add("cameraPos", cameraPos);

        Json tmplist = Json.newList();
        obj.add("bricks", tmplist);
        for (int i = 0; i < bricks.size(); i++) {
            tmplist.add(bricks.get(i).marshal());
        }

        return obj;

    }

    // Unmarshal Model obj
    void unmarshal(Json obj) {

        bricks = new ArrayList<>();
        Json tmpList = obj.get("bricks");
        for (int i = 0; i < tmpList.size(); i++) {
            bricks.add(new Brick(tmpList.get(i)));
        }

        cameraPos = (int)obj.getLong("cameraPos");

    }

    // Getter and Setter methods
    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }

    public int getCameraPos() {
        return cameraPos;
    }

    public void setCameraPosLeft(int cameraPos) {
        this.cameraPos -= cameraPos;
    }

    public void setCameraPosRight(int cameraPos) {
        this.cameraPos += cameraPos;
    }

    public int getMarioImageCount() {
        return marioImageCount;
    }

    public void setMarioImageCount(int marioImageCount) {
        this.marioImageCount = marioImageCount;
    }

    // Create new Brick object and add it to Brick ArrayList
    public void createBrick(int x, int y, int w, int h) {

        Brick brick = new Brick(x, y, w, h);
        bricks.add(brick);

    }

    // Load Mario images
    public BufferedImage loadImage(String imgFile) {

        BufferedImage tempImgFile = null;

        try {

            tempImgFile = ImageIO.read(new File(imgFile));
            System.out.println(imgFile + " has been successfully loaded");

        } catch (Exception e) {

            System.out.println("ERROR: Images were not loaded properly");
            e.printStackTrace();
            System.exit(1);

        }

        return tempImgFile;

    }

    // Update method
    public void update() {}

}
