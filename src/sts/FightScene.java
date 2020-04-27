package sts;

import Models.Cards.Deck;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Models.Dungeon.Room.Fight;
import Models.TextBasedUI;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static sts.Main.game;


public class FightScene extends RoomScene {


    private StackPane fightPane;
    private GridPane gridFight;
    CharPane gridLeft; //character pane
    private MonsterPane gridRight; //monster pane
    private UpperPane gridUpper;
    private CardPane gridLower; // Adjust this lower pane part
    private GridPane division;
    Rectangle endTurn;


    public FightScene()
    {
        fightPane = new StackPane();
        gridFight = new GridPane();

        gridLeft = new CharPane(width/2, height/9*6);
        gridRight = new MonsterPane(width / 2, height / 9 * 6);
        gridLower  = new CardPane(width , height/9*6);
        gridUpper = new UpperPane(width,height/9);
        division = new GridPane();
        endTurn = new Rectangle();

        root.setMinSize( width, height);

    }



    private void addBackground() {
        ImageView imageView = new ImageView(new Image("background1.jpg"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        root.getChildren().add(imageView);
    }
    private void initializeLeft(){
        gridLeft.initialize();
        gridLeft.setBackground( new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)) );
        GridPane.setConstraints(gridLeft, 0,0,1,1);
        gridFight.getChildren().add(gridLeft);
    }

    private void initializeRight(){
        gridRight.initialize();
        gridRight.setBackground( new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)) );
        GridPane.setConstraints(gridRight, 1,0,1,1);
        gridFight.getChildren().add(gridRight);

    }
    private void initializeUpper()
    {
        gridUpper.initialize();
        gridUpper.setBackground( new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)) );

        GridPane.setConstraints(gridUpper, 0,0,1,1);
        division.getChildren().add(gridUpper);
        gridUpper.setMinWidth(width);
        gridUpper.setMinHeight(height/9);


    }
    private void initializeLower()
    {
        gridLower.initialize();
        gridLower.setBackground( new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)) );
        GridPane.setConstraints(gridLower, 0,2,1,1);
        division.getChildren().add(gridLower);
    }
    public void initialize()
    {

        initializeLeft();
        initializeRight();
        initializeUpper();
        initializeLower();
        addBackground();


        gridFight.setGridLinesVisible(true);
        GridPane.setConstraints(gridFight, 0,1,1,1);
        division.getChildren().add(gridFight);
        division.setPadding(new Insets(5,5,5,5));
        division.setMinWidth(width);
        division.setMinHeight(height);
        division.setGridLinesVisible(true);
        root.getChildren().add(division);


        endTurn.setFill(new ImagePattern(new Image("endturn.png")));
        endTurn.setX(200);
        endTurn.setY(0);
        endTurn.setWidth(100);
        endTurn.setHeight(100);
        endTurn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {

                ((Fight) game.getDungeon().getCurrentRoom()).getDiscard().addDeck(((Fight) game.getDungeon().getCurrentRoom()).getHand());
                draw();
                ((Fight) game.getDungeon().getCurrentRoom()).nextState();


            }
        });

        root.getChildren().add(endTurn);

    }


    public void draw()
    {

      gridUpper.draw();
      gridLeft.draw();
      gridRight.draw();
      gridLower.draw();

    }


}