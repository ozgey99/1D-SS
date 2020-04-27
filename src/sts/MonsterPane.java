package sts;

import Models.Actions.FightActions;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    private ArrayList<Text> textList = new ArrayList<Text>();

    private HBox rectangleBox;
    private VBox box;


    Pane pane = new Pane();
    int privLen;
    int space = 100;
    int id;

    public MonsterPane( int width, int height){
        this.setEffect( new DropShadow(30, Color.RED) );
        this.setMinSize( width, height);
        this.width = width;
        this.height = height;
        // this.setMinSize( width, height);
        box = new VBox();
        rectangleBox = new HBox();



    }



    public void initialize()
    {
        monsters = ( (Fight) game.getDungeon().getCurrentRoom()).getMonsters();
        initializeTexts();
        initializeRectangles();


    }
    public void draw()
    {
        drawRectangles();
        drawTexts();
    }

    private void initializeRectangles()
    {
        monsters = ( (Fight) game.getDungeon().getCurrentRoom()).getMonsters();

        int len = monsters.size();
        int i;
        for( i = 0; i < len;i++)
        {

            AbstractMonster monster = monsters.get(i);

            String firstName = monster.getName();
            firstName = firstName + ".png";
            Rectangle rect1 = new Rectangle();
            rect1.setFill(new ImagePattern(new Image(firstName)));
            rect1.setWidth(100);
            rect1.setHeight(100);
            rectangleBox.getChildren().add(rect1);
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
                                        }

                                    }
            );

        }



        rectangleBox.setAlignment(Pos.BOTTOM_LEFT);
        this.getChildren().addAll(rectangleBox);
    }
    private void drawRectangles()
    {
        rectangleBox.getChildren().removeAll();
        rectangleBox.getChildren().clear();
        monsters = ( (Fight) game.getDungeon().getCurrentRoom()).getMonsters();

        int len = monsters.size();
        int i;
        for( i = 0; i < len;i++)
        {

            AbstractMonster monster = monsters.get(i);

            String firstName = monster.getName();
            firstName = firstName + ".png";
            Rectangle rect1 = new Rectangle();
            rect1.setFill(new ImagePattern(new Image(firstName)));
            rect1.setWidth(100);
            rect1.setHeight(100);
            rectangleBox.getChildren().add(rect1);
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
                                        }

                                    }
            );

        }

    }
    public void initializeTexts()
    {
        monsters = ( (Fight) game.getDungeon().getCurrentRoom()).getMonsters();
        for(int i = 0; i < ( (Fight) game.getDungeon().getCurrentRoom()).getMonsters().size();i++)
        {
            Text text = new Text();
            int j = i + 1;
            text.setText("Monster " + j + " health is " + monsters.get(i).getCurrentHP() + " block is " +monsters.get(i).getBlock() + "next move is " + FightActions.getAttackAmount(monsters.get(i), game.getPlayer(), monsters.get(i).getNextMove().getDamage()));
            text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
            box.getChildren().add(text);

        }
        box.setAlignment(Pos.TOP_LEFT);
        this.getChildren().add(box);


    }
    public void drawTexts()
    {
        box.getChildren().removeAll();
        box.getChildren().clear();
        monsters = ( (Fight) game.getDungeon().getCurrentRoom()).getMonsters();
        for(int i = 0; i < ( (Fight) game.getDungeon().getCurrentRoom()).getMonsters().size();i++)
        {
            Text text = new Text();
            int j = i + 1;
            text.setText("Monster " + j + " health is " + monsters.get(i).getCurrentHP() + " block is " +monsters.get(i).getBlock() + "next move is " + FightActions.getAttackAmount(monsters.get(i), game.getPlayer(), monsters.get(i).getNextMove().getDamage()));
            box.getChildren().add(i,text);
            text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));


        }
    }


}