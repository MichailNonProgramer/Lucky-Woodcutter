package map;

import config.GameConfig;
import utils.Point;
import worldObjects.Ground;
import worldObjects.destructibleObject.Boulder;
import worldObjects.destructibleObject.Tree;

import java.io.Serializable;
import java.util.HashMap;

public class GameMap implements Serializable {
    public HashMap<Point, Cell> getMap() {
        return map;
    }

    private final HashMap<Point, Cell> map;
    private static final long serialVersionUID = 1L;

    public GameMap() {
        map = new HashMap<>();
    }

    public void spawnEmptyMap() {
        for (var x = 0; x < GameConfig.getMapWidth() / Cell.cellSize; x++)
            for (var y = 0; y < GameConfig.getMapHeight() / Cell.cellSize; y++) {
                var newCell = new Cell(x * Cell.cellSize, y * Cell.cellSize);
                var ground = new Ground();
                newCell.addObjectInCell(ground);
                map.put(new Point(x, y), newCell);
            }
        addTrees();
        addBoulders();
    }
    // переписать спавн всего
    private void addBoulders() {
        for (var x = 0; x < GameConfig.getMapWidth() / Cell.cellSize; x++)
            main :for (var y = 0; y < GameConfig.getMapHeight() / Cell.cellSize; y++) {
                var rnd = (int) Math.floor(Math.random() * (20));
                if (rnd == 2) {
                    var boulder = new Boulder(x, y);
                    for (var xui : map.get(boulder.getPoint()).getObjectsInCell()) {
                        if (xui instanceof Tree) {
                            continue main;
                        }
                    }
                    map.get(boulder.getPoint()).addObjectInCell(boulder);
                }
            }
    }

    private void addTrees() {
        for (var x = 0; x < GameConfig.getMapWidth() / Cell.cellSize; x++)
            for (var y = 0; y < GameConfig.getMapHeight() / Cell.cellSize; y++) {
                var rnd = (int) Math.floor(Math.random() * (6));
                if (rnd == 2) {
                    var tree = new Tree(x, y);
                    map.get(tree.getPoint()).addObjectInCell(tree);
                }
            }
    }
}
