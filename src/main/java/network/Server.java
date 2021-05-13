package network;

import creatures.persons.player.IPlayer;
import creatures.persons.player.Player;
import map.Cell;
import map.GameMap;
import utils.Point;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server extends Thread {
    private final Socket s;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private GameMap serverMap;
    private HashMap<Point, Cell> map;

    public Server(Socket s) throws IOException, ClassNotFoundException {
        this.s = s;
        this.serverMap = MultiServer.gameMap;
        this.map = new HashMap<>();

        start(); // вызываем run()
    }

    public void run() {
        try {
            out = new ObjectOutputStream((s.getOutputStream()));
            in = new ObjectInputStream(s.getInputStream());
            out.writeObject(serverMap);
            map = (HashMap<Point, Cell>) in.readObject();
            synchronizedGameMap();
            s.shutdownInput();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        MultiServer.setGameMap(serverMap);
        // map = (HashMap<Point, Cell>) in.readObject();
//                for (Server player : MultiServer.players)
//                    MultiServer.send(gameMap, player.out);
//        }
//        catch (IOException e) {
//            System.err.println("IO Exception");
//        }
//        finally {
        try {
            s.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private synchronized void synchronizedGameMap(){
        for (var pointCellEntry : map.entrySet()) {
            var point = (Point) ( pointCellEntry).getKey();
            var cell = (Cell) ( pointCellEntry).getValue();
            if (!serverMap.getMap().get(point).equals(cell))
                for(var obj : serverMap.getMap().get(point).getObjectsInCell()) {
                    if (obj instanceof IPlayer) {
                        var index = cell.getObjectsInCell().indexOf( obj);
                        serverMap.getMap().get(point).addObjectInCell(cell.getObjectsInCell().get(index));
                    }
                }
            else
                serverMap.getMap().put(point, cell);
        }
    }
}

