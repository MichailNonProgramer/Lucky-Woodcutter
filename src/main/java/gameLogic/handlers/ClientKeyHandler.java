package gameLogic.handlers;

import game.Game;
import network.Client;
import network.Lock;
import network.Sender;
import utils.Direction;
import creatures.persons.player.Player;
import map.GameMap;
import worldObjects.Solid;
import worldObjects.destructibleObject.Resources;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Arrays;

public class ClientKeyHandler implements KeyListener {
    private final Player player;
    private final Game game;

    public ClientKeyHandler(Player player, Game game) {
        this.game = game;
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (game.isSoloGame()) {
            var moveEvents = Arrays.asList(KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D);
            var key = e.getKeyCode();
            if (moveEvents.contains(key)) {
                var newDirection = KeyHandlerGeneral.getDirectionByKey(key);
                if (KeyHandlerGeneral.isCanWalkTo(player, gameMap, newDirection))
                    player.move(newDirection, gameMap);
            }
            if (key == KeyEvent.VK_F) {
                if (player.getActiveResource() == Resources.Wood) {
                    player.setActiveResource(Resources.Stone);
                } else {
                    player.setActiveResource(Resources.Wood);
                }
            }
        } else {
            try {
                Lock.isLockClient = true;
                Client.start(new Sender("KeyPressed", e.getKeyCode(), null, player.getId()));
                game.setGameMap(Client.getGameMap());
                game.setPlayer(Client.getPlayer());
                Lock.isLockClient = false;

            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
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