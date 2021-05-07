package network;

import game.Game;
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
    public static LinkedList<Server> players = new LinkedList<>();
    public static HashMap<Point, Cell> changesMap;

//   // public MultiServer(){
//        this.gameMap = new GameMap();
//    }

    public static void main(String[] args) throws IOException {
        gameMap.spawnEmptyMap();
        ServerSocket s = new ServerSocket(Common.PORT);
        System.out.println("Server Started");
        try {
            while (true) {
                // Блокируется до возникновения нового соединения:
                Socket socket = s.accept();
                try {
                    players.add(new Server(socket));
                } catch (IOException | ClassNotFoundException e) {
                    // Если завершится неудачей, закрывается сокет,
                    // в противном случае, нить закроет его:
                    socket.close();
                }
            }
        } finally {
            s.close();
        }
    }
    public static synchronized void addChangesCell(Cell cell){
        if (!changesMap.containsKey(cell.getPoint()))
            changesMap.put(cell.getPoint(), cell);
    }

    public static synchronized void updateChangesCell(){
        changesMap = new HashMap<>();
    }
    public static void setGameMap(GameMap gameMap){
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
