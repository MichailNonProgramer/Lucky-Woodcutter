package utils;

import config.Config;
import creatures.persons.player.Player;
import map.Cell;

public class Camera {
    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    private int xOffset, yOffset;

    public Camera(int xOffset, int yOffset) {
        this.xOffset = xOffset * Cell.cellSize;
        this.yOffset = yOffset * Cell.cellSize;
    }

    public void move(int xAmt, int yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
    }

    public void centerOnPlayer(Player player) {
        xOffset = player.getX() * Cell.cellSize
                - Config.getScreenWidth() / 2
                + Cell.cellSize / 2;
        yOffset = player.getY() * Cell.cellSize
                - Config.getScreenHeight() / 2
                + Cell.cellSize / 2;
    }

}
