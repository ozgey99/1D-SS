package View;


import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class MapScene extends GameScene {

    GridPane divisionUpper;

    UpperPane mapUpper;
    StackPane mapLower;

    GridPane mapDivision;

    MapPane mapCanvas; //? Pane or Canvas
    StackPane left;
    StackPane right;

    public MapScene(){
        super();
        initialize();
    }
    @Override
    public void initialize(){
        root.setMinSize( width, height );
        divisionUpper = new GridPane();
        divisionUpper.setMinSize(width, height);
        initializeUpper();
        initializeLowerMap();
        root.getChildren().add(divisionUpper);
        addMenuExit();
    }



    private void initializeUpper()
    {

        mapUpper = new UpperPane(width, height/14);
        mapUpper.initialize();
        mapUpper.setBackground( new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)) );
        mapUpper.setPadding(new Insets(30,0,0,0));
        GridPane.setConstraints(mapUpper, 0,0,1,1);
        divisionUpper.getChildren().add(mapUpper);
    }
    private void initializeLowerMap(){
        mapLower = new StackPane();
        mapLower.setMaxSize(width, 9*height/10 );
        mapLower.setEffect(  new DropShadow( 30,Color.RED) );
        mapLower.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        mapDivision = new GridPane();
        mapDivision.setMinSize( width , 9*height/10  );

        Image mapBackground = new Image("mapGeneral.jpg");
        ImageView imageView = new ImageView(mapBackground);
        imageView.setFitHeight(37*height/40);
        imageView.setFitWidth(width);
        mapLower.getChildren().add(imageView);
        mapLower.setAlignment(Pos.CENTER);


        initializeLeft();
       initializeCanvas();
       initializeRight();

        mapLower.getChildren().add(mapDivision);

        GridPane.setConstraints(mapLower, 0,1,1,1);
        divisionUpper.getChildren().add(mapLower);
    }

    private void initializeRight(){

        right = new StackPane();
        right.setMinSize(width*3/16, 9*height/10 );
        ImageView imageView = new ImageView(new Image("legend.png"));
        imageView.setFitHeight(height*4/10);
        imageView.setFitWidth(width*2/10);
        right.getChildren().add(imageView);
        right.setAlignment( Pos.CENTER );
        GridPane.setConstraints(right, 2,0,1,1);
        mapDivision.getChildren().add(right);


    }
    private void initializeLeft(){

        left = new StackPane();
        left.setPrefSize(width*3/16, 9*height/10 );
        GridPane.setConstraints(left, 0,0,1,1);
        mapDivision.getChildren().add(left);

    }
    private void initializeCanvas(){

        mapCanvas = new MapPane( width*10/16, 9*height/10 );
        mapCanvas.setMinSize(width*10/16, 9*height/10);
        GridPane.setConstraints(mapCanvas, 1,0,1,1);
        mapDivision.getChildren().add(mapCanvas);

    }
    public void draw(){

    }

    private void addMenuExit(){
        Rectangle exitMenu = new Rectangle(90,60);
        mapUpper.getChildren().add(exitMenu);
        mapUpper.setAlignment( exitMenu,Pos.TOP_RIGHT);
        exitMenu.setOnMouseClicked( e-> returnToMenu());
        ImagePattern imageFilled = new ImagePattern(new Image("BackToHome.png"), 0, 0, 1, 1, true);
        exitMenu.setFill( imageFilled);
    }
    private void returnToMenu(){
        Main.window.setScene( new MenuScene() );
    }

}
