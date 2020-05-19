package View;

import Models.Cards.AbstractCard;
import Controller.Dungeon.Room.Treasure;
import Models.Object.AbstractRelic;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.text.Text;
import Controller.Actions.RelicActions;

import java.util.ArrayList;

import static View.Main.game;

public class TreasureScene extends RoomScene {

    private UpperPane gridUpper;
    ArrayList<Rectangle> rectangles;
    ArrayList<AbstractRelic> relics;
    ImageView chest;
    ImageView tick;
    Pane pane;
    Node rect;
    static int origWidth;
    static  int origHeight;
    ImageView rewardHeader;
    ImageView rewardWarn;

    public TreasureScene()
    {
        super();
        tick = new ImageView(new Image("tick.png"));
        chest = new ImageView(new Image("SmallChest.png"));
        rewardWarn = new ImageView(new Image("rewardWarn.png"));
        rewardHeader = new ImageView(new Image("rewardHeader.png"));
        pane = new Pane();
        relics = new ArrayList<>();
        rectangles = new ArrayList<>();
        gridUpper = new UpperPane(width,height/9);

        root.setMinSize( width, height);
        origWidth = width;
        origHeight = height;

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
        System.out.println("Treasure UPP");
        gridUpper.initialize();
        gridUpper.setBackground( new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)) );
        gridUpper.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        GridPane.setConstraints(gridUpper, 0,0,1,1);
        pane.getChildren().add(gridUpper);
        gridUpper.setMinWidth(width);
    }

    private void info(){
        rewardWarn.setPreserveRatio(true);
        rewardWarn.setFitWidth(width/13.0);
        rewardWarn.setX(width/10*7-width/(106.0/10));
        rewardWarn.setY(height/6*2);
        pane.getChildren().add(rewardWarn);

    }

    static Node rewards() {

        Group g = new Group();
        VBox vbox = new VBox(origHeight/35);

        // gold reward
        HBox box1 = new HBox();
        box1.setPadding(new Insets(origHeight/30, 0, 0, 0));
        Text goldText = new Text();
        //Here is a big issue, SOLVE IT
        int gold = ((Treasure)game.getDungeon().getCurrentRoom()).getGoldAmount();
        goldText.setText(gold + " gold");
        goldText.setFill(Color.BLACK);
        goldText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        ImageView goldImage = new ImageView(new Image("gold.png"));
        goldImage.setFitWidth(origWidth/(325.0/10)); //goldImage.setFitWidth(40);
        goldImage.setFitHeight(origHeight/(175.0/10)); //goldImage.setFitHeight(40);
        goldImage.setVisible(true);
        goldImage.setPreserveRatio(true);
        box1.getChildren().addAll(goldImage, goldText);
        vbox.getChildren().add(box1);

        // card reward
        Rectangle cardRect = new Rectangle();
        ArrayList<AbstractCard> cards = ((Treasure)game.getDungeon().getCurrentRoom()).getCards();
        String label = "add a card to your deck";
        Text text = new Text(label);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, origWidth/100));
        vbox.getChildren().add(text);

        // relic reward
        ArrayList<AbstractRelic> relics = ((Treasure)game.getDungeon().getCurrentRoom()).getRelics();
        Text relicText = new Text();
        Text relicDesc = new Text();
        if(relics.size() != 0){
            String desc = relics.get(0).getDescription();
            String s = "get a relic";
            relicText.setText(s);
            relicText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, origWidth/100));
            String name = relics.get(0).getName();
            relicDesc.setText(name+" : "+ desc);
            relicDesc.setFill(Color.WHITE);
            relicDesc.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, origWidth/100));
            vbox.getChildren().add(relicText);
        }
        else{
            relicText.setText("no relic rewards"+ "\n" +"you already have all relic ");
            relicText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, origWidth/100));
        }


        // gold reward event listener
        goldImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                game.getPlayer().changeGold(((Treasure) game.getDungeon().getCurrentRoom()).getGoldAmount());
                goldImage.setEffect( new DropShadow(10, Color.RED) );
                goldText.setEffect( new DropShadow(10, Color.RED) );
                relicText.setDisable(true);
                text.setDisable(true);
                goldImage.setDisable(true);
            }
        });


        // relic reward event listener
        HBox box2 = new HBox();
        if(relics.size() != 0){
            relicText.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String name = relics.get(0).getName();
                    name = name + ".png";
                    ImageView relicImage = new ImageView(new Image(name));
                    relicImage.setPreserveRatio(true);
                    relicImage.setFitHeight(origHeight/7.0); //relicImage.setFitHeight(100);
                    //box2.setPadding(new Insets(y/2,100, 100, x/2-90 ));
                    box2.setPadding(new Insets(origHeight/2.0,origWidth/13.0, origHeight/7.0, origWidth/(232.0/100) ));
                    box2.getChildren().addAll(relicImage, relicDesc);
                    g.getChildren().add(box2);
                    relicImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            RelicActions.addRelic(game.getPlayer(),relics.get(0));
                            relicImage.setVisible(false);
                            relicDesc.setVisible(false);
                            relicText.setDisable(true);
                            goldImage.setDisable(true);
                            text.setDisable(true);

                        }
                    });
                    relicText.setEffect( new DropShadow(10, Color.RED) );
                }
            });
        }


        // card reward event listener
        text.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = cards.get(0).getName();
                name = name + ".png";
                cardRect.setX(origWidth/(232.0/100)); //cardRect.setX(x/2-90);
                cardRect.setY(origHeight/(33.0/10)); //cardRect.setY(y/2-140);
                cardRect.setFill(new ImagePattern(new Image(name)));
                cardRect.setWidth(origWidth/(65/10.0)); //cardRect.setWidth(200);
                cardRect.setHeight(origHeight/3.0); //cardRect.setHeight(250);
                cardRect.setVisible(true);
                g.getChildren().add(cardRect);
                cardRect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("I AM ADDING CARD " + cards.get(0).getName());
                        game.getPlayer().masterDeck.addCard(cards.get(0));
                        cardRect.setVisible(false);
                        text.setDisable(true);
                        goldImage.setDisable(true);
                        relicText.setDisable(true);
                        text.setDisable(true);
                    }
                });
                text.setEffect( new DropShadow(10, Color.RED) );
            }
        });

        Rectangle rect = new Rectangle();
        rect.setX(origWidth/2.0- origWidth/13.0); //rect.setX(x/2-100);
        rect.setY(origHeight/2-(origHeight/(46.0/10))); //rect.setY(y/2-150);
        rect.setFill(new ImagePattern(new Image("rewardsBack.png")));
        rect.setStroke(Color.BLACK);
        rect.setWidth(origWidth/(65.0/10)); //rect.setWidth(200);
        rect.setHeight(origHeight/(233.0/100)); //rect.setHeight(300);
        rect.setVisible(true);

        //vbox.setPadding(new Insets(y/2-130,100, 100, x/2-90));
        vbox.setPadding(new Insets(origHeight/(32.0/10),origWidth/13.0, origHeight/7.0, origWidth/(232.0/100)));

        g.getChildren().add(rect);
        g.getChildren().add(vbox);
        return g;
    }

    public void nextButton(){
        ImageView nextButton = new ImageView(new Image("goAhead.png"));
        nextButton.setPreserveRatio(true);
        nextButton.setFitHeight(height/7); //nextButton.setFitHeight(50);
        nextButton.setX(width/(14.0/10)); //((nextButton.setX(width-400);
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
        tick.setFitHeight(height/(93.0/10)); //tick.setFitHeight(75);
        tick.setX(width/2); //tick.setX(width/2-100+100); // x of node
        tick.setY(height/2.0-height/5.0+ height/(35.0/10)); //tick.setY(height/2-140+200);
        pane.getChildren().add( tick );
        tick.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rect.setVisible(false);
                rewardHeader.setVisible(false);
                tick.setVisible(false);
                draw();
                nextButton();
                rewardWarn.setVisible(false);
            }
        });
    }

    public void smallChest()
    {
        rect = rewards();
        chest.setFitWidth(width/8);
        chest.setX(width/2-width/8);
        chest.setY(height/3*2);
        chest.setVisible(true);
        chest.setPreserveRatio(true);
        chest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                {
                    info();
                    System.out.println("ASCENDING CALLED IN TREASURE SCENE");
                    pane.getChildren().add(rect);
                    chosen();
                    chest.setDisable(true);
                    root.getChildren().add(rewardHeader);
                    root.setAlignment(rewardHeader, Pos.CENTER);
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