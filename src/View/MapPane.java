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
    int nodeSize;
    public MapPane( int width, int height){

        this.width = width;
        this.height = height;
        nodeSize = (int) Math.sqrt(Math.pow(width,2)+ Math.pow(height,2))/100;
        //this.setBackground( new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)) );
        sketch();
        this.setVisible(true);
    }
    void sketch(){
        AbstractRoom broom;
        broom  = Main.game.getDungeon().getBeginningRoom();
        recurseRooms(broom , 0,0);
    }
    private void recurseRooms( AbstractRoom room, int translationX, int translationY ){
        int spacing = 5*nodeSize;
        if( room != null ) {
            int beginningX = width/2;
            int beginningY = height*14/15;
            int posX = beginningX - translationX* spacing;
            int posY = beginningY - translationY * spacing;
            MapRoomNode roomNode = new MapRoomNode(room, nodeSize );
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
