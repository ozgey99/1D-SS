package View;

import Controller.Dungeon.AbstractRoom;
import Controller.Dungeon.Dungeon;
import Controller.Dungeon.Room.RoomType;
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

public class MapRoomNode extends Circle {
    RoomType roomType;
    int nodeRadius = 50;
    AbstractRoom room;
    boolean active;
    public MapRoomNode(AbstractRoom room){
        this.setRadius(nodeRadius);
        this.room = room;
        roomType = room.getType();
        active = !(room.getDone());
        //active = active && room.anyRootActive();
        defineRoom();
        addRoomTransition();
       // this.setFill( Color.BLUE);
    }
    private void defineRoom(){
        String roomTypeName = ".png";
        if( roomType == RoomType.SHOP )
            roomTypeName = "merchantIcon" + roomTypeName;
        else if( roomType == RoomType.FIGHT )
            roomTypeName = "monsterIcon" + roomTypeName;
        else if( roomType == RoomType.CHEST )
            roomTypeName = "chestIcon" + roomTypeName;
        else if( roomType == RoomType.REST )
            roomTypeName = "restIcon" + roomTypeName;
        else
            throw new IllegalArgumentException("Undefined Room");

        ImagePattern imageFilled = new ImagePattern(new Image(roomTypeName), 0, 0, 1, 1, true);
        this.setFill(imageFilled );
    }
    private void addRoomTransition(){
        if( active == true )
        {
            this.setOnMouseClicked( e->switchToRoom() );
        }
    }
    private void switchToRoom(){
        //Implement switch function
        room.start();
    }
}
