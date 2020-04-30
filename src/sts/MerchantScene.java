package sts;

import javafx.event.EventHandler;
import Models.Cards.AbstractCard;
import Models.Dungeon.Room.Merchant;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import Models.Cards.Deck;
import Models.Creatures.AbstractCharacter;
import Models.Game;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.util.Duration;
import Models.Object.AbstractRelic;

import java.util.ArrayList;

import static sts.Main.game;
import Models.Actions.RelicActions;

public class MerchantScene extends RoomScene  {
    Pane pane;
    private UpperPane gridUpper;

    ArrayList<AbstractCard> cards;
    ArrayList<AbstractRelic> relics;
    ArrayList<Integer> cardPrices;
    ArrayList<Integer> relicPrices;
    ImageView warning;
    boolean destroy;

    public MerchantScene() {
        pane = new Pane();
        gridUpper = new UpperPane(width,height/15);
        root.setMinSize( width, height);
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

    @Override
    public void draw() {

    }

    @Override
    public void initialize(){
        cards = ((Merchant) game.getDungeon().getCurrentRoom()).getCards();
        cardPrices = ((Merchant) game.getDungeon().getCurrentRoom()).getCardPrices();
        relics = ((Merchant) game.getDungeon().getCurrentRoom()).getRelics();
        relicPrices = ((Merchant) game.getDungeon().getCurrentRoom()).getRelicPrices();
        warning = new ImageView(new Image("warning.png"));
        initializeUpper();
        addBackground();
        shopCards();
        shopRelics();
        root.getChildren().add(pane);
    }

    public void warning(int x, int y, boolean destroy,ImageView warning){
        if(!destroy){
            warning.setPreserveRatio(true);
            warning.setX(x);
            warning.setY(y);
            warning.setFitWidth(100);
            pane.getChildren().add( warning );
        }
        else
            pane.getChildren().remove(warning);
    }

    public void shopCards(){
        int space = width/4;

        boolean saleAdded = false;
        int rand = (int) (Math.random() * 5);

        System.out.println("========INITIAL MASTER DECK=========");
        for(int k=0; k<game.getPlayer().masterDeck.getSize(); k++){
            System.out.println(game.getPlayer().masterDeck.getCard(k).getName());
        }
        System.out.println("========INITIAL MASTER DECK=========");

        cards = ((Merchant) game.getDungeon().getCurrentRoom()).getCards();

        for (int i = 0; i < cards.size(); i++){
            int price = cardPrices.get(i);
            String name = cards.get(i).getName();
            name = name + ".png";
            Rectangle rect = new Rectangle();
            rect.setFill(new ImagePattern(new Image(name)));
            rect.setX(space);
            rect.setY(height/5);
            rect.setWidth(100);
            rect.setHeight(150);
            rect.setVisible(true);
            Text saleText = new Text("SALE");
            if(!saleAdded && rand == i ){
                saleAdded = true;
                price = price/2;
                saleText.setX(space+15);
                saleText.setY(height/5-5);
                saleText.setFont(Font.font ("family", 30));
                saleText.setFill(Color.RED);
                pane.getChildren().add(saleText);
            }
            pane.getChildren().add(rect);

            ImageView cost = new ImageView(new Image("cost.png"));
            cost.setPreserveRatio(true);
            cost.setFitHeight(40);
            cost.setX(space+10);
            cost.setY(height/5+140);
            pane.getChildren().add(cost);
            Text text = new Text(" " +price);
            text.setX(space+40);
            text.setY(height/5+170);
            text.setFont(Font.font ("Verdana", 15));
            text.setFill(Color.WHITE);
            pane.getChildren().add(text);

            int warningLocX = space;
            int warningLocY = height/5;
            boolean sale_added = saleAdded;
            int cardPrice = price;
            int j = i;
            rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {

                    if (game.getPlayer().getGold() >= cardPrice) {
                        System.out.println("gold before: "+ game.getPlayer().getGold());
                        game.getPlayer().changeGold(-cardPrice);
                        gridUpper.draw();
                        System.out.println("gold after :" + game.getPlayer().getGold());
                        game.getPlayer().masterDeck.addCard(cards.get(j));
                        pane.getChildren().remove(rect);
                        pane.getChildren().remove(text);
                        pane.getChildren().remove(cost);
                        if(sale_added == true){
                            pane.getChildren().remove(saleText);
                            System.out.println();
                        }


                        System.out.println("========AFTER CLICK MASTER DECK=========");
                        for(int k=0; k<game.getPlayer().masterDeck.getSize(); k++){
                            System.out.println(game.getPlayer().masterDeck.getCard(k).getName());
                        }

                    } else {
                        destroy = false;
                        warning(warningLocX,warningLocY,false, warning);
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
                    ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(200), cost);
                    scaleTransition1.setToX(1.5f);
                    scaleTransition1.setToY(1.5f);
                    scaleTransition1.setCycleCount(1);
                    scaleTransition1.setAutoReverse(true);
                    scaleTransition1.play();
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
                    ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(200), cost);
                    scaleTransition1.setToX(1);
                    scaleTransition1.setToY(1);
                    scaleTransition1.setAutoReverse(true);
                    scaleTransition1.play();
                    if(destroy == false){
                        warning(warningLocX,warningLocY,true, warning);
                    }

                }
            });

