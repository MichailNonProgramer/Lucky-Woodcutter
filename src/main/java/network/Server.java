package network;

import creatures.persons.player.Player;
import gameLogic.handlers.ServerKeyHandler;
import gameLogic.handlers.ServerMouseHandler;
import utils.Point;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Server extends Thread {
    private final Socket s;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Player player;
    private ServerKeyHandler keyHandler;
    private ServerMouseHandler mouseHandler;

    public Server(Socket s) throws IOException, ClassNotFoundException {
        var r = new Random();
        this.s = s;
        out = new ObjectOutputStream((s.getOutputStream()));
        in = new ObjectInputStream(s.getInputStream());
        this.player = new Player(new Point(r.nextInt(10), r.nextInt(10)));
        System.out.println(player.getX() + " " + player.getY());
        MultiServer.players.put( this.player.getId(), this.player);
        MultiServer.gameMap.getMap().get(this.player.getPoint()).addObjectInCell(this.player);
        this.keyHandler = new ServerKeyHandler(player, MultiServer.gameMap);
        this.mouseHandler = new ServerMouseHandler(player, MultiServer.gameMap);
        in.readObject();
        out.writeObject(MultiServer.gameMap);
        out.writeObject(MultiServer.players.get(player.getId()));
        start();
    }

    public void run() {
        while (true) {
            try {
                var send = in.readObject();
                if (send != null) {
                    parseMessage((Sender) send);
                }
                    out.writeObject(MultiServer.gameMap);
                    out.writeObject(player);
                    out.reset();
                    System.out.println(player.getX() + " " + player.getY());
                    out.flush();
                    Thread.sleep(100);

            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void parseMessage(Sender message) {
        var event = message.event;
        System.out.println(event);
        switch (event) {
            case "KeyPressed":
                keyHandler.keyPressed(message.keyCode);
                MultiServer.players.put(player.getId(), player);
                break;
            case "MousePressedLeft":
                mouseHandler.mousePressedLeft(message.mousePoint);
                MultiServer.players.put(player.getId(), player);
                break;
            case "MousePressedRight":
                mouseHandler.mousePressedRight(message.mousePoint);
                MultiServer.players.put(player.getId(), player);
                break;
            case "MouseMoved":
                mouseHandler.mouseMoved(message.mousePoint);
                MultiServer.players.put(player.getId(), player);
                break;
        }
    }

}

