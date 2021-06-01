package gameLogic.infection;

import gameLogic.area.InfectionArea;
import map.Cell;
import map.GameMap;
import utils.Point;
import utils.RandomInt;
import worldObjects.destructibleObjects.InfectedTree;
import worldObjects.destructibleObjects.LiveTree;

public class InfectMap {
    private GameMap gameMap;
    private InfectionArea infectionArea;

    public InfectMap(GameMap map) {
        this.gameMap = map;
    }


    public void infect() {
        for (var cell : gameMap.getMap().values()) {
            var cellPoint = new Point(cell.getX() / Cell.cellSize, cell.getY() / Cell.cellSize);
            for (var worldObj : cell.getObjectsInCell()) {
                if (worldObj instanceof InfectedTree) {
                    this.infectionArea = new InfectionArea(cellPoint);
                    var targetPoint = infectionArea.getActiveCords().get(
                            RandomInt.getValue(infectionArea.getActiveCords().size() - 1, 0));
                    var targetCell = gameMap.getMap().get(targetPoint);
                    for (var worldObj1 : targetCell.getObjectsInCell()) {
                        if (worldObj1 instanceof LiveTree) {
                            //System.out.println("ЗАРАЖЕНИЕ по " + targetPoint.x + " " + targetPoint.y);
                            targetCell.removeObjectFromCell(worldObj1);
                            targetCell.addObjectInCell(new InfectedTree(cellPoint));
                        }
                    }
                }
            }
        }
    }
}

