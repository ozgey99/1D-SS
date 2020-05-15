package View;

import Models.Cards.AbstractCard;
import Models.Cards.Deck;
import Controller.Dungeon.Room.Fight;
import javafx.geometry.Insets;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

import javafx.event.EventHandler;

import static View.Main.game;

public class CardPane  extends GridPane {
    private int space;

    private Deck deck;
    private int width;
    private int height;
    private int privLen;
    Pane pane = new Pane();
    private GridPane general = new GridPane();
    private FightScene scene;
    int id = 0;
    HBox box;



    private Effect shadow = new DropShadow(5, Color.BLACK);
    private Effect blur = new BoxBlur(1, 1, 3);

    public CardPane(int width, int height) {
        this.width = width;
        this.height = height;
        this.deck = deck;
        space = width / 10;
        this.setMinSize(width, height);
        this.scene = scene;
        box = new HBox(10);


    }

    public AbstractCard getCard() {
        return deck.getCard(id);
    }

    public void initialize()
    {
        initializeRectangles();
    }

    public void draw() {
        drawRectangles();
    }

    private void initializeRectangles()
    {
        this.getChildren().addAll(box);
    }

    public void drawRectangles() {
        box.getChildren().removeAll();
        box.getChildren().clear();

        deck = ( (Fight) game.getDungeon().getCurrentRoom()).getHand();
        if(deck != null) {

            int len = deck.getSize();
            privLen = len;
            int i;
            for (i = 0; i < len; i++) {

                AbstractCard firstHandCard = deck.getCard(i);
                String firstName = firstHandCard.getName();
                firstName = firstName + ".png";

                Rectangle rect1 = new Rectangle();
                rect1.setFill(new ImagePattern(new Image(firstName)));

                rect1.setWidth(width/10);
                rect1.setHeight(height/4);


                int finalI = i;
                rect1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        deck = ( (Fight) game.getDungeon().getCurrentRoom()).getHand();
                        AbstractCard firstHandCard = deck.getCard(finalI);
                        if(((Fight) game.getDungeon().getCurrentRoom()).useCard(firstHandCard) ) {
                            firstHandCard.setSelected();
                            System.out.println("PRESSED ID " + firstHandCard.getName());
                            id = finalI;
                            deck = ((Fight) game.getDungeon().getCurrentRoom()).getHand();
                            System.out.println("NEW BLOCK " + game.getPlayer().getBlock());
                            game.currentScene.draw();
                        }

                    }
                });

                box.getChildren().add(rect1);
                box.setPadding(new Insets(0,0,0,height/5*3));


            }
        }

    }




    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> action.run());
    }
}