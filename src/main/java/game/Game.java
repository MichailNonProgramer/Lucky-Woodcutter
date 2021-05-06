package game;

import creatures.persons.player.Player;
import map.GameMap;

import java.util.ArrayList;


public class Game {
    public ArrayList<Player> players = new ArrayList<>();
    private GameMap gameMap;
    public Player player;

    public Game(GameMap gameMap, boolean soloGame, Player player){
        this.gameMap = gameMap;
        this.player = player;
        players.add(player);
        gameMap.getMap().get(player.getPoint()).addObjectInCell(player);
//        var tree = new Tree(0, 1);
//        gameMap.getMap().get(tree.getPoint()).addObjectInCell(tree);
    }

    public GameMap getGameMap(){
        return gameMap;
    }

    private void gameInit(){
        gameMap.spawnEmptyMap();
        var player = new Player(0, 0);
        players.add(player);
        gameMap.getMap().get(player.getPoint()).addObjectInCell(player);
    }
}
