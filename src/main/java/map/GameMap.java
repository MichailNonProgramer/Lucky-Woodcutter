package map;

import config.Config;
import worldObjects.Ground;

import java.util.HashMap;

public class GameMap {
    public static HashMap<Point, Cell> getMap() {
        return map;
    }

    private static final HashMap<Point, Cell> map = spawnEmptyMap();

    public static HashMap<Point, Cell> spawnEmptyMap() {
        var map = new HashMap<Point, Cell>();
        for (var x = 0; x < Config.getMapWidth() / Cell.cellSize; x++)
            for (var y = 0; y < Config.getMapHeight() / Cell.cellSize; y++) {
                var newCell =  new Cell(x * Cell.cellSize, y * Cell.cellSize);
                var ground = new Ground();
                newCell.addObjectInCell(ground);
                map.put(new Point(x, y), newCell);
            }
        return map;
    }
}
