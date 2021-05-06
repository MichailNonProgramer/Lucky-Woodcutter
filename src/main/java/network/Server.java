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
    private final Socket s;
    private  ObjectInputStream in;
    private  ObjectOutputStream out;
    private GameMap gameMap;
    private HashMap<Point, Cell> map;

    public Server(Socket s, GameMap gameMap) throws IOException, ClassNotFoundException {
        this.s = s;
        this.gameMap = gameMap;
        out = new ObjectOutputStream((s.getOutputStream()));
        in = new ObjectInputStream(s.getInputStream());
        // Если любой из вышеприведенных вызовов приведет к
        // возникновению исключения, то вызывающий отвечает за
        // закрытие сокета. В противном случае, нить
        // закроет его.
        out.writeObject(gameMap);
        map = (HashMap<Point, Cell>) in.readObject();
        s.shutdownInput();
        System.out.println("Server start");

        start(); // вызываем run()
    }

    public void run() {
        try {
            System.out.println("Server start2");
               // map = (HashMap<Point, Cell>) in.readObject();
                if (map != null)
                    synchronizedGameMap();
                System.out.println(123);
                for (Server player : MultiServer.players)
                    MultiServer.send(gameMap, player.out);
        }
        catch (IOException e) {
            System.err.println("IO Exception");
        }
        finally {
            try {
                s.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
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
