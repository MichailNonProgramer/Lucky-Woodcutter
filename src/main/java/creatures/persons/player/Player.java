package creatures.persons.player;

import config.Config;
import graphics.DrawPriorities;
import graphics.sprites.PlayerSprites;
import map.Cell;
import map.GameMap;
import map.Point;
import worldObjects.Movable;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Movable implements IPlayer {
    public Point[] visibleBounds;
    public ArrayList<Point> visibleCords;

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
        this.spriteSheet = PlayerSprites.DOWN;
        visibleBounds = updateVisibleBounds();
        visibleCords = getVisibleCords();
    }

    private ArrayList<Point> getVisibleCords() {
        var arr = new ArrayList<Point>();
        for (var i = visibleBounds[0].x; i < visibleBounds[1].x; i++)
            for (var j = visibleBounds[0].y; j < visibleBounds[1].y; j++) {
                arr.add(new Point(i, j));
            }
        return arr;
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
        visibleBounds = updateVisibleBounds();
        visibleCords = getVisibleCords();
    }

    public void attack(KeyEvent e) {
    }

    @Override
    public DrawPriorities getDrawPriority() {
        return DrawPriorities.PLAYER;
    }

    @Override
    public BufferedImage getSpriteSheet() {
        return this.spriteSheet;
    }

    @Override
    public void changeSprite() {
        switch (this.direction) {
            case UP:
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

    private Point[] updateVisibleBounds() {
        return new Point[]{new Point(getLeftBoundX(), getLeftBoundY()),
                new Point(getRightBoundX(), getRightBoundY())};
    }

    private int getLeftBoundX() {
        return getX() - Config.getScreenWidth() / 2 / Cell.cellSize;
    }

    private int getLeftBoundY() {
        return getY() - Config.getScreenHeight() / 2 / Cell.cellSize;
    }

    private int getRightBoundX() {
        return getX() + Config.getScreenWidth() / 2 / Cell.cellSize + 1;
    }

    private int getRightBoundY() {
        return getY() + Config.getScreenHeight() / 2 / Cell.cellSize + 1;
    }
}
