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


public class Client {
    private GameMap gameMap;
    private HashMap<Point, Cell> map = new HashMap<>();
    private HashMap<Point, Cell> activityMap;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private final InetAddress address = InetAddress.getByName(null);
    private Player player;

    public Client() throws IOException, ClassNotFoundException {
        Socket socket = new Socket(address, Common.PORT);
        System.out.println(124);
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println(125);
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Create Client");
            this.gameMap = (GameMap) in.readObject();
            System.out.println("Create Client2");
            initPlayer();
            System.out.println("Create Client3");
            out.writeObject(map);
            System.out.println(gameMap);
    }

    private void initPlayer(){
        this.player = new Player(1,2);
        var cell = this.gameMap.getMap().get(player.getPoint());
        cell.addObjectInCell(player);
        map.put(cell.getPoint() ,cell);
    }

    public Player getPlayer() {
        return player;
    }

    public void start() throws IOException {
        activityMap = new HashMap<>();
        for(Point point: player.getVisibleArea().getActiveCords()){
            activityMap.put(point, gameMap.getMap().get(point));
        }
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
        System.out.println("addr = " + addr);
        Socket socket = new Socket(addr, Common.PORT);
        // Помещаем все в блок try-finally, чтобы
        // быть уверенным, что сокет закроется:

        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            this.gameMap = (GameMap) in.readObject();
            synchronizedGameMap();
            out.writeObject(map);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("closing...");
            socket.close();
        }
    }

    private void synchronizedGameMap(){
        for(var point : this.player.getVisibleArea().getActiveCords()){
            if (gameMap.getMap().get(point) != map.get(point))
                    map.put(point, gameMap.getMap().get(point));
        }
    }

    public GameMap getGameMap(){
        return this.gameMap;
    }
}
