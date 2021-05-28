package gameLogic.handlers;

import creatures.persons.player.Player;
import gameLogic.area.Area;
import gameLogic.area.HandsArea;
import map.Cell;
import map.GameMap;
import utils.Direction;
import utils.Point;

import java.util.ConcurrentModificationException;

public class MouseHandlerGeneral {
    public static void handleMoved(Player player, GameMap gameMap, Point mousePoint) throws NullPointerException {
        var cell = MouseHandlerGeneral.getCellByMouseCords(player, gameMap,
                mousePoint.x, mousePoint.y);
        var rawSelectedPoint = cell.getPoint();
        MouseHandlerGeneral.tryChangeViewDirection(player, gameMap, rawSelectedPoint);
        player.changeSprite();
        player.getHandsArea().updateBounds(player.getDirection());
        player.getHandsArea().updateActiveCords();
    }

    public static void handlePressed(Player player, GameMap gameMap, Point mousePoint, boolean isLeft, boolean isRight) {
        try {
            var cell = MouseHandlerGeneral.getCellByMouseCords(player, gameMap, mousePoint.x, mousePoint.y);
            if (MouseHandlerGeneral.isCellFromActiveArea(player, cell)) {
                var rawPoint = cell.getPoint();
                var activePoint = new Point(rawPoint.x / Cell.cellSize, rawPoint.y / Cell.cellSize);
                if (isLeft)
                    MouseHandlerGeneral.handleLeftButton(player, gameMap, activePoint);
                else if (isRight)
                    MouseHandlerGeneral.handleRightButton(player, gameMap, activePoint);
            }
        } catch (NullPointerException exception) {
            System.out.println("OUT OF BOUNDS");
        } catch (ConcurrentModificationException exception) {
            System.out.println("THREAD ERROR, WILL FIX IT LATER");
        }
    }

    public static void handleRightButton(Player player, GameMap gameMap, Point buildingPoint) {
        player.build(buildingPoint, gameMap);
    }

    public static void handleLeftButton(Player player, GameMap gameMap, Point attackedPoint) {
        player.attack(attackedPoint, gameMap);
    }

    public static void changeViewDirection(Player player, GameMap gameMap, Direction assumedDirection,
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

    public synchronized static void tryChangeViewDirection(Player player, GameMap gameMap, Point rawSelectedPoint) {
        var upCords = new HandsArea(player.getX(), player.getY(), Direction.UP);
        var downCords = new HandsArea(player.getX(), player.getY(), Direction.DOWN);
        var rightCords = new HandsArea(player.getX(), player.getY(), Direction.RIGHT);
        var leftCords = new HandsArea(player.getX(), player.getY(), Direction.LEFT);

        changeViewDirection(player, gameMap, Direction.UP, upCords, rawSelectedPoint,
                ViewDirectionsConfig.upDown1, ViewDirectionsConfig.upDown2, ViewDirectionsConfig.upDown3,
                ViewDirectionsConfig.up1, ViewDirectionsConfig.up2);
        changeViewDirection(player, gameMap, Direction.DOWN, downCords, rawSelectedPoint,
                ViewDirectionsConfig.upDown1, ViewDirectionsConfig.upDown2, ViewDirectionsConfig.upDown3,
                ViewDirectionsConfig.down1, ViewDirectionsConfig.down2);
        changeViewDirection(player, gameMap, Direction.RIGHT, rightCords, rawSelectedPoint,
                ViewDirectionsConfig.rightLeft1, ViewDirectionsConfig.rightLeft2, ViewDirectionsConfig.rightLeft3,
                ViewDirectionsConfig.right1, ViewDirectionsConfig.right2);
        changeViewDirection(player, gameMap, Direction.LEFT, leftCords, rawSelectedPoint,
                ViewDirectionsConfig.rightLeft1, ViewDirectionsConfig.rightLeft2, ViewDirectionsConfig.rightLeft3,
                ViewDirectionsConfig.left1, ViewDirectionsConfig.left2);
    }

    public static Boolean isCellFromActiveArea(Player player, Cell cell) {
        for (var point : player.getHandsArea().getActiveCords()) {
            if (cell != null && cell.getPoint().equals(point.mul(Cell.cellSize))) {
                return true;

            }
        }
        return false;
    }

    public static Cell getCellByMouseCords(Player player, GameMap gameMap, int mouseX, int mouseY) {
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
}
