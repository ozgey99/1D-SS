package sts;
import Models.Cards.Deck;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Models.Dungeon.Room.Fight;
import Models.TextBasedUI;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import Models.Cards.AbstractCard;
import Models.Dungeon.Room.Merchant;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import Models.Cards.Deck;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Models.Dungeon.Room.Fight;
import Models.Game;
import Models.TextBasedUI;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.ArrayList;

import static sts.Main.game;

public class MerchantScene extends RoomScene  {
    Pane pane;
    HBox box;
    ArrayList<AbstractCard> cards;
    ArrayList<Integer> prices;


    public MerchantScene() {
        pane = new Pane();
        box = new HBox();
        root.setMinSize( width, height);
    }

    @Override
    public void draw() {

    }

    public void initializeShop(){
        int space = width/4;
        cards = ((Merchant) game.getDungeon().getCurrentRoom()).getCards();
        prices = ((Merchant) game.getDungeon().getCurrentRoom()).getPrices();

        boolean saleAdded = true;
        int rand = (int) (Math.random() * 5);

        System.out.println("========INITIAL MASTER DECK=========");
        for(int k=0; k<game.getPlayer().masterDeck.getSize(); k++){
            System.out.println(game.getPlayer().masterDeck.getCard(k).getName());
        }
        System.out.println("========INITIAL MASTER DECK=========");

        for (int i = 0; i < cards.size(); i++){
            int price = prices.get(i);
            String name = cards.get(i).getName();
            name = name + ".png";
            Rectangle rect = new Rectangle();
            rect.setFill(new ImagePattern(new Image(name)));
            rect.setX(space);
            rect.setY(height/5);
            rect.setWidth(100);
            rect.setHeight(150);
            rect.setVisible(true);

            if(saleAdded && rand == i ){
                saleAdded = false;
                price = price/2;
                Text text1 = new Text("SALE");
                text1.setX(space+15);
                text1.setY(height/5-5);
                text1.setFont(Font.font ("Verdana", 30));
                text1.setFill(Color.RED);
                pane.getChildren().add(text1);
            }

            pane.getChildren().add(rect);
            String cost = "cost " + price;
            Text text = new Text(cost);
            text.setX(space+10);
            text.setY(height/5+160);
            text.setFont(Font.font ("Verdana", 15));
            text.setFill(Color.WHITE);
            pane.getChildren().add(text);

            int j = i;
            rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {

                    if (game.getPlayer().getGold() >= prices.get(j)) {
                        game.getPlayer().changeGold(-prices.get(j));
                        System.out.println(game.getPlayer().getGold());
                        game.getPlayer().masterDeck.addCard(cards.get(j));
                        pane.getChildren().remove(rect);
                        pane.getChildren().remove(text);

                        System.out.println("========AFTER CLICK MASTER DECK=========");
                        for(int k=0; k<game.getPlayer().masterDeck.getSize(); k++){
                            System.out.println(game.getPlayer().masterDeck.getCard(k).getName());
                        }

                    } else {
                        rect.setEffect(new Shadow(30, Color.RED));
                        System.out.println("You don't have enough gold to purchase this item.");
                    }

                }
            });
            space = space + 120;
        }
        root.getChildren().add(pane);

    }

    @Override
    public void initialize(){
        addBackground();
        initializeShop();
    }

    /*public void initialize() {


        ImageView back = new ImageView(new Image("back_merchant.jpg"));
        back.setFitWidth(width);
        back.setFitHeight(height);

        ImageView image = new ImageView(new Image("papirus.png"));
        image.setFitWidth(width-300);
        image.setFitHeight(height);
        root.getChildren().add(back);
        root.getChildren().add(image);

        int space = width/4;
        cards = ((Merchant) game.getDungeon().getCurrentRoom()).getCards();
        prices = ((Merchant) game.getDungeon().getCurrentRoom()).getPrices();

        for (int i = 0; i < cards.size(); i++){
            int price = prices.get(i);
            String name = cards.get(i).getName();
            System.out.println("NAME IS " + name);
            name = name + ".png";
            Rectangle rect = new Rectangle();
            rect.setFill(new ImagePattern(new Image(name)));
            rect.setX(space);
            rect.setY(height/5);
            rect.setWidth(100);
            rect.setHeight(150);
            rect.setVisible(true);
            pane.getChildren().add(rect);
            String post = "cost " + price;
            Text text = new Text(post);
            text.setX(space);
            text.setY(height/2+155);
            pane.getChildren().add(text);

            rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    // karakterin deck'ine eklemen lazım
                    // altın değişecek
                    // lorem ipsum

                }
            });
            space = space + 120;
        }
        //root.getChildren().add(box);
        root.getChildren().add(pane);
    }*/

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

    /*public void initialize(ArrayList<AbstractCard> cards, ArrayList<Integer> prices)
    {
        this.cards = cards;
        this.prices = prices;
        int space = width/4;
        //System.out.println("heeeey");
        for (int i = 0; i < cards.size(); i++) {
            String name = cards.get(i).getName();
            int price = prices.get(i);
            System.out.println(name);
            name = name + ".png";
            Rectangle rect = new Rectangle();
            rect.setFill(new ImagePattern(new Image(name)));
            space = space + 160;
            rect.setX(space);
            rect.setY(height/2);
            rect.setWidth(100);
            rect.setHeight(150);
            rectangles.add(rect);
            rectangles.get(i).setVisible(true);
            papirus.getChildren().add(rectangles.get(i));
            String post = "cost " + price;
            Text text = new Text(post);
            text.setX(space);
            text.setY(height/2+155);
            papirus.getChildren().add(text);
            int finalI = i;

            rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    // karakterin deck'ine eklemen lazım
                    // altın değişecek
                    // lorem ipsum

                }
            });
        }
        root.getChildren().add(papirus);
    }*/


}

