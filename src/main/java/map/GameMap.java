package map;

import java.util.ArrayList;

public class GameMap {
    public static ArrayList<Cell> spawnEmptyMap() {
        var mapList = new ArrayList<Cell>();
        int width = 8000;
        int height = 8000;
        for (var x = 0; x < width / Cell.cellSize; x++)
            for (var y = 0; y < height / Cell.cellSize; y++) {
                mapList.add(new Cell(x, y));
            }
        return mapList;
    }
}
