package gameLogic.handlers;

import creatures.persons.player.Player;
import game.Game;
import map.Cell;
import map.GameMap;
import network.Client;
import network.Lock;
import network.Sender;
import utils.Point;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

public class ClientMouseHandler implements MouseListener, MouseMotionListener {
    private final Game game;
    private final GameMap gameMap;
    private final Player player;

    private int mouseX = -1;
    private int mouseY = -1;
    private Point mousePoint;
    private Cell prevCell;

    public ClientMouseHandler(Player player, Game game) {
        this.game = game;
        this.gameMap = game.getGameMap();
        this.player = player;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        mousePoint = new Point(mouseX, mouseY);
        if (game.isSoloGame())
            try {
                MouseHandlerGeneral.handleMoved(player, gameMap, mousePoint);
            } catch (NullPointerException ignored) {
            }
        else {
            try {
                var cell = MouseHandlerGeneral.getCellByMouseCords(player, gameMap, mouseX, mouseY);
                if (prevCell != null && prevCell.getPoint().equals(cell.getPoint()))
                    return;
                prevCell = cell;
            } catch (NullPointerException ignored) {
            }
            try {
                Lock.isLockClient = true;
                Client.start(new Sender("MouseMoved", -1, mousePoint, player.getId()));
                game.setGameMap(Client.getGameMap());
                game.setPlayer(Client.getPlayer());
                Lock.isLockClient = false;

            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        var isLeft = SwingUtilities.isLeftMouseButton(e);
        var isRight = SwingUtilities.isRightMouseButton(e);

        if (game.isSoloGame())
            MouseHandlerGeneral.handlePressed(player, gameMap, mousePoint, isLeft, isRight);
        else {
            if (SwingUtilities.isLeftMouseButton(e) || SwingUtilities.isRightMouseButton(e)) {
                try {
                    Lock.isLockClient = true;
                    if (isLeft) {
                        Client.start(new Sender("MousePressedLeft", -1, mousePoint, player.getId()));
                    } else if (isRight) {
                        Client.start(new Sender("MousePressedRight", -1, mousePoint, player.getId()));
                    }
                    game.setGameMap(Client.getGameMap());
                    game.setPlayer(Client.getPlayer());
                    Lock.isLockClient = false;

                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}