package network;

import creatures.persons.player.Player;
import game.Game;
import gameLogic.area.VisibleArea;
import map.Cell;
import map.GameMap;
import utils.Point;

import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


public class Client {
    private static HashMap<Object, Object> activityMap;
    public static Player player;
    private static GameMap gameMap;
    private static HashMap<Point, Cell> changesCells;
    private static ObjectInputStream in;
    private static HashMap<Point, Cell> map = new HashMap<>();
    private static ObjectOutputStream out;
    private final InetAddress address = InetAddress.getByName(null);

    public Client() throws IOException, ClassNotFoundException {
        Socket socket = new Socket(address, Common.PORT);
        System.out.println(124);
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println(125);
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Create Client");
            gameMap = (GameMap) in.readObject();
            System.out.println("Create Client2");
            initPlayer();
            System.out.println("Create Client3");
            out.writeObject(map);
            System.out.println(gameMap.getMap().get(new Point(1, 2)).getObjectsInCell().toString());
            activityMap = new HashMap<>();
            for(Point point: player.getVisibleArea().getActiveCords()){
                activityMap.put(point, gameMap.getMap().get(point));}
    }

    private void initPlayer(){
        player = new Player(1,2);
        var cell = gameMap.getMap().get(player.getPoint());
        cell.addObjectInCell(player);
        //gameMap.getMap().get(cell.getPoint()).addObjectInCell(player);
        map.put(cell.getPoint() ,cell);
    }

    public Player getPlayer() {
        return player;
    }

    public static void start() throws IOException {
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

        try (Socket socket = new Socket(addr, Common.PORT)) {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            synchronizedGameMapWrite();
            out.writeObject(map);
            gameMap = (GameMap) in.readObject();
            map = new HashMap<>();
            activityMap = new HashMap<>();
            for (Point point : player.getVisibleArea().getActiveCords()) {
                activityMap.put(point, gameMap.getMap().get(point));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println("closing...");
    }

    private static void synchronizedGameMapWrite(){
        for(var point : player.getVisibleArea().getActiveCords()){
            if (gameMap.getMap().get(point) != activityMap.get(point))
                    map.put(point, gameMap.getMap().get(point));
        }
//        for (Map.Entry<Point, Cell> pointCellEntry : changesCells.entrySet()) {
//            var point = (Point) ((Map.Entry) pointCellEntry).getKey();
//            var cell = (Cell) ((Map.Entry) pointCellEntry).getValue();
//            gameMap.getMap().put(point, cell);
//        }
    }

    public static void addMap(Cell cell){
        map.put(cell.getPoint(), cell);
    }

    public GameMap getGameMap(){
        return gameMap;
    }
}
