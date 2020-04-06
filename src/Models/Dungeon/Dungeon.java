package Models.Dungeon;

import Models.Dungeon.Room.Fight;

public class Dungeon {
    private int act;
    private String name;

    private AbstractRoom root;
    private AbstractRoom currentRoom;

    public Dungeon() {
        this.act = 1;
        this.name = "The Exordium";
    }

    public void generate() {
        currentRoom = new Fight(null);
    }

    public int getAct() {
        return act;
    }

    public String getName() {
        return name;
    }

    public AbstractRoom getCurrentRoom() {
        return currentRoom;
    }

    public boolean ascend() {
        if (this.currentRoom.getChildren() == null) return false;
        this.currentRoom = this.currentRoom.getChildren();
        return true;
    }
}
