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
        AbstractRoom room;
        room  = Main.game.getDungeon().getCurrentRoom();
        recurseRooms(room , 0,0);
    }
    private void recurseRooms( AbstractRoom room, int translationX, int translationY ){
        int spacing = 50;
        if( room != null ) {
            int beginningX = width/2;
            int beginningY = height*14/15;
            int posX = beginningX - translationX* spacing;
            int posY = beginningY - translationY * spacing;
            System.out.println("The added room is: " + room.getType());
            MapRoomNode roomNode = new MapRoomNode(room);
            roomNode.setCenterX(posX);
            roomNode.setCenterY(posY);
            this.getChildren().add(roomNode);
            if( room.getChildren() != null )
            {
                int branchingSize = room.getChildren().size();
                int posSuccessorY = beginningY - (translationY + 1) * spacing;
                if( !( room.getChildren().get(0).isUnion() ) ) {

                    for (int i = 0; i < branchingSize; i++) {
                        int posSuccessorX = beginningX - (translationX + i) * spacing;
                        Line passage = new Line(posX, posY, posSuccessorX, posSuccessorY);
                        passage.setStroke(Color.RED);
                        passage.setEffect(new DropShadow(30, Color.YELLOW));
                        this.getChildren().add(passage);
                        recurseRooms(room.getChildren().get(i), translationX + i, translationY + 1);
                    }
                }
                else{
                    for (int i = 0; i < branchingSize; i++) {
                        int posSuccessorX = beginningX;
                        Line passage = new Line(posX, posY, posSuccessorX, posSuccessorY);
                        passage.setStroke(Color.RED);
                        passage.setEffect(new DropShadow(30, Color.YELLOW));
                        this.getChildren().add(passage);
                        recurseRooms(room.getChildren().get(i), 0, translationY + 1);
                    }
                }
            }
        }
    }
}
