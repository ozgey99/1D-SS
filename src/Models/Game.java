package Models;

import Models.Creatures.AbstractCharacter;
import Models.Creatures.Characters.Ironclad;
import Models.Dungeon.Dungeon;
import sts.Controller;

public class Game {
    private Dungeon dungeon;
    private AbstractCharacter player;

    public Game() {
        dungeon = new Dungeon();

        player = new Ironclad();
        player.initialize();
    }

    public void start() {
        Controller.displayStartMessage();
        Controller.displayAct(dungeon);

        dungeon.getCurrentRoom().start();

        if(dungeon.getCurrentRoom().getDone())
            Controller.displayFinishMessage(player);
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public AbstractCharacter getPlayer() {
        return player;
    }
}