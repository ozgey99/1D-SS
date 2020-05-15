package View;

import Models.Cards.AbstractCard;
import Models.Cards.CardColor;
import Models.Utils;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

import static View.Main.game;

public class CardCollectionScene extends GameScene {
    Pane redPane;
    Pane colorlessPane;
    VBox redvbox;
    VBox colorlessvbox;

    //ArrayList<AbstractCard> cards;
    ArrayList<AbstractCard> redColor;
    ArrayList<AbstractCard> colorless;
    ImageView turnBack;

    ImageView back;
    ImageView ss_back;
    ScrollBar sc;

    public CardCollectionScene(){
        super();
        redPane = new Pane();
        colorlessPane = new Pane();
        sc = new ScrollBar();

        redColor = Utils.getAllCardsOfColor(CardColor.RED);
        colorless = Utils.getAllCardsOfColor(CardColor.COLORLESS);
        ss_back = new ImageView(new Image("ss_back.jpg"));
        back = new ImageView(new Image("up.png"));

        redPane.setPrefSize(250, 150);
        redPane.setPrefHeight(height/(18.0/10));
        redPane.setPrefWidth(width/2);

        colorlessPane.setPrefSize(250, 150);
        colorlessPane.setPrefHeight(400);
        colorlessPane.setPrefWidth(650);

        redvbox = new VBox(height/70);
        redvbox.setPadding(new Insets(height/(46.0/10),width/(32.0/10),height/(46.0/10),width/(52.0/10)));

        colorlessvbox = new VBox(height/70);
        colorlessvbox.setPadding(new Insets(height/(46.0/10),width/(32.0/10),height/(46.0/10),width/(52.0/10)));

        root.setMinSize( width, height);
        root.getChildren().add(ss_back);
        root.getChildren().add(back);
        root.getChildren().add(redPane);
        //root.getChildren().add(colorlessPane);
    }
    @Override
    public void draw() {

    }

    @Override
    public void initialize() {
        addBackground();
        cards();
        tab();
        turnBack();
    }

    public void tab(){
        TabPane tabpane = new TabPane();
        tabpane.setTabMinHeight(20);
        tabpane.setTabMinWidth(200);
        tabpane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        // create multiple tabs
        String[] types = {"RED", "GREEN", "BLUE", "COLORLESS", "CURSES"};
        /* TabPane tabPane = new TabPane();

        Tab tab1 = new Tab("Planes", new Label("Show all planes available"));
        Tab tab2 = new Tab("Cars"  , new Label("Show all cars available"));
        Tab tab3 = new Tab("Boats" , new Label("Show all boats available"));

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);

        VBox vBox = new VBox(tabPane);
        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX App");

        primaryStage.show();*/
        for (int i = 0; i < 5; i++) {
            Tab tab = new Tab(types[i]);
            if( i == 0)
                tab.setContent(redPane);
            if( i == 3){
                //tab.setContent(colorlessPane); // redPane ekleyince, red tab'taki gözükmüyor
            }
            tabpane.getTabs().add(tab);
        }
        root.getChildren().add(tabpane);
        root.setAlignment(tabpane, Pos.CENTER);
    }

    public void turnBack(){
        turnBack = new ImageView(new Image("turnBack.png"));
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

    public void cards(){
        redCards();
        //colorlessCards();
    }

    public void colorlessCards(){
        Group group = new Group();
        sc.setMin(0);
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(height-height/15);
        sc.setPrefWidth(20);
        sc.setMax(10000);
        group.getChildren().addAll(colorlessvbox, sc);
        colorlessPane.getChildren().addAll(group);
        sc.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                colorlessvbox.setLayoutY(-new_val.doubleValue());
            }
        });

        colorlessvbox.getChildren().removeAll();
        colorlessvbox.getChildren().clear();

        int size = colorless.size();
        System.out.println("colorless card size: "+ size);
        for (int i = 0; i < size; i++){
            HBox hbox = new HBox();
            hbox.getChildren().clear();
            for(int count = 0; count < 8; count++ ){
                String name = colorless.get(i).getName();
                System.out.println(name);
                name = name + ".png";
                Rectangle rect = new Rectangle();
                rect.setFill(new ImagePattern(new Image(name)));
                rect.setWidth(width/13);
                rect.setHeight(height/(46/10));
                rect.setVisible(true);

                rect.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), rect);
                        scaleTransition.setToX(1.5f);
                        scaleTransition.setToY(1.5f);
                        scaleTransition.setCycleCount(1);
                        scaleTransition.setAutoReverse(true);
                        scaleTransition.play();
                    }
                });

                rect.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), rect);
                        scaleTransition.setToX(1);
                        scaleTransition.setToY(1);
                        scaleTransition.setAutoReverse(true);
                        scaleTransition.play();
                    }
                });

                hbox.getChildren().add(rect);
                if(count != 7)
                    i++;
                if(i == size)
                    break;
            }
            colorlessvbox.getChildren().add(hbox);

        }
    }

    public void redCards(){
        Group group = new Group();
        sc.setMin(0);
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(height-height/15);
        sc.setPrefWidth(width/64.0);
        sc.setMax(10000);
        group.getChildren().addAll(redvbox, sc);
        redPane.getChildren().addAll(group);
        sc.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                redvbox.setLayoutY(-new_val.doubleValue());
            }
        });

        redvbox.getChildren().removeAll();
        redvbox.getChildren().clear();

        int size = redColor.size();

        for (int i = 0; i < size; i++){
            HBox hbox = new HBox();
            hbox.getChildren().clear();
            for(int count = 0; count < 8; count++ ){
                String name = redColor.get(i).getName();
                System.out.println(name);
                name = name + ".png";
                Rectangle rect = new Rectangle();
                rect.setFill(new ImagePattern(new Image(name)));
                rect.setWidth(width/(128.0/10));
                rect.setHeight(height/(46/10));
                rect.setVisible(true);

                rect.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), rect);
                        scaleTransition.setToX(1.5f);
                        scaleTransition.setToY(1.5f);
                        scaleTransition.setCycleCount(1);
                        scaleTransition.setAutoReverse(true);
                        scaleTransition.play();
                    }
                });

                rect.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), rect);
                        scaleTransition.setToX(1);
                        scaleTransition.setToY(1);
                        scaleTransition.setAutoReverse(true);
                        scaleTransition.play();
                    }
                });

                hbox.getChildren().add(rect);
                if(count != 7)
                    i++;
                if(i == size)
                    break;
            }
            redvbox.getChildren().add(hbox);

        }
    }


    public void addBackground() {
        back.setFitWidth(width);
        back.setFitHeight(height);
        back.setOpacity(0.90);

    }
}
