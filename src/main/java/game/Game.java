package game;

import creatures.persons.player.Player;
import map.GameMap;

import java.util.ArrayList;


public class Game {
    public ArrayList<Player> players = new ArrayList<>();
    private GameMap gameMap;
    private  Player player;
    final boolean soloGame;

    public Game(GameMap gameMap, boolean soloGame, Player player){
        this.gameMap = gameMap;
        this.player = player;
        this.soloGame = soloGame;
        players.add(player);
        if (soloGame)
            gameMap.getMap().get(player.getPoint()).addObjectInCell(player);
//        var tree = new Tree(0, 1);
//        gameMap.getMap().get(tree.getPoint()).addObjectInCell(tree);
    }

    public GameMap getGameMap(){
        return gameMap;
    }

    public synchronized Player getPlayer() {return player;}

    public boolean isSoloGame(){return this.soloGame;}

    private void gameInit(){
        gameMap.spawnEmptyMap();
        var player = new Player(0, 0);
        players.add(player);
        gameMap.getMap().get(player.getPoint()).addObjectInCell(player);
    }

    public synchronized void setPlayer(Player player){
        this.player = player;
    }
    public synchronized void setGameMap(GameMap gameMap){
        this.gameMap = gameMap;
    }
}
