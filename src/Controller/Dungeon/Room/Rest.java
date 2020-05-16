package Controller.Dungeon.Room;

import Controller.Dungeon.AbstractRoom;
import View.Main;
import View.RestScene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import static View.Main.game;

import java.nio.file.Paths;
import java.util.ArrayList;


public class Rest extends AbstractRoom {

    MediaPlayer mediaPlayer;

    public Rest(ArrayList<AbstractRoom> c) {
        type = RoomType.REST;
        children = c;
        done = false;
    }

    @Override
    public void start() {

        game.currentScene = new RestScene();
        Main.window.setScene(
                game.currentScene);
    }


    @Override
    public RoomType getType() {
        return type;
    }

    @Override
    public ArrayList<AbstractRoom> getChildren() {
        return children;
    }

    @Override
    public boolean getDone() {
        return done;
    }
}
