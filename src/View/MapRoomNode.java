package View;

import Controller.Dungeon.AbstractRoom;
import Controller.Dungeon.Room.Fight;
import Controller.Dungeon.Room.RoomType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

import static View.Main.game;


public class MapRoomNode extends Circle {
    AbstractRoom nodeRoom;
    boolean active = false;
    int nodeSize;
    public MapRoomNode(AbstractRoom room, int nodeSize){
        this.nodeRoom = room;
        this.nodeSize = nodeSize;
        //active = active && room.anyRootActive();
        defineRoom();
        addRoomTransition();
       // this.setFill( Color.BLUE);
    }
    private void defineRoom(){
        checkStatus();
        String roomTypeName = ".png";
        RoomType type = nodeRoom.getType();
        if( type == RoomType.SHOP )
            roomTypeName = "merchantIcon" + roomTypeName;
        else if( type == RoomType.FIGHT && ((Fight) nodeRoom).getIsBoss() ) {
            roomTypeName = "bossIcon" + roomTypeName;
            nodeSize = 10*nodeSize/3;
        }
        else if( type == RoomType.FIGHT && ((Fight) nodeRoom).getIsElite() )
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
        this.setRadius(nodeSize);
    }
    public void checkStatus(){
        AbstractRoom currentRoom = Main.game.getDungeon().getCurrentRoom();
        if( nodeRoom == currentRoom ){
            this.setEffect(new DropShadow(30, Color.RED));
        }
        if( currentRoom.getChildren() != null ) {
            ArrayList<AbstractRoom> childrenOfCurrent = currentRoom.getChildren();
            for (int i = 0; i < childrenOfCurrent.size(); i++) {
                if (childrenOfCurrent.get(i) == nodeRoom) {
                    active = true;
                }
            }
        }
    }
    private void addRoomTransition(){
        if( active == true )
        {
            this.setOnMouseClicked( e->switchToRoom() );
        }
    }
    private void switchToRoom(){

        if((game.getDungeon().getCurrentRoom()).getType() != RoomType.FIGHT ){
           //Main.game.getDungeon().getCurrentRoom().stopMusic();
        }
        Main.game.getDungeon().setCurrentRoom(nodeRoom);
        Main.game.getDungeon().addTreasure();
        nodeRoom.start();
        //nodeRoom.addMusic();


    }
}
