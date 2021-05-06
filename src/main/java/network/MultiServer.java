package network;

import game.Game;
import map.GameMap;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class MultiServer {
    private static GameMap gameMap = new GameMap();
    public static LinkedList<Server> players = new LinkedList<>();

//   // public MultiServer(){
//        this.gameMap = new GameMap();
//    }

    public void start() throws IOException {
        ServerSocket s = new ServerSocket(Common.PORT);
        System.out.println("Server Started");
        try {
            while (true) {
                // Блокируется до возникновения нового соединения:
                Socket socket = s.accept();
                try {
                    players.add(new Server(socket, gameMap));
                } catch (IOException e) {
                    // Если завершится неудачей, закрывается сокет,
                    // в противном случае, нить закроет его:
                    socket.close();
                }
            }
        } finally {
            s.close();
        }
    }

    public static void setGameMap(GameMap gameMap){
        MultiServer.gameMap = gameMap;
    }

    public static synchronized void send(GameMap gameMap, ObjectOutputStream out) throws IOException {
        try {
            MultiServer.setGameMap(gameMap);
            out.writeObject(gameMap);
            out.flush();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}