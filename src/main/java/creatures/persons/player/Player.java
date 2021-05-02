package creatures.persons.player;

import utils.Direction;
import graphics.DrawPriorities;
import graphics.sprites.PlayerSprites;
import map.GameMap;
import utils.Point;
import gameLogic.area.HandsArea;
import gameLogic.area.VisibleArea;
import gameLogic.Camera;
import gameLogic.Inventory;
import worldObjects.Movable;
import worldObjects.Solid;
import worldObjects.destructibleObject.DestructibleObject;
import worldObjects.destructibleObject.Resources;
import worldObjects.destructibleObject.WoodenWall;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Player extends Movable implements IPlayer, Serializable {
    private Point point;
    private Direction direction;
    private BufferedImage spriteSheet;
    private final Camera camera;
    private final VisibleArea visibleArea;
    private final HandsArea handsArea;
    private final Inventory inventory;

    public HandsArea getHandsArea() {
        return handsArea;
    }

    public VisibleArea getVisibleArea() {
        return visibleArea;
    }

    public int getX() {
        return point.x;
    }

    public int getY() {
        return point.y;
    }

    public Camera getCamera() {
        return camera;
    }

    public Point getPoint() {
        return point;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Player(Point point) {
        this.point = point;
        this.direction = Direction.DOWN;
        this.spriteSheet = PlayerSprites.DOWN;
        this.visibleArea = new VisibleArea(point.x, point.y);
        this.handsArea = new HandsArea(point.x, point.y, direction);
        this.camera = new Camera(point);
        this.camera.centerOnPlayer(this);
        this.inventory = new Inventory();
    }

    public Player(int x, int y) {
        this(new Point(x, y));
    }

    public void move(Direction dir, GameMap gameMap) {
        camera.move(dir.getPoint());

        var currentCell = gameMap.getMap().get(point);
        currentCell.removeObjectFromCell(this);
        this.point = point.add(dir.getPoint());
        var newCell = gameMap.getMap().get(point);
        newCell.addObjectInCell(this);

        this.handsArea.setUpdatedActiveCords(point);
        this.visibleArea.setUpdatedActiveCords(point);
    }

    public void attack(Point point, GameMap gameMap) {
        var cell = gameMap.getMap().get(point);
        for (var worldGameObj : cell.getObjectsInCell()) {
            if (worldGameObj instanceof DestructibleObject) {
                ((DestructibleObject) worldGameObj).reduceLives();
                if (((DestructibleObject) worldGameObj).getLives() == 0) {
                    cell.removeObjectFromCell(worldGameObj);
                    inventory.addResources((DestructibleObject) worldGameObj);
                    System.out.println("INVENTORY:" + " " + this.inventory.getContainer());
                }
            }
        }
    }

    public void build(Point point, GameMap gameMap) {
        var buildCost = 3;
        var cell = gameMap.getMap().get(point);
        var isCanBuild = true;
        for (var worldGameObj : cell.getObjectsInCell()) {
            if (worldGameObj instanceof Solid
                    || worldGameObj instanceof DestructibleObject
                    || worldGameObj instanceof IPlayer) {
                isCanBuild = false;
                break;
            }
        }
        if (isCanBuild) {
            if (inventory.getContainer().containsKey(Resources.Wood) && inventory.getContainer().get(Resources.Wood) >= buildCost) {
                var wall = new WoodenWall(cell.getPoint());
                cell.addObjectInCell(wall);
                inventory.removeResources(wall,buildCost);
                System.out.println("INVENTORY:" + " " + this.inventory.getContainer());
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
}
