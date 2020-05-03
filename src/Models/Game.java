package Models;

import Models.Creatures.AbstractCharacter;
import Models.Creatures.Characters.Ironclad;
import Models.Dungeon.Dungeon;
import sts.Controller;
import sts.FightScene;
import sts.RoomScene;

public class Game {
    private Dungeon dungeon;
    private AbstractCharacter player;
    public RoomScene currentScene;

    public Game() {
        dungeon = new Dungeon();

        player = new Ironclad();
        player.initialize();
    }

    public void start() {
        dungeon.getCurrentRoom().start();
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public AbstractCharacter getPlayer() {
        return player;
    }
}