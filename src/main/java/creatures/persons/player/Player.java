package creatures.persons.player;

import graphics.sprites.PlayerSprites;
import map.GameMap;
import map.Point;
import graphics.DrawPriorities;
import worldObjects.Movable;
import worldObjects.WorldGameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Movable implements IPlayer {
    public Point getPoint() {
        return point;
    }

    private final Point point;

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private Direction direction;

    public int getX() {
        return point.x;
    }

    public int getY() {
        return point.y;
    }
    private BufferedImage spriteSheet;

    public Player(int x, int y) {
        this.point = new Point(x, y);
        this.direction = Direction.DEFAULT;
    }

    public Player(Point point) {
        this.point = point;
        this.direction = Direction.DEFAULT;
        this.spriteSheet = PlayerSprites.DOWN;
    }

    public void move(Direction dir) {
        var currentCell = GameMap.getMap().get(point);
        currentCell.removeObjectFromCell(this);
        point.add(dir.getPoint());
        var newCell = GameMap.getMap().get(point);
        newCell.addObjectInCell(this);
    }

    public void attack(KeyEvent e) {
    }

    @Override
    public DrawPriorities getPriority() {
        return DrawPriorities.PLAYER;
    }

    @Override
    public BufferedImage getSpriteSheet() {
        return this.spriteSheet;
    }

    @Override
    public void changeSprite() {
        switch (this.direction){
            case UP :
                this.spriteSheet = PlayerSprites.UP;
                break;
            case DOWN:
                this.spriteSheet = PlayerSprites.DOWN;
                break;
            case LEFT:
                this.spriteSheet = PlayerSprites.LEFT;
                break;
            case RIGHT:
                this.spriteSheet = PlayerSprites.RIGHT;
                break;

        }
    }

}
