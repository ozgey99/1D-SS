package Controller.Dungeon;

import Controller.Dungeon.Room.RoomType;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class AbstractRoom implements Serializable {
    protected RoomType type;
    protected ArrayList<AbstractRoom> children;
    protected boolean done;

    public abstract void start();

    public abstract RoomType getType();
    public abstract ArrayList<AbstractRoom> getChildren();
    public abstract boolean getDone();
}
