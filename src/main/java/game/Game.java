package game;

import config.GameConfig;
import creatures.persons.player.Player;
import gameLogic.growing.TreesGrowTimer;
import gameLogic.infection.InfectionTimer;
import map.Cell;
import map.GameMap;

import java.util.ArrayList;


public class Game {
    public ArrayList<Player> players = new ArrayList<>();
    private GameMap gameMap;
    private  Player player;
    final boolean soloGame;

    public Game(GameMap gameMap, boolean soloGame){
        this.gameMap = gameMap;
        this.player = player;
        this.soloGame = soloGame;
        players.add(player);
        if (soloGame)
            gameMap.getMap().get(player.getPoint()).addObjectInCell(player);
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public synchronized Player getPlayer() {return player;}

    public boolean isSoloGame(){return this.soloGame;}

    private void gameInit() {
        gameMap.spawnEmptyMap();
        player = new Player(
                GameConfig.getMapWidth() / 2 / Cell.cellSize,
                GameConfig.getMapWidth() / 2 / Cell.cellSize);
        players.add(player);
        gameMap.getMap().get(player.getPoint()).addObjectInCell(player);
        InfectionTimer.start(gameMap);
        TreesGrowTimer.start(gameMap);
    }

    public synchronized void setPlayer(Player player){
        this.player = player;
    }
    public synchronized void setGameMap(GameMap gameMap){
        this.gameMap = gameMap;
    }
}
