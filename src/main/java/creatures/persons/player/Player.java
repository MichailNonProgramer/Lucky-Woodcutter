package creatures.persons.player;

import game.Game;
import map.Cell;
import utils.Direction;
import graphics.DrawPriorities;
import graphics.sprites.PlayerSprites;
import map.GameMap;
import utils.Point;
import gameLogic.area.HandsArea;
import gameLogic.area.VisibleArea;
import gameLogic.Camera;
import gameLogic.Inventory;
import weapons.Weapons;
import worldObjects.Movable;
import worldObjects.Solid;
import worldObjects.destructibleObject.DestructibleObject;
import worldObjects.destructibleObject.Resources;
import worldObjects.destructibleObject.StoneWall;
import worldObjects.destructibleObject.WoodenWall;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Movable implements IPlayer, Serializable {
    private String id;
    private Point point;
    private Direction direction;
    private transient BufferedImage spriteSheet;
    private Camera camera;
    private VisibleArea visibleArea;
    private HandsArea handsArea;
    private Inventory inventory;
    private Resources activeResource;
//    private ArrayList<Weapons> weapons;
//    private Weapons activeWeapon;
    private static final long serialVersionUID = 1L;

    public void setCamera(Camera camera){
        this.camera = camera;
    }

    public void setId(String id){
        this.id = id;
    }
    public void setVisibleArea(VisibleArea visibleArea){
        this.visibleArea = visibleArea;
    }
    public void setHandsArea(HandsArea handsArea){
        this.handsArea = handsArea;
    }
    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }
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

    public Resources getActiveResource() {
        return activeResource;
    }

    public void setActiveResource(Resources activeResource) {
        this.activeResource = activeResource;
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
        this.activeResource = Resources.Wood;
        this.id = generateID();
    }

    public Player(int x, int y) {
        this(new Point(x, y));
    }

    public Player(Player other){
        this(other.getPoint());
        this.setActiveResource(other.getActiveResource());
        this.setDirection(other.getDirection());
        this.setSpriteSheet();
        this.setVisibleArea(other.getVisibleArea());

        this.setCamera(other.getCamera());
        this.setHandsArea(other.handsArea);
        this.setId(other.getId());
        this.setInventory(other.getInventory());
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public synchronized void move(Direction dir, Game game) {
        camera.move(dir.getPoint());

        var currentCell = game.getGameMap().getMap().get(point);
        currentCell.removeObjectFromCell(this);
        this.point = point.add(dir.getPoint());
        var newCell = game.getGameMap().getMap().get(point);
        newCell.addObjectInCell(this);
        game.getGameMap().getMap().put(point, newCell);

        this.handsArea.setUpdatedActiveCords(point);
        this.visibleArea.setUpdatedActiveCords(point);
    }

    public synchronized void attack(Point point, GameMap gameMap) {
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

    private String generateID(){
        var random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
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
            if (inventory.getContainer().containsKey(activeResource) && inventory.getContainer().get(activeResource) >= buildCost) {
                DestructibleObject wall;
                switch (activeResource) {
                    case Wood:
                        wall = new WoodenWall(cell.getPoint());
                        break;
                    case Stone:
                        wall = new StoneWall(cell.getPoint());
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + activeResource);
                }

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

    @Override
    public boolean equals(Object obj){
        try {
            System.out.println(this.point.toString() + ' ' + ((Player)obj).point.toString());
            System.out.println(this.point == ((Player)obj).point);
            return this.point == ((Player)obj).point;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.point.x;
        hash = 71 * hash + this.point.y;
        return hash;
    }

    @Override
    public void setSpriteSheet() {
        changeSprite();
    }

    public String getId() {
        return id;
    }
}
