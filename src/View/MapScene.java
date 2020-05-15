package View;

import Controller.Dungeon.AbstractRoom;
import Controller.Dungeon.Dungeon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.*;


public class MapScene extends GameScene {
    int width = 1280;
    int height = 720;

    StackPane root;

    GridPane divisionUpper;

    UpperPane mapUpper;
    StackPane mapLower;

    GridPane mapDivision;

    MapPane mapCanvas; //? Pane or Canvas
    StackPane left;
    StackPane right;

    public MapScene(){
        super();
        root = (StackPane) this.getRoot();
        initialize();
    }
    @Override
    public void initialize(){
        root.setMinSize( width, height );
        divisionUpper = new GridPane();
        divisionUpper.setMinSize(width, height);
        divisionUpper.setGridLinesVisible(true);
        initializeUpper();
        initializeLowerMap();
        root.getChildren().add(divisionUpper);
    }

    private void initializeUpper()
    {
        mapUpper = new UpperPane(width, height/10);
        mapUpper.initialize();
        mapUpper.setBackground( new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)) );

        GridPane.setConstraints(mapUpper, 0,0,1,1);
        divisionUpper.getChildren().add(mapUpper);
    }
    private void initializeLowerMap(){
        mapLower = new StackPane();
        mapLower.setMinSize(width, 9*height/10 );
        mapLower.setEffect(  new DropShadow(30, Color.YELLOW));
        mapDivision = new GridPane();
        mapDivision.setMinSize( width , 9*height/10  );
        mapDivision.setGridLinesVisible(true);

        ImageView imageView = new ImageView(new Image("mapGeneral.png"));
        mapLower.getChildren().add(imageView);


        initializeLeft();
       initializeCanvas();
       initializeRight();

        mapLower.getChildren().add(mapDivision);

        GridPane.setConstraints(mapLower, 0,1,1,1);
        divisionUpper.getChildren().add(mapLower);
    }

    private void initializeRight(){

        right = new StackPane();
        right.setMinSize(width/5, 9*height/10 );
        ImageView imageView = new ImageView(new Image("legend.png"));
        imageView.setFitHeight(height*7/10.0);
        imageView.setFitWidth(width/5);
        right.getChildren().add(imageView);
        right.setAlignment( Pos.CENTER_LEFT );
        GridPane.setConstraints(right, 2,0,1,1);
        mapDivision.getChildren().add(right);


    }
    private void initializeLeft(){

        left = new StackPane();
        left.setPrefSize(width/5, 9*height/10 );
        GridPane.setConstraints(left, 0,0,1,1);
        mapDivision.getChildren().add(left);

    }
    private void initializeCanvas(){

        mapCanvas = new MapPane( width*3/5, 9*height/10 );
        mapCanvas.setMinSize(width*3/5, 9*height/10);
        GridPane.setConstraints(mapCanvas, 1,0,1,1);
        mapDivision.getChildren().add(mapCanvas);

    }
    public void draw(){

    }

}
