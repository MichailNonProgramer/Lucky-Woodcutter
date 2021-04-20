package utils;

import config.Config;
import creatures.persons.player.Player;
import map.Cell;

public class Camera {
    public float getxOffset() {
        return xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    private float xOffset, yOffset;

    public Camera(float xOffset, float yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void move(float xAmt, float yAmt) {
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
