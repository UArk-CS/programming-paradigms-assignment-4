// Benjamin A. Worthington
// September 9th, 2021
// CSCE 3193 Assignment 3 (imported from Assignment 2) - A simple map editor where the user can
// draw bricks and scroll left and right
// Model.java

// Import Statements
import java.util.ArrayList;

public class Model {

    // Declare ArrayList of Brick objects
    ArrayList<Brick> bricks;

    // Declare private member variables
    private int cameraPos;

    // Default constructor
    Model() {
        bricks = new ArrayList<>();
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

    // Create new Brick object and add it to Brick ArrayList
    public void createBrick(int x, int y, int w, int h) {

        Brick brick = new Brick(x, y, w, h);
        bricks.add(brick);

    }

    // Update method
    public void update() {}

}

