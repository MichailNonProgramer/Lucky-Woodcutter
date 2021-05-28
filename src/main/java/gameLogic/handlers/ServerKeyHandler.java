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
        KeyHandlerGeneral.handlePress(player, gameMap, key);
    }
}
