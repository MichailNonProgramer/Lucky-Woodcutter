package map;

import java.util.ArrayList;

public class Map {
    private final int width = 8000;
    private final int height = 8000;
    private final ArrayList<Cell> map;

    public Map() {
        this.map = spawnEmptyMap();
    }

    public ArrayList<Cell> spawnEmptyMap() {
        var mapList = new ArrayList<Cell>();
        for (var x = 0; x < width / Cell.cellSize; x++)
            for (var y = 0; y < height / Cell.cellSize; y++) {
                mapList.add(new Cell(x, y));
            }
        return mapList;
    }
}
