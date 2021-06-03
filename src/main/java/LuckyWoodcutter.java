import config.GameConfig;
import creatures.persons.player.Player;
import game.Game;
import map.GameMap;
import network.Client;
import network.MultiServer;
import visualizer.Display;
import java.io.IOException;


public class LuckyWoodcutter {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Display display;
        Client client;
        MultiServer multiServer;
        if (GameConfig.getIsSoloGame()) {
            var gameMap = new GameMap();
            gameMap.spawnMap();
            display = new Display(GameConfig.getScreenWidth(), GameConfig.getScreenHeight(), new Game(gameMap, GameConfig.getIsSoloGame(), new Player(1, 2)));
        }
        else {
            client = new Client();
            display = new Display(GameConfig.getScreenWidth(), GameConfig.getScreenHeight(), new Game(client.getGameMap(), false, client.getPlayer()));
        }
    }
}
