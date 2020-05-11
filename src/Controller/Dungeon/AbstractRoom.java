package Controller.Dungeon;

import Controller.Dungeon.Room.RoomType;

import java.util.ArrayList;

public abstract class AbstractRoom {
    protected RoomType type;
    protected ArrayList<AbstractRoom> children;
    protected boolean done;
    protected boolean isUnion = false;

    public abstract void start();

    public void setUnion( boolean isUnion ){
        this.isUnion = isUnion;
    }
    public boolean isUnion(){
        return this.isUnion;
    }


    public abstract RoomType getType();
    public abstract ArrayList<AbstractRoom> getChildren();
    public abstract boolean getDone();
}
