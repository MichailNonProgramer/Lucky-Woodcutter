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
    private final GameMap gameMap;

    public ClientKeyHandler(Player player, Game game) {
        this.game = game;
        this.player = player;
        this.gameMap = game.getGameMap();
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
    public void keyTyped(KeyEvent e) {
        // nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}