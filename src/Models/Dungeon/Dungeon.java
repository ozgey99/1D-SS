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
        generate();
    }

    private void generate() {
        currentRoom = new Fight(null);
    }

    public int getAct() {
        return act;
    }

    public AbstractRoom getCurrentRoom() {
        return currentRoom;
    }

    public void ascend() {
        this.currentRoom = this.currentRoom.getChildren();
    }
}
