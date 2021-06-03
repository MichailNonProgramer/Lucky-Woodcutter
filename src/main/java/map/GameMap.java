package map;

import config.GameConfig;
import utils.Point;
import worldObjects.Ground;
import worldObjects.destructibleObjects.Boulder;
import worldObjects.destructibleObjects.InfectedTree;
import worldObjects.destructibleObjects.LiveTree;

import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.SynchronousQueue;

public class GameMap implements Serializable {
    public HashMap<Point, Cell> getMap() {
        return map;
    }

    private final HashMap<Point, Cell> map;
    private static final long serialVersionUID = 1L;

    public GameMap() {
        map = new HashMap<>();
    }

    public void spawnMap() {
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

    private void addBoulders() {
        for (var x = 0; x < GameConfig.getMapWidth() / Cell.cellSize; x++)
            main :for (var y = 0; y < GameConfig.getMapHeight() / Cell.cellSize; y++) {
                var rnd = (int) Math.floor(Math.random() * (50));
                if (rnd == 2) {
                    var boulder = new Boulder(x, y);
                    for (var ob : map.get(boulder.getPoint()).getObjectsInCell()) {
                        if (ob instanceof LiveTree || ob instanceof InfectedTree) {
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
                var rnd = (int) Math.floor(Math.random() * (4));
                var rndDead = (int) Math.floor(Math.random() * (40));
                if (rnd == 2) {
                    var tree = new LiveTree(x, y);
                    map.get(tree.getPoint()).addObjectInCell(tree);
                }
                else if (rndDead == 3) {
                    var deadTree = new InfectedTree(x, y);
                    map.get(deadTree.getPoint()).addObjectInCell(deadTree);
                }
            }
    }
}
