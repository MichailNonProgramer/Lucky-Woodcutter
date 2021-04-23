package map;

import config.Config;
import worldObjects.Ground;
import worldObjects.destructibleObject.Tree;

import java.util.HashMap;

public class GameMap {
    public  HashMap<Point, Cell> getMap() {
        return map;
    }

    private final HashMap<Point, Cell> map;

    public GameMap(){
        map = new HashMap<>();
    }

    public  HashMap<Point, Cell> spawnEmptyMap() {
        for (var x = 0; x < Config.getMapWidth() / Cell.cellSize; x++)
            for (var y = 0; y < Config.getMapHeight() / Cell.cellSize; y++) {
                var newCell =  new Cell(x * Cell.cellSize, y * Cell.cellSize);
                var ground = new Ground();
                newCell.addObjectInCell(ground);
                map.put(new Point(x, y), newCell);
            }
        addTrees();
        return map;
    }

    private void addTrees(){
        for (var x = 0; x < Config.getMapWidth() / Cell.cellSize; x++)
            for(var y = 0; y < Config.getMapHeight() / Cell.cellSize; y++){
                var rnd = (int)Math.floor(Math.random()*(3));
                if (rnd == 2){
                    var tree = new Tree(new Point(x, y));
                    map.get(tree.getPoint()).addObjectInCell(tree);
                }
            }
    }
}
