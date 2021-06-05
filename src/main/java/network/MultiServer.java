package network;

import creatures.persons.player.Player;
import gameLogic.growing.TreesGrowTimer;
import gameLogic.infection.InfectionTimer;
import map.GameMap;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class MultiServer {
    public static GameMap gameMap = new GameMap();
    public static LinkedList<Server> servers = new LinkedList<>();
    public static HashMap<String, Player> players = new HashMap<>();

    public static void main(String[] args) throws IOException {
        gameMap.spawnMap();
        InfectionTimer.start(gameMap);
        TreesGrowTimer.start(gameMap);
        ServerSocket s = new ServerSocket(Common.PORT);
        System.out.println("Server Started");
        try (s) {
            while (true) {
                // Блокируется до возникновения нового соединения:
                Socket socket = s.accept();
                try {
                    servers.add(new Server(socket));
                } catch (IOException | ClassNotFoundException e) {
                    // Если завершится неудачей, закрывается сокет,
                    // в противном случае, нить закроет его:
                    socket.close();
                }
            }
        }
    }

    public static synchronized void setGameMap(GameMap gameMap) {
        MultiServer.gameMap = gameMap;
    }
}
