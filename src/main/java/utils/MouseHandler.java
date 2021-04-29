package utils;

import creatures.Direction;
import creatures.persons.player.Player;
import map.Cell;
import map.GameMap;
import map.Point;
import map.area.HandsArea;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ConcurrentModificationException;

public class MouseHandler implements MouseListener, MouseMotionListener {
    private final GameMap gameMap;
    private final Player player;

    private static int mouseX = -1;
    private static int mouseY = -1;
    private static int mouseB = -1;

    public MouseHandler(Player player, GameMap gameMap) {
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
        try {
            var cell = getCellByMouseCords();
            if (isCellFromActiveArea(cell)) {
                var rawPoint = cell.getPoint();
                var activePoint = new Point(rawPoint.x / Cell.cellSize, rawPoint.y / Cell.cellSize);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    handleLeftButton(activePoint);
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    handleRightButton(activePoint);
                }
            }
        } catch (NullPointerException exception) {
            System.out.println("OUT OF BOUNDS");
        } catch (ConcurrentModificationException exception) {
            System.out.println("THREAD ERROR, WILL FIX IT LATER");
        }
    }

    private void changeViewDirection(Direction assumedDirection,
                                     HandsArea area,
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