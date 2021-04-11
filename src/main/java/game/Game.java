package game;

import creatures.persons.player.Player;
import map.Cell;
import map.GameMap;

public class Game {

    public Game(){
        Player player = new Player(10, 10);
        GameMap.getCell(10, 10).addObjectInCell(player);
    }
}
