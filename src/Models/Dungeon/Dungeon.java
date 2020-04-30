package Models.Dungeon;

import Models.Dungeon.Room.Fight;
import Models.Dungeon.Room.Rest;
import Models.Dungeon.Room.Treasure;
import sts.FightScene;
import sts.RestScene;
import sts.RoomScene;
import sts.TreasureScene;

import static sts.Main.game;

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
       /* Fight z = new Fight(null);
        Fight f = new Fight(z);
        currentRoom = new Treasure(f);
        game.currentScene = new TreasureScene();*/
        currentRoom = new Rest(null);
        game.currentScene = new RestScene();


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

        if (this.currentRoom.getChildren() == null) {
            System.out.println("ASCEND RETURN FALSE");
            return false;
        }
        System.out.println("ASCEND IN");
        this.currentRoom = this.currentRoom.getChildren();
        this.currentRoom.start();
        return true;
    }
}
