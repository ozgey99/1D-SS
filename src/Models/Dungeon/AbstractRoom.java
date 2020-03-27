package Models.Dungeon;

import Models.Dungeon.Room.RoomType;

public abstract class AbstractRoom {
    private RoomType type;
    private AbstractRoom children;
    private boolean done;

    public abstract void start();

    public abstract RoomType getType();
    public abstract AbstractRoom getChildren();
    public abstract boolean getDone();
}
