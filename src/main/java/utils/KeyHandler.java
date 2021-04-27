package utils;

import creatures.Direction;
import creatures.persons.player.Player;
import map.GameMap;
import worldObjects.Solid;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class KeyHandler implements KeyListener {
    private final Player player;
    private final GameMap gameMap;

    public KeyHandler(Player player, GameMap gameMap) {
        this.gameMap = gameMap;
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        var moveEvents = Arrays.asList(KeyEvent.VK_A, KeyEvent.VK_D,
                KeyEvent.VK_W, KeyEvent.VK_S);
        var key = e.getKeyCode();
        var oldDirection = player.getDirection();
        if (moveEvents.contains(key)) {
            var newDirection = getDirection(e);
            player.setDirection(newDirection);
            player.changeSprite();
            player.getHandsArea().updateBounds(player.getDirection());
            player.getHandsArea().updateActiveCords();
            if (oldDirection == newDirection)
                if (isCanWalkTo(newDirection))
                    player.move(newDirection, gameMap);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public Boolean isCanWalkTo(Direction dir) {
        var newPoint = player.getPoint().add(dir.getPoint());
        var newCell = gameMap.getMap().get(newPoint);
        if (newCell == null)
            return false;
        else {
            for (var ob : newCell.getObjectsInCell()) {
                if (ob instanceof Solid)
                    return false;
            }
            return  true;
        }
    }


    public Direction getDirection(KeyEvent e) {
        var key = e.getKeyCode();
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