package game;

import creatures.persons.player.Player;
import map.GameMap;
import map.Point;
import worldObjects.destructibleObject.Tree;


public class Game {
    public Player player;
    private final GameMap gameMap = new GameMap();

    public Game(){
        gameInit();
//        var tree = new Tree(0, 1);
//        gameMap.getMap().get(tree.getPoint()).addObjectInCell(tree);
    }

    public GameMap getGameMap(){
        return gameMap;
    }

    private void gameInit(){
        gameMap.spawnEmptyMap();
        player = new Player(0, 0);
        gameMap.getMap().get(player.getPoint()).addObjectInCell(player);
    }
}
