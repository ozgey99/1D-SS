package View;

import Models.Actions.FightActions;
import Models.Creatures.Monsters.AbstractMonster;
import Controller.Dungeon.Room.Fight;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
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

import static View.Main.game;


public class MonsterPane extends StackPane {
    private int width;
    private int height;
    private AbstractMonster monster;

    ArrayList<AbstractMonster> monsters = new ArrayList<>();
    private ArrayList<Text> textList = new ArrayList<Text>();

    private HBox rectangleBox;
    private VBox box;

    Pane pane = new Pane();

    public MonsterPane( int width, int height){
        this.setEffect( new DropShadow(30, Color.RED) );
        this.setMinSize( width, height);
        this.width = width;
        this.height = height;
        // this.setMinSize( width, height);
        box = new VBox();
        rectangleBox = new HBox(20);




    }

    static Node initializeMonsterMove(String text, int x, int y) {
        Group g = new Group();

        int len = text.length();

        Rectangle rect = new Rectangle();
        rect.setX(x);
        rect.setY(y);
        rect.setFill(Color.GREY);
        rect.setStroke(Color.BLACK);
        rect.setWidth(len*5);
        rect.setHeight(len);
        rect.setVisible(true);

        Text relicText = new Text(text);
        relicText.setX(x+5);
        relicText.setY(y+13);
        relicText.setFont(Font.font ("Verdana", 15));
        relicText.setFill(Color.WHITE);

        g.getChildren().add(rect);
        g.getChildren().add(relicText);

        return g;
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
            System.out.println("Monster " + firstName);
            firstName = firstName + ".png";
            Rectangle rect1 = new Rectangle();
            rect1.setFill(new ImagePattern(new Image(firstName)));
            rect1.setWidth(100);
            rect1.setHeight(100);
            rectangleBox.getChildren().add(rect1);
            int j = i + 1;
            String s = "  Monster (" + j + ") health is " + monsters.get(i).getCurrentHP() + "\n"+
                    "  block is " +monsters.get(i).getBlock() + "\n"+
                    "  next move damage is " + FightActions.getAttackAmount(monsters.get(i), game.getPlayer(), monsters.get(i).getNextMove().getDamage());
            Node movement = initializeMonsterMove(s, width/4*3, height/2);
            this.getChildren().add(movement); // add each movement
            movement.setVisible(false);
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), rect1);
            rect1.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    scaleTransition.setToX(1.5f);
                    scaleTransition.setToY(1.5f);
                    scaleTransition.setCycleCount(1);
                    scaleTransition.setAutoReverse(true);
                    scaleTransition.play();
                    movement.setVisible(true);
                }
            });

            rect1.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    movement.setVisible(false);
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), rect1);
                    scaleTransition.setToX(1);
                    scaleTransition.setToY(1);
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
            int j = i + 1;
            String s = "  Monster (" + j + ") health is " + monsters.get(i).getCurrentHP() + "\n"+
                    "  block is " +monsters.get(i).getBlock() + "\n"+
                    "  next move damage is " + FightActions.getAttackAmount(monsters.get(i), game.getPlayer(), monsters.get(i).getNextMove().getDamage());
            Node movement = initializeMonsterMove(s, width/4*3, height/2);
            this.getChildren().add(movement);
            movement.setVisible(false);
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), rect1);
            rect1.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    scaleTransition.setToX(1.5f);
                    scaleTransition.setToY(1.5f);
                    scaleTransition.setCycleCount(1);
                    scaleTransition.setAutoReverse(true);
                    scaleTransition.play();
                    movement.setVisible(true);
                }
            });
            rect1.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    movement.setVisible(false);
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), rect1);
                    scaleTransition.setToX(1);
                    scaleTransition.setToY(1);
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
            text.setText("Monster " + j + " health is " + monsters.get(i).getCurrentHP() + " block is " +monsters.get(i).getBlock() + "next move damage is " + FightActions.getAttackAmount(monsters.get(i), game.getPlayer(), monsters.get(i).getNextMove().getDamage()));
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
            text.setText("Monster " + j + " health is " + monsters.get(i).getCurrentHP() +
                    " block is " +monsters.get(i).getBlock() + "next move damage is " +
                    FightActions.getAttackAmount(monsters.get(i), game.getPlayer(), monsters.get(i).getNextMove().getDamage()));
/*
            for(int r = 0; r < monsters.get(i).powers.size();r++)
            {
                System.out.println("MONSTER POWR IS " + monsters.get(i).powers.get(r).getName() + " " + monsters.get(i).powers.get(r).getAmount());
            }*/
            box.getChildren().add(i,text);
            text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));


        }

    }


}