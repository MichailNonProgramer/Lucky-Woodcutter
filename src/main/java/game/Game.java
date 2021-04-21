package game;

import creatures.persons.player.Player;
import map.GameMap;
import map.Point;
import worldObjects.destructibleObject.Tree;

public class Game {
    public Player player;

    public Game(){
        player = new Player(0, 0);
        GameMap.getMap().get(player.getPoint()).addObjectInCell(player);
        var tree = new Tree(new Point(0 , 1));
        GameMap.getMap().get(tree.getPoint()).addObjectInCell(tree);
    }
}
