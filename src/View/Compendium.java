package View;

import Controller.Dungeon.Room.Merchant;
import Models.Cards.AbstractCard;
import Models.Object.AbstractRelic;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

import static View.Main.game;

public class Compendium extends RoomScene {
    Pane pane;
    VBox vbox;
    private UpperPane gridUpper;
    RemoveCard removeCardPane;
    ArrayList<AbstractCard> cards;
    ArrayList<AbstractRelic> relics;
    ArrayList<Integer> cardPrices;
    ArrayList<Integer> relicPrices;
    ImageView turnBack;
    ImageView removeButton;
    static boolean added;
    static int origWidth;
    static  int origHeight;
    ImageView back;
    final ScrollBar sc;

    public Compendium(){
        super(new StackPane());
        pane = new Pane();
        sc = new ScrollBar();
        gridUpper = new UpperPane(width,height/15);
        root.setMinSize( width, height);
        removeCardPane  = new RemoveCard(width/3*2 , height/9*6);
        removeButton = new ImageView(new Image("removeButton.png"));
        cards = ((Merchant) game.getDungeon().getCurrentRoom()).getCards();
        cardPrices = ((Merchant) game.getDungeon().getCurrentRoom()).getCardPrices();
        relics = ((Merchant) game.getDungeon().getCurrentRoom()).getRelics();
        relicPrices = ((Merchant) game.getDungeon().getCurrentRoom()).getRelicPrices();
        added = false;

        back = new ImageView(new Image("up.png"));
        pane = new Pane();
        //pane.setPadding(new Insets(150,400,300,250));
        pane.setPrefSize(250, 150);
        pane.setPrefHeight(400);
        pane.setPrefWidth(650);
        vbox = new VBox(height/70);
        vbox.setPadding(new Insets(height/(46.0/10),width/(32.0/10),height/(46.0/10),width/(52.0/10)));
        //vbox.setPadding(new Insets(150,400,100,250));
        root.setMinSize( width, height);
        root.getChildren().add(back);
        root.getChildren().add(pane);
    }
    @Override
    public void draw() {

    }

    @Override
    public void initialize() {
        initializeUpper();
        addBackground();
        cards();
    }

    public void cards(){
        Group group = new Group();
        sc.setMin(0);
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(height-20);
        sc.setMax(360);


        group.getChildren().addAll(vbox, sc);
        pane.getChildren().addAll(group);
        //pane.getChildren().add(vbox);
        sc.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                vbox.setLayoutY(-new_val.doubleValue());
            }
        });

        System.out.println("you have "+ game.getPlayer().masterDeck.getSize() + "card");
        vbox.getChildren().removeAll();
        vbox.getChildren().clear();
        int size = game.getPlayer().masterDeck.getSize();
        int paneSize = size/7;
        if(size % 7 != 0)
            paneSize++;

        for (int i = 0; i < size; i++){
            HBox hbox = new HBox();
            hbox.getChildren().clear();
            for(int count = 0; count < 7; count++ ){
                String name = game.getPlayer().masterDeck.getCard(i).getName();
                System.out.println(name);
                name = name + ".png";
                Rectangle rect = new Rectangle();
                rect.setFill(new ImagePattern(new Image(name)));
                rect.setWidth(width/13);
                rect.setHeight(height/(46/10));
                rect.setVisible(true);
                int price = 0; // şimdilik
                int j = i;
                rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        if (game.getPlayer().getGold() >= price) {
                            System.out.println("gold before: "+ game.getPlayer().getGold());
                            game.getPlayer().changeGold(-price);

                            System.out.println("gold after :" + game.getPlayer().getGold());
                            game.getPlayer().masterDeck.removeCard(game.getPlayer().masterDeck.getCard(j));
                            draw();
                            System.out.println("========AFTER CLICK MASTER DECK=========");
                            for(int k=0; k<game.getPlayer().masterDeck.getSize(); k++){
                                System.out.println(game.getPlayer().masterDeck.getCard(k).getName());
                            }

                        } else {
                            System.out.println("You don't have enough gold for card");
                        }

                    }
                });

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
                if(count != 6)
                    i++;
                if(i == size)
                    break;
            }
            vbox.getChildren().add(hbox);

        }
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

    public void addBackground() {
        back.setFitWidth(width);
        back.setFitHeight(height);
        back.setOpacity(0.80);

    }
}
