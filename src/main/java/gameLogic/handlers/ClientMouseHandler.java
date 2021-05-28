package gameLogic.handlers;

import network.Client;
import network.Lock;
import network.Sender;
import utils.Direction;
import creatures.persons.player.Player;
import map.Cell;
import map.GameMap;
import utils.Point;
import gameLogic.area.Area;
import gameLogic.area.HandsArea;
import game.Game;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ConcurrentModificationException;

public class ClientMouseHandler implements MouseListener, MouseMotionListener {
    private final GameMap gameMap;
    private final Player player;

    private static int mouseX = -1;
    private static int mouseY = -1;
    private static int mouseB = -1;

    public ClientMouseHandler(Player player, GameMap gameMap) {
        this.gameMap = gameMap;
        this.player = player;
    }

    public int getX() {
        return mouseX;
    }

    public int getY() {
        return mouseY;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        try {
            var cell = getCellByMouseCords();
            var rawSelectedPoint = cell.getPoint();
            tryChangeViewDirection(rawSelectedPoint);
        } catch (NullPointerException ignored) {
        }

        player.changeSprite();
        player.getHandsArea().updateBounds(player.getDirection());
        player.getHandsArea().updateActiveCords();
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

    private void changeViewDirection(Direction assumedDirection,
                                     Area area,
                                     Point rawSelectedPoint,
                                     Point point1,
                                     Point point2,
                                     Point point3,
                                     Point point4,
                                     Point point5) {
        var selectedPoint = new Point(rawSelectedPoint.x / Cell.cellSize, rawSelectedPoint.y / Cell.cellSize);
        for (var point : area.getActiveCords()) {
            Cell currentsCell = gameMap.getMap().get(point);
            if (currentsCell != null) {
                if (gameMap.getMap().get(point).getPoint().equals(rawSelectedPoint)
                        && !(selectedPoint.equals(player.getPoint().add(point1)))
                        && !(selectedPoint.equals(player.getPoint().add(point2)))
                        && !(selectedPoint.equals(player.getPoint().add(point3)))
                        && !(selectedPoint.equals(player.getPoint().add(point4)))
                        && !(selectedPoint.equals(player.getPoint().add(point5))))
                    player.setDirection(assumedDirection);

            }
        }
    }

    private void tryChangeViewDirection(Point rawSelectedPoint) {
        var upCords = new HandsArea(player.getX(), player.getY(), Direction.UP);
        var downCords = new HandsArea(player.getX(), player.getY(), Direction.DOWN);
        var rightCords = new HandsArea(player.getX(), player.getY(), Direction.RIGHT);
        var leftCords = new HandsArea(player.getX(), player.getY(), Direction.LEFT);

        changeViewDirection(Direction.UP, upCords, rawSelectedPoint,
                ViewDirectionsConfig.upDown1, ViewDirectionsConfig.upDown2, ViewDirectionsConfig.upDown3,
                ViewDirectionsConfig.up1, ViewDirectionsConfig.up2);
        changeViewDirection(Direction.DOWN, downCords, rawSelectedPoint,
                ViewDirectionsConfig.upDown1, ViewDirectionsConfig.upDown2, ViewDirectionsConfig.upDown3,
                ViewDirectionsConfig.down1, ViewDirectionsConfig.down2);
        changeViewDirection(Direction.RIGHT, rightCords, rawSelectedPoint,
                ViewDirectionsConfig.rightLeft1, ViewDirectionsConfig.rightLeft2, ViewDirectionsConfig.rightLeft3,
                ViewDirectionsConfig.right1, ViewDirectionsConfig.right2);
        changeViewDirection(Direction.LEFT, leftCords, rawSelectedPoint,
                ViewDirectionsConfig.rightLeft1, ViewDirectionsConfig.rightLeft2, ViewDirectionsConfig.rightLeft3,
                ViewDirectionsConfig.left1, ViewDirectionsConfig.left2);
    }

    private void handleRightButton(Point buildingPoint) {
        player.build(buildingPoint, gameMap);
    }

    private void handleLeftButton(Point attackedPoint) {
        player.attack(attackedPoint, gameMap);
    }

    private Boolean isCellFromActiveArea(Cell cell) {
        for (var point : player.getHandsArea().getActiveCords()) {
            if (cell != null && cell.getPoint().equals(point.mul(Cell.cellSize))) {
                return true;

            }
        }
        return false;
    }

    private Cell getCellByMouseCords() {
        var xOffset = player.getCamera().getXOffset();
        var yOffset = player.getCamera().getYOffset();

        for (var point : player.getVisibleArea().getActiveCords()) {
            Cell cell = gameMap.getMap().get(point);
            if (cell != null) {
                if (cell.getX() < mouseX + xOffset
                        && mouseX + xOffset < cell.getX() + Cell.cellSize
                        && cell.getY() < mouseY + yOffset
                        && mouseY + yOffset < cell.getY() + Cell.cellSize) {
                    return cell;
                }
            }
        }
        throw new NullPointerException();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseB = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}