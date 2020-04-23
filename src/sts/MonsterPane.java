package sts;

import Models.Cards.AbstractCard;
import Models.Cards.Deck;
import Models.Creatures.Monsters.AbstractMonster;
import Models.Dungeon.Room.Fight;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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


public class MonsterPane extends StackPane {
    private int width;
    private int height;
    private AbstractMonster monster;
    ArrayList<String> MonsterNames = new ArrayList<>();
    ArrayList<Rectangle> Rectangles = new ArrayList<>();
    ArrayList<AbstractMonster> monsters = new ArrayList<>();
    Pane pane = new Pane();
    int privLen;
    int space = 100;
    int id;

    public MonsterPane( int width, int height){
        this.setEffect( new DropShadow(30, Color.RED) );
        this.setMinSize( width, height);
        this.width = width;
        this.height = height;
        this.setMinSize( width, height);

    }

    void update(AbstractMonster m)
    {

        draw();
    }

    public void initialize()
    {
        monsters = ( (Fight) game.getDungeon().getCurrentRoom()).getMonsters();
        initializeRectangles();


    }
    private void initializeRectangles()
    {
        monsters = ( (Fight) game.getDungeon().getCurrentRoom()).getMonsters();

        int len = monsters.size();
        System.out.println("SIZE IS "+ len);
        privLen = len;
        int i;
        for( i = 0; i < len;i++)
        {

            AbstractMonster monster = monsters.get(i);

            String firstName = monster.getName();
            firstName = firstName + ".png";
            MonsterNames.add(firstName);
            System.out.println("FIRSTNAME NAME IS "+ firstName);
            Rectangle rect1 = new Rectangle();
            rect1.setFill(new ImagePattern(new Image(firstName)));
            rect1.setX(i * space);
            rect1.setY(600);
            rect1.setWidth(100);
            rect1.setHeight(100);
            System.out.println(rect1 == null);
            Rectangles.add(rect1);
            System.out.println("Rectangles size is "+ Rectangles.size());
            pane.getChildren().add(Rectangles.get(i));
            Rectangles.get(i).setVisible(true);
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), rect1);
            rect1.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    scaleTransition.setToX(1.5f);
                    scaleTransition.setToY(1.5f);
                    scaleTransition.setCycleCount(2);
                    scaleTransition.setAutoReverse(true);
                    scaleTransition.play();
                }
            });
            int finalI = i;
            rect1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {

                    ( (Fight) game.getDungeon().getCurrentRoom()).setSelectedMonster(monsters.get(finalI));
                        System.out.println(id);
                        game.fightScene.draw();
                    }

                }
            );

        }



        this.getChildren().addAll(pane);
    }
    public void draw()
    {


    }
    public AbstractMonster getMonster(){
        return monster;
    }
}