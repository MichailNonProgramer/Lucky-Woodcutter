package game;

import config.GameConfig;
import creatures.persons.player.Player;
import gameLogic.growing.TreesGrowTimer;
import gameLogic.infection.InfectionTimer;
import map.Cell;
import map.GameMap;


public class Game {
    public Player player;
    private final GameMap gameMap = new GameMap();

    public Game() {
        gameInit();
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    private void gameInit() {
        gameMap.spawnEmptyMap();
        player = new Player(
                GameConfig.getMapWidth() / 2 / Cell.cellSize,
                GameConfig.getMapWidth() / 2 / Cell.cellSize);
        gameMap.getMap().get(player.getPoint()).addObjectInCell(player);
        InfectionTimer.start(gameMap);
        TreesGrowTimer.start(gameMap);
    }
}
