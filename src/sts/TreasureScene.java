package sts;

import Models.Cards.AbstractCard;
import Models.Dungeon.Room.Treasure;
import Models.Object.AbstractRelic;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.text.Text;
import Models.Actions.RelicActions;

import java.util.ArrayList;

import static sts.Main.game;

public class TreasureScene extends RoomScene {

    private UpperPane gridUpper;
    ArrayList<Rectangle> rectangles;
    ArrayList<AbstractRelic> relics;
    ImageView chest;
    ImageView tick;
    Pane pane;
    Node rect;

    public TreasureScene()
    {
        tick = new ImageView(new Image("tick.png"));
        chest = new ImageView(new Image("SmallChest.png"));
        pane = new Pane();
        relics = new ArrayList<>();
        rectangles = new ArrayList<>();
        gridUpper = new UpperPane(width,height/15);
        rect = rewards(width, height);
        root.setMinSize( width, height);
    }

    public void initialize()
    {
        initializeUpper();
        addBackground();
        smallChest();
        root.getChildren().add(pane);
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
    }

    static Node rewards(int x, int y) {

        Group g = new Group();
        VBox vbox = new VBox(10);

        // gold reward
        HBox box1 = new HBox();
        Text goldText = new Text();
        int gold = ((Treasure)game.getDungeon().getCurrentRoom()).getGoldAmount();
        goldText.setText(gold + " gold");
        goldText.setFill(Color.WHITE);
        goldText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        ImageView goldImage = new ImageView(new Image("gold.png"));
        goldImage.setFitWidth(40);
        goldImage.setFitHeight(40);
        goldImage.setVisible(true);
        goldImage.setPreserveRatio(true);
        goldImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                game.getPlayer().changeGold(((Treasure) game.getDungeon().getCurrentRoom()).getGoldAmount());
                goldImage.setVisible(false);
                goldText.setVisible(false);
            }
        });
        box1.getChildren().addAll(goldImage, goldText);
        vbox.getChildren().add(box1);


        // relic reward
        HBox box2 = new HBox();
        ArrayList<AbstractRelic> relics = ((Treasure)game.getDungeon().getCurrentRoom()).getRelics();
        String desc = relics.get(0).getDescription();
        String s = "get a relic";
        Text relicText = new Text(s);

        String name = relics.get(0).getName();
        Text relicDesc = new Text();
        relicDesc.setText(name+" : "+ desc);
        relicDesc.setFill(Color.WHITE);
        relicDesc.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        vbox.getChildren().add(relicText);
        relicText.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = relics.get(0).getName();
                name = name + ".png";
                ImageView relicImage = new ImageView(new Image(name));
                relicImage.setPreserveRatio(true);
                relicImage.setFitHeight(100);
                //relicImage.setX(x/2-90);
                //relicImage.setY(y/2-140);
                box2.setPadding(new Insets(y/2,100, 100, x/2-90 ));
                box2.getChildren().addAll(relicImage, relicDesc);
                g.getChildren().add(box2);
                relicImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        RelicActions.addRelic(game.getPlayer(),relics.get(0));
                        relicImage.setVisible(false);
                        relicText.setDisable(true);
                        relicDesc.setVisible(false);
                    }
                });

            }
        });

        // card reward
        Rectangle cardRect = new Rectangle();
        ArrayList<AbstractCard> cards = ((Treasure)game.getDungeon().getCurrentRoom()).getCards();
        String label = "add a card to your deck";
        Text text = new Text(label);
        vbox.getChildren().add(text);
        text.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = cards.get(0).getName();
                name = name + ".png";
                cardRect.setX(x/2-90);
                cardRect.setY(y/2-140);
                cardRect.setFill(new ImagePattern(new Image(name)));
                cardRect.setWidth(200); // proportional yap
                cardRect.setHeight(250); //
                cardRect.setVisible(true);
                g.getChildren().add(cardRect);
                cardRect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        game.getPlayer().masterDeck.addCard(cards.get(0));
                        cardRect.setVisible(false);
                        text.setDisable(true);
                    }
                });
            }
        });

        Rectangle rect = new Rectangle();
        rect.setX(x/2-100);
        rect.setY(y/2-150);
        rect.setFill(Color.GREY);
        rect.setStroke(Color.BLACK);
        rect.setWidth(200); // proportional yap
        rect.setHeight(300); //
        rect.setVisible(true);

        vbox.setPadding(new Insets(y/2-130,100, 100, x/2-90));

        g.getChildren().add(rect);
        g.getChildren().add(vbox);

        return g;
    }


    public void nextButton(){
        ImageView nextButton = new ImageView(new Image("nextButton.png"));
        nextButton.setPreserveRatio(true);
        nextButton.setFitHeight(50);
        nextButton.setX(width-400);
        nextButton.setY(height/7*5);
        pane.getChildren().add( nextButton );
        nextButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                game.getDungeon().ascend();
            }
        });
    }

    public void chosen(){
        tick.setPreserveRatio(true);
        tick.setFitHeight(75);
        tick.setX(width/2); //tick.setX(width/2-100+100); // x of node
        tick.setY(height/2+60); //tick.setY(height/2-140+200);
        pane.getChildren().add( tick );
        tick.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rect.setVisible(false);
                tick.setVisible(false);
                draw();
                nextButton();
            }
        });
    }


    public void smallChest()
    {
        chest.setFitWidth(width/8);
        chest.setX(width/2-width/8);
        chest.setY(height/3*2);
        chest.setVisible(true);
        chest.setPreserveRatio(true);
        chest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                {
                    System.out.println("ASCENDING CALLED IN TREASURE SCENE");
                    pane.getChildren().add(rect);
                    chosen();
                    chest.setDisable(true);
                }

            }
        });
        pane.getChildren().add(chest);

    }

    private void addBackground() {
        ImageView imageView = new ImageView(new Image("treasure_back.jpg"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        root.getChildren().add(imageView);
    }

    public void draw()
    {
        gridUpper.draw();
    }

}