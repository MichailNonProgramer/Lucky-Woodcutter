package creatures.persons.player;

import map.GameMap;
import map.Point;
import graphics.DrawPriorities;
import worldObjects.Movable;
import worldObjects.WorldGameObject;

import java.awt.event.KeyEvent;

public class Player extends Movable implements WorldGameObject, IPlayer {
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

    public Player(int x, int y) {
        this.point = new Point(x, y);
        this.direction = Direction.DEFAULT;
    }

    public Player(Point point) {
        this.point = point;
        this.direction = Direction.DEFAULT;
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

}
