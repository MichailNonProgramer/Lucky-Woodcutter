package utils;

import creatures.Direction;
import creatures.persons.player.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class KeyHandler implements KeyListener {
    private final Player player;

    public KeyHandler(Player player) {
        this.player = player;
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
                return Direction.RIGHT;
            case KeyEvent.VK_A:
                return Direction.LEFT;
            case KeyEvent.VK_W:
                return Direction.UP;
            case KeyEvent.VK_S:
                return Direction.DOWN;
            default:
                return Direction.DEFAULT;
        }
    }

}