            space = space + 120;
        }

    }

    static Node relicDescription(ArrayList<AbstractRelic> relic,int ind, int x, int y) {
        Group g = new Group();

        String desc = relic.get(ind).getDescription();
        int len = desc.length();

        Rectangle rect = new Rectangle();
        rect.setX(x);
        rect.setY(y);
        rect.setFill(Color.GREY);
        rect.setStroke(Color.BLACK);
        //rectDesc.setEffect();
        rect.setWidth(len*6);
        rect.setHeight(20);
        rect.setVisible(true);

        Text relicText = new Text(desc);
        relicText.setX(x+5);
        relicText.setY(y+13);
        //relicText.setStroke(Color.BLACK);
        relicText.setFont(Font.font ("Verdana", 10));
        relicText.setFill(Color.WHITE);

        g.getChildren().add(rect);
        g.getChildren().add(relicText);

        return g;
    }


    public void shopRelics(){
        int space = width/4;
        relics = ((Merchant) game.getDungeon().getCurrentRoom()).getRelics();

        for (int i = 0; i < relics.size(); i++){

            int price = relicPrices.get(i);
            String name = relics.get(i).getName();
            System.out.println(name);
            name = name + ".png";
            ImageView relicImage = new ImageView(new Image(name));
            relicImage.setPreserveRatio(true);
            relicImage.setFitHeight(50);
            relicImage.setX(space);
            relicImage.setY(height/5*2+100);
            pane.getChildren().add( relicImage );

            ImageView cost = new ImageView(new Image("cost.png"));
            cost.setPreserveRatio(true);
            cost.setFitHeight(40);
            cost.setX(space);
            cost.setY(height/5*2+150);
            pane.getChildren().add(cost);
            Text text = new Text(" " +price);
            text.setX(space+30);
            text.setY(height/5*2+170);
            text.setFont(Font.font ("Verdana", 15));
            text.setFill(Color.WHITE);
            pane.getChildren().add(text);


            int warningLocX = space;
            int warningLocY = height/5*2+100;
            int j = i;
            relicImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    System.out.println("current gold: "+ game.getPlayer().getGold());
                    if (game.getPlayer().getGold() >= relicPrices.get(j)) {

                        game.getPlayer().changeGold(-relicPrices.get(j));

                        System.out.println("gold after purchase: "+ game.getPlayer().getGold());

                        System.out.println("========BEFORE ADDING NEW RELIC=========");
                        for(int k=0; k<game.getPlayer().relics.size(); k++){
                            System.out.println(game.getPlayer().relics.get(k).getName() );
                        }
                        System.out.println("========BEFORE NEW RELIC=========");

                        RelicActions.addRelic(game.getPlayer(),relics.get(j));
                        gridUpper.draw();

                        System.out.println("========AFTER ADDING NEW RELIC=========");
                        for(int k=0; k<game.getPlayer().relics.size(); k++){
                            System.out.println(game.getPlayer().relics.get(k).getName() );
                        }
                        System.out.println("========ADDED NEW RELIC=========");

                        pane.getChildren().remove(relicImage);
                        pane.getChildren().remove(text);
                        pane.getChildren().remove(cost);


                    } else {
                        destroy = false;
                        warning(warningLocX,warningLocY,false, warning);
                        System.out.println("You don't have enough gold for relic");
                    }

                }
            });
            Node desc = relicDescription(relics,i, space+50, height/5*2+130);
            pane.getChildren().add(desc);
            desc.setVisible(false);
            relicImage.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    ScaleTransition relicTransition = new ScaleTransition(Duration.millis(200), relicImage);
                    relicTransition.setToX(1.5f);
                    relicTransition.setToY(1.5f);
                    relicTransition.setCycleCount(1);
                    relicTransition.setAutoReverse(true);
                    relicTransition.play();
                    ScaleTransition relicCostTransition = new ScaleTransition(Duration.millis(200), cost);
                    relicCostTransition.setToX(1.5f);
                    relicCostTransition.setToY(1.5f);
                    relicCostTransition.setCycleCount(1);
                    relicCostTransition.setAutoReverse(true);
                    relicCostTransition.play();
                    desc.setVisible(true);

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
                    ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(200), cost);
                    scaleTransition1.setToX(1);
                    scaleTransition1.setToY(1);
                    scaleTransition1.setAutoReverse(true);
                    scaleTransition1.play();
                    if(destroy == false){ // destroy, if it is created
                        warning(warningLocX,warningLocY,true, warning);
                    }
                    //pane.getChildren().remove(desc);
                    desc.setVisible(false);
                }
            });

            space = space + 120;
        }
    }


    private void addBackground() {
        ImageView back = new ImageView(new Image("back_merchant.jpg"));
        back.setFitWidth(width);
        back.setFitHeight(height);

        ImageView image = new ImageView(new Image("papirus.png"));
        image.setFitWidth(width-300);
        image.setFitHeight(height);
        root.getChildren().add(back);
        root.getChildren().add(image);

    }


}

