package network;

import map.Cell;
import map.GameMap;
import utils.Point;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server extends Thread
{
    private final Socket socket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private GameMap gameMap;
    private HashMap<Point, Cell> map;

    public Server(Socket s, GameMap gameMap) throws IOException {
        this.socket = s;
        this.gameMap = gameMap;
        in = new ObjectInputStream(s.getInputStream());
        out = new ObjectOutputStream((s.getOutputStream()));
        // Если любой из вышеприведенных вызовов приведет к
        // возникновению исключения, то вызывающий отвечает за
        // закрытие сокета. В противном случае, нить
        // закроет его.
        start(); // вызываем run()
    }

    public void run() {
        try {
                map = (HashMap<Point, Cell>) in.readObject();
                synchronizedGameMap();
                for (Server player : MultiServer.players)
                    MultiServer.send(gameMap, player.out);
        }
        catch (IOException | ClassNotFoundException e) {
            System.err.println("IO Exception");
        }
        finally {
            try {
                socket.close();
            }
            catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }

    private void synchronizedGameMap(){
        for (Map.Entry<Point, Cell> pointCellEntry : map.entrySet()) {
            var point = (Point) ((Map.Entry) pointCellEntry).getKey();
            var cell = (Cell) ((Map.Entry) pointCellEntry).getValue();
            if (!gameMap.getMap().get(point).equals(cell))
                gameMap.getMap().put(point, cell);
        }
    }
}
