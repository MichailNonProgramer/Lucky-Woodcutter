import config.GameConfig;
import creatures.persons.player.Player;
import game.Game;
import map.GameMap;
import network.Client;
import network.MultiServer;
import visualizer.Display;

import java.io.IOException;


public class LuckyWoodcutter {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var soloGame = false;
        var serverStart = true;
        Display display;
        Client client;
        MultiServer multiServer;
        if (soloGame)
             display = new Display(GameConfig.getScreenWidth(), GameConfig.getScreenHeight(), new Game(new GameMap(), soloGame, new Player(1 ,2)));
        else {
            client = new Client();
            display = new Display(GameConfig.getScreenWidth(), GameConfig.getScreenHeight(), new Game(client.getGameMap(), soloGame, client.getPlayer()));
        }
    }
}
