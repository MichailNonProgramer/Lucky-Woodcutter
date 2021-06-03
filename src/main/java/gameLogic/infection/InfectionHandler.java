package gameLogic.infection;

import gameLogic.area.InfectionArea;
import map.Cell;
import map.GameMap;
import utils.Point;
import utils.RandomInt;
import worldObjects.destructibleObjects.DeadTree;
import worldObjects.destructibleObjects.InfectedTree;
import worldObjects.destructibleObjects.LiveTree;

public class InfectionHandler {
    private final GameMap gameMap;

    public InfectionHandler(GameMap map) {
        this.gameMap = map;
    }

    public void infect() {
        for (var cell : gameMap.getMap().values()) {
            var cellPoint = new Point(cell.getX() / Cell.cellSize, cell.getY() / Cell.cellSize);
            for (var worldObj : cell.getObjectsInCell()) {
                // Заражается случайное здоровое дерево
                if (worldObj instanceof LiveTree) {
                    if (RandomInt.getValue(470, 0) == 2) {
                        System.out.println("СЛУЧАЙНОЕ ДЕРЕВО ЗАРАЗИЛОСЬ НА " + cellPoint.x + " " + cellPoint.y);
                        cell.removeObjectFromCell(worldObj);
                        cell.addObjectInCell(new InfectedTree(cellPoint));
                    }
                    continue;
                }
                // Взаимодействуем только с заражёнными деревьями
                if (!(worldObj instanceof InfectedTree))
                    continue;
                var infectionArea = new InfectionArea(cellPoint);
                // Умирает заражённое дерево, не имеющее здоровых соседей
                var isHermit = true;
                for (var infectPoint : infectionArea.getActiveCords()) {
                    var maybeCell = gameMap.getMap().get(infectPoint);
                    for (var ob : maybeCell.getObjectsInCell()) {
                        if (!(ob instanceof LiveTree))
                            continue;
                        isHermit = false;
                    }
                }
                if (isHermit) {
                    // System.out.println("СДОХ ОДИНОЧКА на " + cellPoint.x + " " + cellPoint.y);
                    cell.removeObjectFromCell(worldObj);
                    cell.addObjectInCell(new DeadTree(cellPoint));
                    continue;
                }
                // Пытаемся заразить рандомную клетку из соседей cell
                var targetPoint = infectionArea.getActiveCords().get(
                        RandomInt.getValue(infectionArea.getActiveCords().size() - 1, 0));
                var targetCell = gameMap.getMap().get(targetPoint);
                for (var worldObj1 : targetCell.getObjectsInCell()) {
                    if (worldObj1 instanceof LiveTree) {
                        System.out.println("ЗАРАЖЕНИЕ по " + targetPoint.x + " " + targetPoint.y);
                        targetCell.removeObjectFromCell(worldObj1);
                        targetCell.addObjectInCell(new InfectedTree(targetPoint));
                    }
                }
            }
        }
    }
}

