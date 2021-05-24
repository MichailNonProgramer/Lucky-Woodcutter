package gameLogic.handlers;

import game.Game;
import network.Client;
import utils.Direction;
import creatures.persons.player.Player;
import map.GameMap;
import worldObjects.Solid;
import worldObjects.destructibleObject.Resources;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class KeyHandler implements KeyListener {
    private final Player player;
    private final Game game;

    public KeyHandler(Player player, Game game) {
        this.game = game;
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
        if (moveEvents.contains(key)) {
            var newDirection = getDirectionByKey(e);
            if (isCanWalkTo(newDirection))
                player.move(newDirection, game);
        }
        if (e.getKeyCode() == KeyEvent.VK_F) {
            if (player.getActiveResource() == Resources.Wood) {
                player.setActiveResource(Resources.Stone);
            } else {
                player.setActiveResource(Resources.Wood);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private Boolean isCanWalkTo(Direction dir) {
        var newPoint = player.getPoint().add(dir.getPoint());
        var newCell = game.getGameMap().getMap().get(newPoint);
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


    private Direction getDirectionByKey(KeyEvent e) {
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