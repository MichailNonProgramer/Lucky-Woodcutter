package map;

import creatures.persons.player.Player;
import utils.Point;
import worldObjects.Ground;
import worldObjects.WorldGameObject;
import worldObjects.destructibleObject.DestructibleObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;


public class Cell implements Serializable {
    public static final int cellSize = 70;
    private final Point point;
    private final ArrayList<WorldGameObject> objectsInCell = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    public Cell(int x, int y) {
        this(new Point(x, y));
    }

    public Cell(Point point) {
        this.point = point;
    }

    // конструктор копии
    public Cell(Cell other) {
        this(other.getPoint());
        for (var ob : other.getObjectsInCell()) {
            this.addObjectInCell(ob);
        }
    }

    public ArrayList<WorldGameObject> getObjectsInCell() {
        return this.objectsInCell;
    }

    public synchronized void addObjectInCell(WorldGameObject worldGameObject) {
        objectsInCell.add(worldGameObject);
    }

    public synchronized void removeObjectFromCell(WorldGameObject worldGameObject) {
        objectsInCell.remove(worldGameObject);
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

    @Override
    public boolean equals(Object obj) {
        try {
            //System.out.println(this.point.equals(((Cell) obj).point) && eqObjectInCell((Cell) obj));
            return this.point.equals(((Cell) obj).point) && eqObjectInCell((Cell) obj);
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.point.x;
        hash = 71 * hash + this.point.y;
        return hash;
    }

    private boolean eqObjectInCell(Cell cell) {
        var co = false;
        for (var object1 : cell.getObjectsInCell()) {
            for (var object2 : this.objectsInCell) {
                if (object1 instanceof DestructibleObject && object2 instanceof DestructibleObject) {
                    var destructibleObject1 = (DestructibleObject) object1;
                    var destructibleObject2 = (DestructibleObject) object2;
                    if (destructibleObject1.equals(destructibleObject2)) {
                        co = true;
                        break;
                    }
                }
                if (object1 instanceof Ground && object2 instanceof Ground) {
                    var ground1 = (Ground) object1;
                    var ground2 = (Ground) object2;
                    if (ground1.equals(ground2)) {
                        co = true;
                        break;
                    }
                }
                if (object1 instanceof Player && object2 instanceof Player) {
                    var player1 = (Player) object1;
                    var player2 = (Player) object2;
                    if (player1.equals(player2)) {
                        co = true;
                       // System.out.println(player1.toString() + " " + player2.toString() + " " + 1);
                        break;
                    }
                }
            }
            if (!co) {
                return false;
            }
            co = false;
        }
        for (var object1 : this.objectsInCell) {
            for (var object2 : cell.getObjectsInCell()) {
                if (object1 instanceof DestructibleObject && object2 instanceof DestructibleObject) {
                    var destructibleObject1 = (DestructibleObject) object1;
                    var destructibleObject2 = (DestructibleObject) object2;
                    if (destructibleObject1.equals(destructibleObject2)) {
                        co = true;
                        break;
                    }
                }
                if (object1 instanceof Ground && object2 instanceof Ground) {
                    var ground1 = (Ground) object1;
                    var ground2 = (Ground) object2;
                    if (ground1.equals(ground2)) {
                        co = true;
                        break;
                    }
                }
                if (object1 instanceof Player && object2 instanceof Player) {
                    var player1 = (Player) object1;
                    var player2 = (Player) object2;
                    if (player1.equals(player2)) {
                       // System.out.println(player1.toString() + " " + player2.toString() + " " + 2);
                        co = true;
                        break;
                    }
                }
            }
            if (!co) {
                return false;
            }
            co = false;
        }
        return true;
    }
}
