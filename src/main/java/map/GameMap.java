package map;

import java.util.HashMap;

public class GameMap {
    private static final int width = 1400;
    private static final int height = 800;

    public static HashMap<Point, Cell> getMap() {
        return map;
    }

    private static final HashMap<Point, Cell> map = spawnEmptyMap();

    public static HashMap<Point, Cell> spawnEmptyMap() {
        var map = new HashMap<Point, Cell>();
        for (var x = 0; x < width / Cell.cellSize; x++)
            for (var y = 0; y < height / Cell.cellSize; y++) {
                map.put(new Point(x, y), new Cell(x * Cell.cellSize, y * Cell.cellSize));
            }
        return map;
    }
}
