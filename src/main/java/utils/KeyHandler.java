package utils;

import creatures.persons.player.Direction;
import creatures.persons.player.Player;
import map.Cell;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class KeyHandler implements KeyListener {
    private final Player player;
    private final Camera camera;

    public KeyHandler(Player player, Camera camera) {
        this.player = player;
        this.camera = camera;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        var moveEvents = Arrays.asList(KeyEvent.VK_A, KeyEvent.VK_D,
                KeyEvent.VK_W, KeyEvent.VK_S);
        var key = e.getKeyCode();
        if (moveEvents.contains(key)) {
            var newDirection = getDirection(e);
            player.setDirection(newDirection);
            player.changeSprite();
            player.move(newDirection);
            camera.centerOnPlayer(player);
//            System.out.println("LEFT " + player.visibleBounds[0].x + " " + player.visibleBounds[0].y);
//            System.out.println("RIGHT " + player.visibleBounds[1].x + " " + player.visibleBounds[1].y);
        }
        if (key == KeyEvent.VK_SPACE) player.attack();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public Direction getDirection(KeyEvent e) {
        var key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_D:
                camera.move(Cell.cellSize, 0);
                return Direction.RIGHT;
            case KeyEvent.VK_A:
                camera.move(-Cell.cellSize, 0);
                return Direction.LEFT;
            case KeyEvent.VK_W:
                camera.move(0, -Cell.cellSize);
                return Direction.UP;
            case KeyEvent.VK_S:
                camera.move(0, Cell.cellSize);
                return Direction.DOWN;
            default:
                camera.move(0, 0);
                return Direction.DEFAULT;
        }
    }

}