package View;

import Controller.Dungeon.AbstractRoom;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class MapPane extends BorderPane {
    int width;
    int height;
    public MapPane( int width, int height){
        this.width = width;
        this.height = height;
        //this.setBackground( new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)) );
        sketch();
        this.setVisible(true);

    }
    void sketch(){
        Main.game.startMap();
        AbstractRoom room;
        room  = Main.game.getDungeon().getCurrentRoom();
        recurse(room , 1,1);
    }
    private void recurse( AbstractRoom room, int translationX, int translationY ){
        if( room != null ) {
            int beginningX = width/2;
            int beginningY = height*14/15;
            int posX = beginningX - translationX* 100;
            int posY = beginningY - translationY * 100;
            System.out.println("The added room is: " + room.getType());
            MapRoomNode roomNode = new MapRoomNode(room);
            roomNode.setCenterX(posX);
            roomNode.setCenterY(posY);
            this.getChildren().add(roomNode);
            if( room.getChildren() != null )
            {
                int branchingSize = room.getChildren().size();
                for (int i = 0; i < branchingSize; i++) {
                    int posSuccessorX = beginningX-(translationX+i)*100;
                    int posSuccessorY = beginningY - (translationY+1) * 100;
                    Line passage = new Line(posX, posY, posSuccessorX, posSuccessorY );
                    passage.setStroke(Color.RED);
                    passage.setEffect(new DropShadow(30, Color.YELLOW));
                    this.getChildren().add(passage);
                    recurse(room.getChildren().get(i), translationX+i,translationY+1);
                }
            }
        }
    }
}
