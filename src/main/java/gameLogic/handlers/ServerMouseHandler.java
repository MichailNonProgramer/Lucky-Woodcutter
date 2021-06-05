package gameLogic.handlers;

import creatures.persons.player.Player;
import map.GameMap;
import utils.Point;

public class ServerMouseHandler {
    private final Player player;
    private final GameMap gameMap;

    public ServerMouseHandler(Player player, GameMap gameMap) {
        this.gameMap = gameMap;
        this.player = player;
    }

    public void mousePressedLeft(Point mousePoint) {
        MouseHandlerGeneral.handlePressed(player, gameMap, mousePoint, true, false);
    }

    public void mousePressedRight(Point mousePoint) {
        MouseHandlerGeneral.handlePressed(player, gameMap, mousePoint, false, true);
    }

    public void mouseMoved(Point mousePoint) {
        try {
            MouseHandlerGeneral.handleMoved(player, gameMap, mousePoint);
        } catch (NullPointerException ignored) {
        }
    }
}
