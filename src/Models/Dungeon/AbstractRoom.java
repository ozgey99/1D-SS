package Models.Dungeon;

import Models.Dungeon.Room.RoomType;

import java.util.ArrayList;

public abstract class AbstractRoom {
    protected RoomType type;
    protected ArrayList<AbstractRoom> children;
    protected boolean done;

    public abstract void start();

    public abstract RoomType getType();
    public abstract ArrayList<AbstractRoom> getChildren();
    public abstract boolean getDone();
}
