package Models.Dungeon.Room;

import Models.Dungeon.AbstractRoom;
import Models.UI;

public class Fight extends AbstractRoom {
    private RoomType type;
    private AbstractRoom children;
    private boolean done;

    public Fight(AbstractRoom children) {
        this.type = RoomType.FIGHT;
        this.children = children;
        this.done = false;
    }

    @Override
    public void start() {
        UI.displayFightRoom(this);
    }

    // Getters and setters.
    // --------------------------------------------------------------------------------------------------------

    @Override
    public RoomType getType() {
        return type;
    }

    @Override
    public AbstractRoom getChildren() {
        return children;
    }

    @Override
    public boolean getDone() {
        return done;
    }
}
