package network;

import creatures.persons.player.Player;
import map.Cell;
import map.GameMap;
import utils.Point;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class Client extends  ObjectOutputStream{
    private static HashMap<Point, Cell> activityMap;
    public static Player player;
    private static GameMap gameMap;
    private static ObjectInputStream in;
    private static HashMap<Point, Cell> changesCells = new HashMap<>();
    private static ObjectOutputStream out;
    private final InetAddress address = InetAddress.getByName(null);

    public Client() throws IOException, ClassNotFoundException {
        Socket socket = new Socket(address, Common.PORT);
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        gameMap = (GameMap) in.readObject();
        initPlayer();
        out.writeObject(changesCells);
        activityMap = new HashMap<>();
        for (Point point : player.getVisibleArea().getActiveCords()) {
            if(gameMap.getMap().get(point) != null)
                activityMap.put(point, new Cell(gameMap.getMap().get(point)));
        }
    }

    private void initPlayer(){
        player = new Player(1,2);
        gameMap.getMap().get(player.getPoint()).addObjectInCell(player);
        changesCells.put(player.getPoint() ,new Cell(gameMap.getMap().get(player.getPoint())));
    }

    public Player getPlayer() {
        return player;
    }


    public static void start() throws IOException, ClassNotFoundException {
        // Передаем null в getByName(), получая
        // специальный IP адрес "локальной заглушки"
        // для тестирования на машине без сети:
        InetAddress addr = InetAddress.getByName(null);
        // Альтернативно, вы можете использовать
        // адрес или имя:
        // InetAddress addr =
        // InetAddress.getByName("127.0.0.1");
        // InetAddress addr =
        // InetAddress.getByName("localhost");
        //System.out.println("addr = " + addr);
        // Помещаем все в блок try-finally, чтобы
        // быть уверенным, что сокет закроется:

            Socket socket = new Socket(addr, Common.PORT);
            //Socket socket = new Socket(addr, Common.PORT);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

            rememberChanges();
            gameMap = (GameMap) in.readObject();
//            gameMap.getMap().get(player.getPoint()).addObjectInCell(player);
            synchronizeGameMap();
            out.writeObject(changesCells);
            changesCells = new HashMap<>();
            activityMap = new HashMap<>();
            for (Point point : player.getVisibleArea().getActiveCords()) {
                if (gameMap.getMap().get(point) != null)
                    activityMap.put(point, new Cell(gameMap.getMap().get(point)));
            }
            socket.close();

            in.close();
            out.close();

        //System.out.println("closing...");
    }

    private synchronized static void synchronizeGameMap() {
        for (var pointCellEntry : changesCells.entrySet()) {
            var point = (Point) (pointCellEntry).getKey();
            var cell = (Cell) ( pointCellEntry).getValue();
            gameMap.getMap().put(point, cell);
        }
    }
// ИСПРАВИТЬ
    private synchronized static void rememberChanges() {
        System.out.println(gameMap.getMap().get(player.getPoint()).toString());
        for (var pointCellEntry : activityMap.entrySet()) {
            var point =  (pointCellEntry).getKey();
            if (gameMap.getMap().get(point) == null)
                continue;
            if (!gameMap.getMap().get(point).equals(activityMap.get(point))) {
                changesCells.put(point, gameMap.getMap().get(point));
                System.out.println(point.toString() + " " + gameMap.getMap().get(point).getObjectsInCell().toString() + " " + changesCells.get(point).getObjectsInCell().toString() );
            }
        }
    }

    public static void addMap(Cell cell){
        changesCells.put(cell.getPoint(), cell);
    }

    public synchronized static GameMap getGameMap(){
        return gameMap;
    }
}
