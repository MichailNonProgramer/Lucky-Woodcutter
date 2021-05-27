package gameLogic.handlers;
import creatures.persons.player.Player;
import map.GameMap;
import utils.Direction;
import worldObjects.Solid;

import java.awt.event.KeyEvent;

public class KeyHandlerGeneral {
    public static Boolean isCanWalkTo(Player player, GameMap gameMap, Direction dir) {
        var newPoint = player.getPoint().add(dir.getPoint());
        var newCell = gameMap.getMap().get(newPoint);
        if (newCell == null)
            return false;
        else {
            for (var ob : newCell.getObjectsInCell()) {
                if (ob instanceof Solid)
                    return false;
            }
            return true;
        }
    }


    public static Direction getDirectionByKey(int key) {
        switch (key) {
            case KeyEvent.VK_D:
                return Direction.RIGHT;
            case KeyEvent.VK_A:
                return Direction.LEFT;
            case KeyEvent.VK_W:
                return Direction.UP;
            case KeyEvent.VK_S:
                return Direction.DOWN;
            default:
                return Direction.DEFAULT;
        }
    }
}
