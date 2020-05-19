package View;

import Controller.Dungeon.Room.Fight;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.nio.file.Paths;

import static View.Main.game;


public class FightScene extends RoomScene {

    private FightRewardsPane fightRewards;
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
        super();
        fightPane = new StackPane();
        gridFight = new GridPane();
        fightRewards = new FightRewardsPane(width, height);
        gridLeft = new CharPane(5*width/10, 11*height/18);
        gridRight = new MonsterPane(5*width / 10, 11*height / 18 );
        gridLower  = new CardPane(width , height/9*6);
        gridUpper = new UpperPane(width,height/9);
        division = new GridPane();
        endTurn = new Rectangle();
        root.setMinSize( width, height);

        initialize();
    }




    public void getRewards(){

        fightRewards.visible(true);
        fightRewards.initialize();
        root.getChildren().add(fightRewards);

    }

    private void addBackground() {
        ImageView imageView = new ImageView(new Image("background1.jpg"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        root.getChildren().add( imageView);
    }
    private void initializeLeft(){
        gridLeft.initialize();
        GridPane.setConstraints(gridLeft, 0,0,1,1);
        gridFight.getChildren().add(gridLeft);
    }

    private void initializeRight(){
        gridRight.initialize();
        GridPane.setConstraints(gridRight, 1,0,1,1);
        gridFight.getChildren().add(gridRight);

    }
    private void initializeUpper()
    {
        gridUpper.initialize();
        gridUpper.setBackground( new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)) );
        GridPane.setConstraints(gridUpper, 0,0,1,1);
        division.getChildren().add(gridUpper);
        gridUpper.setMinWidth(width);
        gridUpper.setMinHeight(height/9);


    }
    private void initializeLower()
    {
        gridLower.initialize();
        GridPane.setConstraints(gridLower, 0,2,1,1);
        division.getChildren().add(gridLower);
    }
    public void initialize()
    {
        addBackground();
        Main.mediaPlayer.addMusic();
        initializeLeft();
        initializeRight();
        initializeUpper();
        initializeLower();


        GridPane.setConstraints(gridFight, 0,1,1,1);
        division.getChildren().add(gridFight);
        division.setPadding(new Insets(5,5,5,5));
        division.setMinWidth(width);
        division.setMinHeight(height);
        root.getChildren().add(division);


        endTurn.setFill(new ImagePattern(new Image("endturn.png")));
        endTurn.setWidth(width/(128.0/10));
        endTurn.setHeight(height/(72.0/10));
        endTurn.setOnMouseClicked(e -> {
            ((Fight) game.getDungeon().getCurrentRoom()).getDiscard().addDeck(((Fight) game.getDungeon().getCurrentRoom()).getHand());
                draw();
                ((Fight) game.getDungeon().getCurrentRoom()).nextState();


            }
        );

        root.getChildren().add(endTurn);

        draw();

    }

    public void draw()
    {

        if(( (Fight) game.getDungeon().getCurrentRoom()).getMonsters().size() == 0 &&  game.getPlayer().getCurrentHP() > 0)
        {

            if (game.getDungeon().getCurrentRoom().getChildren() == null) {

                game.currentScene = new WinScene();
                Main.window.setScene(
                        game.currentScene);
                game.currentScene.initialize();

            }
            else {
                ((Fight) game.getDungeon().getCurrentRoom()).postFight();
                getRewards();
            }

        }
        else if( game.getPlayer().getCurrentHP() <= 0)
        {
                game.currentScene = new WinScene();
                Main.window.setScene(
                        game.currentScene);
                game.currentScene.initialize();

        }
        else {
            // get current energy
            gridUpper.draw();
            gridLeft.draw();
            gridRight.draw();
            gridLower.draw();
        }

    }

}