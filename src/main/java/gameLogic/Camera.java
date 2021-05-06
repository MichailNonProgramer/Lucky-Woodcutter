package gameLogic;

import config.GameConfig;
import creatures.persons.player.Player;
import map.Cell;
import utils.Point;

import java.io.Serializable;

public class Camera implements Serializable {
    private static final long serialVersionUID = 1L;
    public int getXOffset() {
        return offsets.x;
    }

    public int getYOffset() {
        return offsets.y;
    }

    private Point offsets;

    public Camera(int xOffset, int yOffset) {
        this(new Point(xOffset, yOffset));
    }

    public Camera(Point offsets) {
        this.offsets = offsets.mul(Cell.cellSize);
    }

    public void move(Point cords) {
        this.offsets = offsets.add(cords.mul(Cell.cellSize));
    }

    public void centerOnPlayer(Player player) {
        this.offsets = new Point(player.getX() * Cell.cellSize
                - GameConfig.getScreenWidth() / 2 + Cell.cellSize / 2,
                player.getY() * Cell.cellSize
                        - GameConfig.getScreenHeight() / 2 + Cell.cellSize / 2);
    }

}
