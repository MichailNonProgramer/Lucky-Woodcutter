package gameLogic.growing;

import creatures.persons.player.IPlayer;
import map.Cell;
import map.GameMap;
import utils.Point;
import utils.RandomInt;
import worldObjects.Solid;
import worldObjects.destructibleObjects.LiveTree;

public class TreesGrowHandler {
    private GameMap gameMap;

    public TreesGrowHandler(GameMap map) {
        this.gameMap = map;
    }

    public void grow() {
        main:
        for (var cell : gameMap.getMap().values()) {
            var cellPoint = new Point(cell.getX() / Cell.cellSize, cell.getY() / Cell.cellSize);
            for (var ob : cell.getObjectsInCell()) {
                if (ob instanceof Solid || ob instanceof IPlayer)
                    continue main;
            }

            if (RandomInt.getValue(200, 0) == 1) {
                System.out.println("ЗАСПАВНИЛОСЬ ДЕРЕВО " + cellPoint.x + " " + cellPoint.y);
                cell.addObjectInCell(new LiveTree(cellPoint));
            }
        }
    }
}

