package network;

import utils.Point;

import java.io.Serializable;

public class Sender implements Serializable {
    final String event;
    final int keyCode;
    final Point mousePoint;
    final String id;

    public Sender(String event, int keyCode, Point mousePoint, String  id){
        this.event = event;
        this.keyCode = keyCode;
        this.mousePoint = mousePoint;
        this.id = id;
    }
}
