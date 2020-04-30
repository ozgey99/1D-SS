package Models.Dungeon.Room;

import Models.Actions.RelicActions;
import Models.Creatures.AbstractCharacter;
import Models.Dungeon.AbstractRoom;
import Models.Object.AbstractRelic;
import Models.Object.Relics.BloodVial;
import Models.TextBasedUI;
import sts.FightScene;
import sts.Main;
import sts.TreasureScene;


import java.util.ArrayList;

import static sts.Main.game;

public class Treasure extends AbstractRoom {
    ArrayList<AbstractRelic> relics;
    int goldAmount;

    public Treasure(ArrayList<AbstractRoom> c) {
        type = RoomType.CHEST;
        children = c;
        done = false;
        relics = new ArrayList<>();
        generate();
    }

    private void generate() {
        relics.add(new BloodVial());
        goldAmount = (int) (Math.random() * 100) + 10;
    }

    @Override
    public void start() {

        game.currentScene = new TreasureScene();
        Main.window.setScene(
                game.currentScene);
        game.currentScene.initialize();
    }

    public void addRewards() {
        AbstractCharacter player = game.getPlayer();
        player.changeGold(goldAmount);
        for (AbstractRelic r : relics) {
            RelicActions.addRelic(player, r);
        }
    }

    @Override
    public RoomType getType() {
        return null;
    }

    @Override
    public ArrayList<AbstractRoom> getChildren() {
        return null;
    }

    @Override
    public boolean getDone() {
        return false;
    }

    public ArrayList<AbstractRelic> getRelics() {
        return relics;
    }

    public int getGoldAmount() {
        return goldAmount;
    }
}
