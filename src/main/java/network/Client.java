package network;

import creatures.persons.player.Player;
import game.Game;
import map.Cell;
import map.GameMap;
import utils.Point;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class Client extends  ObjectOutputStream {
    public static Player player;
    private static GameMap gameMap;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    final Socket socket;
    private static String id;

    public Client() throws IOException, ClassNotFoundException, InterruptedException {
        InetAddress address = InetAddress.getByName(null);
        socket = new Socket(address, Common.PORT);
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(null);
        out.flush();
        gameMap = (GameMap) in.readObject();
        player = (Player) in.readObject();
    }


    public synchronized static void start(Sender sender) throws IOException, ClassNotFoundException {
        try {
                out.writeObject(sender);
                gameMap = (GameMap) in.readObject();
                player = (Player) in.readObject();
                System.out.println(player.getX() + " " + player.getY());
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public String getId(){
        return id;
    }

    public static Player getPlayer(){
        return player;
    }

    public static GameMap getGameMap(){
        return gameMap;
    }
}
