package gameLogic.handlers;

import creatures.persons.player.Player;
import game.Game;
import map.GameMap;
import utils.Direction;
import worldObjects.Solid;
import worldObjects.destructibleObject.Resources;

import java.awt.event.KeyEvent;
import java.util.Arrays;

public class ServerKeyHandler {
    private final Player player;
    private final GameMap gameMap;

    public ServerKeyHandler(Player player, GameMap gameMap) {
        this.gameMap = gameMap;
        this.player = player;
    }

    public void keyPressed(int key) {
        var moveEvents = Arrays.asList(KeyEvent.VK_A, KeyEvent.VK_D,
                KeyEvent.VK_W, KeyEvent.VK_S);
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
    }
}
