package Models.Dungeon;

import Models.Dungeon.Room.Fight;
import Models.Dungeon.Room.Rest;
import Models.Dungeon.Room.Merchant;
import Models.Dungeon.Room.Treasure;
import sts.FightScene;
import sts.RestScene;
import sts.RoomScene;
import sts.TreasureScene;
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

        Merchant m = new Merchant(null);
        Rest r = new Rest(m);
        Treasure t = new Treasure(r);
        currentRoom = new Fight(t);
        game.currentScene = new FightScene();

        /*Merchant m = new Merchant(null);
        currentRoom = new Merchant(m);
        game.currentScene = new MerchantScene();*/

        /*Rest r = new Rest(null);
        currentRoom = new Rest(r);
        game.currentScene = new RestScene();*/

        /*Treasure t = new Treasure(null);
        currentRoom = new Treasure(t);
        game.currentScene = new TreasureScene();*/

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
