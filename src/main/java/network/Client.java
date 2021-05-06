package network;

import creatures.persons.player.Player;
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
    private HashMap<Point, Cell> map;

    private Client(){

    }


    public void start() throws IOException {
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
            System.out.println("socket = " + socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            // Вывод автоматически Output быталкивается PrintWriter'ом.
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
            for (int i = 0; i < 10; i++) {
                out.println("howdy " + i);
                String str = in.readLine();
                System.out.println(str);
            }
            out.println("END");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("closing...");
            socket.close();
        }
    }
}
