package Models.Dungeon;

import Models.Dungeon.Room.RoomType;

public abstract class AbstractRoom {
    protected RoomType type;
    protected AbstractRoom children;
    protected boolean done;

    public abstract void start();

    public abstract RoomType getType();
    public abstract AbstractRoom getChildren();
    public abstract boolean getDone();
}
