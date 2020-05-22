package Controller.Dungeon.Room;

import Controller.Dungeon.AbstractRoom;
import View.Main;
import View.RestScene;
import javafx.scene.media.MediaPlayer;

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

        Main.currentScene = new RestScene();
        Main.window.setScene(
                Main.currentScene);
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
