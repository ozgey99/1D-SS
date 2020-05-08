package Controller;

import Models.Creatures.AbstractCharacter;
import Models.Creatures.Characters.Ironclad;
import Controller.Dungeon.Dungeon;
import View.RoomScene;
import static View.Main.game;


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

        dungeon = new Dungeon();
        player = new Ironclad();
        player.initialize();
        game.getDungeon().generate();
        dungeon.getCurrentRoom().start();
    }

    public void startMap(){
        dungeon = new Dungeon();
        player = new Ironclad();
        player.initialize();
        game.getDungeon().generate();
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public AbstractCharacter getPlayer() {
        return player;
    }
}