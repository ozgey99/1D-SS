package View;

import Models.Object.AbstractRelic;
import Models.Utils;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;


import static View.Main.game;

public class RelicLibraryScene extends GameScene {
    Pane pane;
    VBox vbox;
    HBox hbox;

    ArrayList<AbstractRelic> relics;
    ImageView turnBack;
    ImageView back;
    ImageView ss_back;
    ScrollBar sc;

    public RelicLibraryScene(){
        super();
        pane = new Pane();
        hbox = new HBox();
        sc = new ScrollBar();
        relics = Utils.getAllRelics();
        turnBack = new ImageView(new Image("turnBack.png"));
        ss_back = new ImageView(new Image("ss_back.jpg"));
        back = new ImageView(new Image("up.png"));

        vbox = new VBox(height/70);
        vbox.setPadding(new Insets(height/(46.0/10),width/(32.0/10),height/(46.0/10),width/(52.0/10)));

        root.setMinSize( width, height);
        root.getChildren().add(ss_back);
        root.getChildren().add(back);
        root.getChildren().add(pane);
    }

    public void relicLib(){
        Group group = new Group();
        sc.setMin(0);
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(height-height/15);
        sc.setPrefWidth(width/64.0);
        sc.setMax(10000);
        group.getChildren().addAll(vbox, sc);
        pane.getChildren().addAll(group);
        sc.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                vbox.setLayoutY(-new_val.doubleValue());
            }
        });

        vbox.getChildren().removeAll();
        vbox.getChildren().clear();

        int size = relics.size();

        for (int i = 0; i < size; i++){
            HBox hbox = new HBox(width/16);
            hbox.getChildren().clear();
            for(int count = 0; count < 8; count++ ){
                String name = relics.get(i).getName();
                System.out.println(name);
                name = name + ".png";
                ImageView relicImage = new ImageView(new Image(name));
                relicImage.setPreserveRatio(true);
                relicImage.setFitHeight(height/14); //relicImage.setFitHeight(50);
                pane.getChildren().add( relicImage );

                relicImage.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), relicImage);
                        scaleTransition.setToX(1.5f);
                        scaleTransition.setToY(1.5f);
                        scaleTransition.setCycleCount(1);
                        scaleTransition.setAutoReverse(true);
                        scaleTransition.play();
                    }
                });

                relicImage.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), relicImage);
                        scaleTransition.setToX(1);
                        scaleTransition.setToY(1);
                        scaleTransition.setAutoReverse(true);
                        scaleTransition.play();
                    }
                });

                hbox.getChildren().add(relicImage);
                if(count != 7)
                    i++;
                if(i == size)
                    break;
            }
            vbox.getChildren().add(hbox);

        }
    }

    @Override
    public void initialize() {
        addBackground();
        relicLib();
        turnBack();
    }

    @Override
    public void draw() {

    }

    public void turnBack(){
        turnBack.setPreserveRatio(true);
        turnBack.setFitHeight(height/7); //turnBack.setFitHeight(100);
        turnBack.setX(width/5);
        turnBack.setY(height/4*3);

        root.getChildren().add( turnBack );
        root.setAlignment(turnBack, Pos.BOTTOM_LEFT);
        turnBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                game.currentScene = new CompendiumScene();
                Main.window.setScene(
                        game.currentScene);
            }
        });
    }

    public void addBackground() {
        back.setFitWidth(width);
        back.setFitHeight(height);
        back.setOpacity(0.90);
        ss_back.setFitWidth(width);
        ss_back.setFitHeight(height);
    }
}
