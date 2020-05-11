package View;

import Controller.Dungeon.AbstractRoom;
import Controller.Dungeon.Dungeon;
import Controller.Dungeon.Room.Fight;
import Controller.Dungeon.Room.Merchant;
import Controller.Dungeon.Room.RoomType;
import Controller.Dungeon.Room.Treasure;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.IllegalFormatException;

import static View.Main.game;

public class MapRoomNode extends Circle {
    AbstractRoom currentRoom;
    boolean active;
    public MapRoomNode(AbstractRoom room){
        this.currentRoom = room;
        active = !(room.getDone());
        //active = active && room.anyRootActive();
        defineRoom();
        addRoomTransition();
       // this.setFill( Color.BLUE);
    }
    private void defineRoom(){

        int nodeRadius = 20;
        String roomTypeName = ".png";
        RoomType type = currentRoom.getType();
        if( type == RoomType.SHOP )
            roomTypeName = "merchantIcon" + roomTypeName;
        else if( type == RoomType.FIGHT && ((Fight)currentRoom).getIsBoss() ) {
            roomTypeName = "bossIcon" + roomTypeName;
            nodeRadius = 50;
        }
        else if( type == RoomType.FIGHT && ((Fight)currentRoom).getIsElite() )
            roomTypeName = "eliteIcon" + roomTypeName;
        else if( type == RoomType.FIGHT  )
            roomTypeName = "monsterIcon" + roomTypeName;
        else if( type == RoomType.CHEST )
            roomTypeName = "chestIcon" + roomTypeName;
        else if( type == RoomType.REST )
            roomTypeName = "restIcon" + roomTypeName;

        else
            throw new IllegalArgumentException("Undefined Room");

        ImagePattern imageFilled = new ImagePattern(new Image(roomTypeName), 0, 0, 1, 1, true);
        this.setFill(imageFilled );
        this.setRadius(nodeRadius);
    }
    private void addRoomTransition(){
        if( active == true )
        {
            this.setOnMouseClicked( e->switchToRoom() );
        }
    }
    private void switchToRoom(){
        //Implement switch function
        currentRoom.start();
    }
}
