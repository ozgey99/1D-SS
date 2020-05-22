package Controller;

import Models.Creatures.AbstractCharacter;
import Models.Creatures.Characters.Ironclad;
import Controller.Dungeon.Dungeon;
import View.GameScene;
import View.RoomScene;
import javafx.scene.Scene;

import java.io.Serializable;

import static View.Main.game;


public class Game implements Serializable {
    private Dungeon dungeon;
    private AbstractCharacter player;

    public Game() {
        dungeon = new Dungeon();

        player = new Ironclad();
        player.initialize();
    }

  /*  public void start() {

        dungeon = new Dungeon();
        player = new Ironclad();
        player.initialize();
        game.getDungeon().generate();
        dungeon.getCurrentRoom().start();
    }
*/
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