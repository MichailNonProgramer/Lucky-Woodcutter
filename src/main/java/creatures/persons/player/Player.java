package creatures.persons.player;

import config.Config;
import creatures.Direction;
import graphics.DrawPriorities;
import graphics.sprites.PlayerSprites;
import map.Cell;
import map.GameMap;
import map.Point;
import utils.Camera;
import worldObjects.Movable;
import worldObjects.destructibleObject.DestructibleObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Movable implements IPlayer {
    private final Point point;
    private Direction direction;
    private BufferedImage spriteSheet;
    private ArrayList<Point> visibleCords;
    private final Camera camera;

    public int getX() {
        return point.x;
    }

    public int getY() {
        return point.y;
    }

    public ArrayList<Point> getVisibleCords() {
        return visibleCords;
    }

    public Camera getCamera() {
        return camera;
    }

    public Point getPoint() {
        return point;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Player(int x, int y) {
        this.point = new Point(x, y);
        this.direction = Direction.DEFAULT;
        this.spriteSheet = PlayerSprites.DOWN;
        this.visibleCords = updateVisibleCords();
        camera = new Camera(x, y);
        camera.centerOnPlayer(this);
    }

    public Player(Point point) {
        this.point = point;
        this.direction = Direction.DEFAULT;
        this.spriteSheet = PlayerSprites.DOWN;
        this.visibleCords = updateVisibleCords();
        camera = new Camera(point.x, point.y);
        camera.centerOnPlayer(this);
    }

    public void move(Direction dir, GameMap gameMap) {
        camera.move(dir.getPoint().x * Cell.cellSize,
                dir.getPoint().y * Cell.cellSize);

        var currentCell = gameMap.getMap().get(point);
        currentCell.removeObjectFromCell(this);
        point.add(dir.getPoint());
        var newCell = gameMap.getMap().get(point);
        newCell.addObjectInCell(this);

        this.visibleCords = updateVisibleCords();
    }

    public void attack(GameMap gameMap) {
        var cell = gameMap.getMap().get(this.point);
        for (var worldGameObj : cell.getObjectsInCell()) {
            if (worldGameObj instanceof DestructibleObject) {
                ((DestructibleObject) worldGameObj).reduceLives();
                if (((DestructibleObject) worldGameObj).getLives() == 0)
                    cell.removeObjectFromCell(worldGameObj);
            }
        }
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

    private ArrayList<Point> updateVisibleCords() {
        var visibleBounds = generateVisibleBounds();
        return generateVisibleCords(visibleBounds);
    }

    private ArrayList<Point> generateVisibleCords(Point[] visibleBounds) {
        var arr = new ArrayList<Point>();
        for (var i = visibleBounds[0].x; i < visibleBounds[1].x; i++)
            for (var j = visibleBounds[0].y; j < visibleBounds[1].y; j++) {
                arr.add(new Point(i, j));
            }
        return arr;
    }

    private Point[] generateVisibleBounds() {
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
