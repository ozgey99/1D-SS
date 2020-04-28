package Models.Dungeon;

import Models.Dungeon.Room.Fight;
import Models.Dungeon.Room.Merchant;
import Models.Dungeon.Room.Treasure;
import sts.FightScene;
import sts.RoomScene;
import  sts.MerchantScene;

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
        //Treasure t = new Treasure(null);
        //currentRoom = new Fight(t);
        //game.currentScene = new FightScene();
        Merchant m = new Merchant(null); // ??
        currentRoom = new Merchant(m);
        game.currentScene = new MerchantScene();

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
