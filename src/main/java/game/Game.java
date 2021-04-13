package game;

import creatures.persons.player.Player;
import map.Cell;
import map.GameMap;
import map.Point;

public class Game {
    public Player player;

    public Game(){
        player = new Player(0, 0);
        GameMap.getMap().get(player.getPoint()).addObjectInCell(player);
    }
}
