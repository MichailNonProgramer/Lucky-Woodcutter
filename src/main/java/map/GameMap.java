package map;

import java.util.ArrayList;

public class GameMap {
    private static final int width = 2000;
    private static final int height = 2000;

    public static ArrayList<Cell> getMapList() {
        return mapList;
    }

    private static ArrayList<Cell> mapList = spawnEmptyMap();

    public static Cell getCell(int x, int y) {
        return (Cell) mapList.stream().filter((cell) -> cell.getX() == x && cell.getY() == y);
    }


    public static ArrayList<Cell> spawnEmptyMap() {
        var mapList = new ArrayList<Cell>();
        for (var x = 0; x < width / Cell.cellSize; x++)
            for (var y = 0; y < height / Cell.cellSize; y++) {
                mapList.add(new Cell(x, y));
            }
        System.out.println(mapList.size());
        return mapList;
    }
}
