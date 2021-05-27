package network;

import creatures.persons.player.Player;
import map.Cell;
import map.GameMap;
import utils.Point;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class MultiServer {
    public static GameMap gameMap = new GameMap();
    public static LinkedList<Server> servers = new LinkedList<>();
    public static HashMap<Point, Cell> changesMap;
    public static HashMap<String, Player> players = new HashMap<>();

    public static void main(String[] args) throws IOException {
        gameMap.spawnEmptyMap();
        ServerSocket s = new ServerSocket(Common.PORT);
        System.out.println("Server Started");
        try (s){
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

    public static synchronized void updateChangesCell(){
        changesMap = new HashMap<>();
    }

    public static synchronized void setGameMap(GameMap gameMap){
        for (var cell : gameMap.getMap().values())
            for (var obj : cell.getObjectsInCell())
                if (obj instanceof Player)
                    System.out.println(obj.toString() + " " + cell.getPoint().toString());
        MultiServer.gameMap = gameMap;
    }

    public static synchronized void send(GameMap gameMap, ObjectOutputStream out) throws IOException {
        try {
            MultiServer.setGameMap(gameMap);
            out.writeObject(changesMap);
            out.flush();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
