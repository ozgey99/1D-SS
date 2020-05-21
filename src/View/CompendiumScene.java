package View;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

import static View.Main.game;

public class CompendiumScene extends GameScene {
    Pane pane;
    HBox box;
    HBox options;
    SmithPane smithPane;
    ImageView turnBack;
    ImageView cardLibrary;
    ImageView relicCollection;
    ImageView back;
    ImageView ss_back;
    static boolean added;

    public CompendiumScene(){
        super();
        pane = new Pane();
        box = new HBox(100);
        options = new HBox();
        turnBack = new ImageView(new Image("goAhead.png"));
        ss_back = new ImageView(new Image("ss_back.jpg"));
        cardLibrary = new ImageView(new Image("cardLibrary.png"));
        relicCollection = new ImageView(new Image("relicCollection.png"));
        back = new ImageView(new Image("up.png"));
        root.setMinSize( width, height);
        added = false;
        initialize();
    }
    @Override
    public void draw() {

    }

    public void  cardCollection(){
        game.currentScene = new CardCollectionScene();
        Main.window.setScene(
                game.currentScene);
        game.currentScene.initialize();
    }

    public void relicLibrary(){
        game.currentScene = new RelicLibraryScene();
        Main.window.setScene(
                game.currentScene);
        game.currentScene.initialize();
    }

    @Override
    public void initialize() {
        addBackground();
        showOptions();
        pane.getChildren().add(cardLibrary);
        pane.getChildren().add(relicCollection);
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
                game.currentScene = new MenuScene();
                Main.window.setScene(
                        game.currentScene);
                game.currentScene.initialize();
            }
        });
    }

    public void showOptions(){
        System.out.println("INITIALIZE IN REST SCENE");
        int imageWidth = width / 6;
        int imageHeight = height / 2;

        cardLibrary.setFitWidth(imageWidth);
        cardLibrary.setFitHeight(imageHeight);
        cardLibrary.setX(width/6*2);
        cardLibrary.setY(height/3);
        cardLibrary.setVisible(true);
        cardLibrary.setPreserveRatio(true);
        cardLibrary.setOnMouseClicked( e -> {
                {
                    draw();
                    cardCollection();

                }
            }
        );

        cardLibrary.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, e -> {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), cardLibrary);
                scaleTransition.setToX(1.25f);
                scaleTransition.setToY(1.25f);
                scaleTransition.setCycleCount(1);
                scaleTransition.setAutoReverse(true);
                scaleTransition.play();
            }
        );

        cardLibrary.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, e -> {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), cardLibrary);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                scaleTransition.setAutoReverse(true);
                scaleTransition.play();
            }
        );


        relicCollection.setVisible(true);
        relicCollection.setPreserveRatio(true);
        relicCollection.setFitWidth(imageWidth);
        relicCollection.setFitHeight(imageHeight);
        relicCollection.setX(width/6*2+ imageWidth);
        relicCollection.setY(height/3);
        relicCollection.setVisible(true);
        relicCollection.setPreserveRatio(true);
        relicCollection.setOnMouseClicked( e -> {
                {
                    draw();
                    relicLibrary();
                }

            }
        );

        relicCollection.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, e ->  {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), relicCollection);
                scaleTransition.setToX(1.25f);
                scaleTransition.setToY(1.25f);
                scaleTransition.setCycleCount(1);
                scaleTransition.setAutoReverse(true);
                scaleTransition.play();
            }
        );

        relicCollection.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, e -> {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), relicCollection);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                scaleTransition.setAutoReverse(true);
                scaleTransition.play();
            }
        );

    }

    public void addBackground() {
        ss_back.setFitWidth(width);
        ss_back.setFitHeight(height);
        back.setFitWidth(width);
        back.setFitHeight(height);
        back.setOpacity(0.90);
        root.getChildren().add(ss_back);
        root.getChildren().add(back);
    }
}