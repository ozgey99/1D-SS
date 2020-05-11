package View;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static View.Main.game;
public class RestScene extends RoomScene {
    Pane pane;
    HBox box;
    HBox options;
    SmithPane smithPane;
    private UpperPane gridUpper;
    ImageView turnBack;
    ImageView rest;
    ImageView smith;
    static boolean added;
    public RestScene()
    {
        super();
        pane = new Pane();
        box = new HBox();
        options = new HBox();
        turnBack = new ImageView(new Image("goAhead.png"));
        rest = new ImageView(new Image("Rest.png"));
        smith = new ImageView(new Image("Smith.png"));
        gridUpper = new UpperPane(width,height/15);
        smithPane  = new SmithPane(width/3*2 , height/9*6);
        root.setMinSize( width, height);
        added = false;
    }

    public void turnBack(){
        turnBack = new ImageView(new Image("goAhead.png"));
        turnBack.setPreserveRatio(true);
        turnBack.setFitHeight(height/7); //turnBack.setFitHeight(100);
        turnBack.setX(width/5);
        turnBack.setY(height/4*3);

        smithPane.stack.getChildren().add( turnBack );
        smithPane.stack.setAlignment(turnBack, Pos.BOTTOM_RIGHT);
        turnBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                smithPane.visible(false);
            }
        });
    }

    private void initializeUpper()
    {
        gridUpper.initialize();
        gridUpper.setBackground( new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)) );
        gridUpper.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        GridPane.setConstraints(gridUpper, 0,0,1,1);
        pane.getChildren().add(gridUpper);
        gridUpper.setMinWidth(width);
        //gridUpper.setMinHeight(height/9);

    }

    public void initialize() {
        initializeUpper();
        addBackground();
        showOptions();
        pane.getChildren().add(rest);
        pane.getChildren().add(smith);
        root.getChildren().add(pane);
        proceed();

    }

    public void proceed(){
        turnBack.setPreserveRatio(true);
        turnBack.setFitHeight(height/7);
        turnBack.setX(width/5+ width/3*2);
        turnBack.setY(height/4*3);
        pane.getChildren().add( turnBack );
        turnBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(" you are in next scene");
                game.getDungeon().ascend();
            }
        });
    }


    public void showOptions(){
        System.out.println("INITIALIZE IN REST SCENE");
        int imageWidth = width / 10;
        int imageHeight = height / 4;

        rest.setFitWidth(imageWidth);
        rest.setFitHeight(imageHeight);
        rest.setX(width/6*2);
        rest.setY(height/3);
        rest.setVisible(true);
        rest.setPreserveRatio(true);
        rest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                {
                    System.out.println("ASCENDING CALLED IN REST SCENE");
                    int hp = (game.getPlayer().getMaxHP() * 3) / 10;
                    game.getPlayer().changeHealth(hp);
                    draw();
                    rest.setDisable(true);
                    smith.setDisable(true);

                }
            }
        });


        smith.setVisible(true);
        smith.setPreserveRatio(true);
        smith.setFitWidth(imageWidth);
        smith.setFitHeight(imageHeight);
        smith.setX(width/6*2+ imageWidth);
        smith.setY(height/3);
        smith.setVisible(true);
        smith.setPreserveRatio(true);
        smith.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                {
                    smithPane.visible(true);
                    smithPane.addBackground();
                    smithPane.initialize();
                    if(!added){
                        root.getChildren().add(smithPane);
                        added = true;
                    }
                    draw();
                    turnBack();
                    rest.setDisable(true);
                    smith.setDisable(true);
                }

            }
        });

    }

    private void addBackground() {
        ImageView imageView = new ImageView(new Image("campFire.gif"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        root.getChildren().add(imageView);
    }

    public void draw()
    {
        gridUpper.draw();
    }


}
