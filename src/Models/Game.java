package Models;

import Models.Creatures.AbstractCharacter;
import Models.Creatures.Characters.Ironclad;
import Models.Dungeon.Dungeon;

public class Game {
    private Dungeon dungeon;
    private AbstractCharacter player;

    public Game() {
        dungeon = new Dungeon();

        player = new Ironclad();
        player.initialize();
    }

    public void start() {
        UI.displayStartMessage();
        UI.displayAct(dungeon);

        dungeon.getCurrentRoom().start();

        UI.displayFinishMessage(player);
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public AbstractCharacter getPlayer() {
        return player;
    }
}