package utils;

import creatures.persons.player.Player;
import map.Cell;
import map.GameMap;
import map.Point;

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

    public int getButton() {
        return mouseB;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Cell cell;
        try {
            cell = getCellByClick(e);
            System.out.println(cell.getX() + " " + cell.getY() + "\t" + mouseX + " " + mouseY);
            var rawPoint = cell.getPoint();
            var attackedPoint = new Point(rawPoint.x / Cell.cellSize, rawPoint.y / Cell.cellSize);
            player.attack(attackedPoint, gameMap);
        } catch (NullPointerException exception) {
            System.out.println("OUT OF BOUNDS");
        } catch (ConcurrentModificationException exception) {
            System.out.println("THREAD ERROR, WILL FIX IT LATER");
        }
    }

    private Cell getCellByClick(MouseEvent e) {
        var xOffset = player.getCamera().getXOffset();
        var yOffset = player.getCamera().getYOffset();

        for (var point : player.getHandsArea().getActiveCords()) {
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