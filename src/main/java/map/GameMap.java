package map;

import config.GameConfig;
import utils.Point;
import worldObjects.Ground;
import worldObjects.destructibleObject.Tree;

import java.io.Serializable;
import java.util.HashMap;

public class GameMap implements Serializable {
    public HashMap<Point, Cell> getMap() {
        return map;
    }

    private final HashMap<Point, Cell> map;

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
