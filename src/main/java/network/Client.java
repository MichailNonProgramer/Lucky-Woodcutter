package network;

import creatures.persons.player.Player;
import map.GameMap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class Client extends ObjectOutputStream {
    public static Player player;
    private static GameMap gameMap;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    final Socket socket;

    public Client() throws IOException, ClassNotFoundException {
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
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static Player getPlayer() {
        return player;
    }

    public static GameMap getGameMap() {
        return gameMap;
    }
}
