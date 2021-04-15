package map;

import worldObjects.WorldGameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Cell {
    public static final int cellSize = 50;
    private final Point point;
    private final ArrayList<WorldGameObject> objectInCell = new ArrayList<>();
    private static BufferedImage spriteSheet;

    static {
        try {
            spriteSheet = ImageIO.read(new File("src\\main\\java\\graphics\\image\\ground\\Cell.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }

    public Cell(int x, int y)   {
        this.point = new Point(x, y);
    }

    public Cell(Point point)   {
        this.point = point;
    }

    public ArrayList<WorldGameObject> getObjectsInCell() {
        return this.objectInCell;
    }

    public void addObjectInCell(WorldGameObject worldGameObject) {
        objectInCell.add(worldGameObject);
    }

    public void removeObjectFromCell(WorldGameObject worldGameObject) {
        objectInCell.remove(worldGameObject);
    }

    public int getX() {
        return point.x;
    }

    public int getY() {
        return point.y;
    }

    public Point getPoint() {
        return point;
    }
}
