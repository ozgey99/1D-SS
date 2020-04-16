package sts;

import Models.Game;
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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FightScene extends Scene {
    private int width = 1920;
    private int height = 1080;
    public static Game game;

    private StackPane root;
    private StackPane fightPane = new StackPane();
    private GridPane gridFight = new GridPane();
    CharPane left; //character pane
    MonsterPane right; //monster pane
    private Pane upper = new  Pane(); // Adjust this upper pane part
    private CardPane lower; // Adjust this lower pane part
    private GridPane division = new GridPane();


    public FightScene(StackPane pane)
    {
        super( pane );
        root = pane;
        root.setMinSize( width, height);
        addBackground();

        game = new Game();

        lower  = new CardPane(width , height/9*6,game.getPlayer().masterDeck);
        left = new CharPane( width/2 , height/9*6, game.getPlayer());
        right = new MonsterPane( width/2 , height/9*6);
        initialize();

        //game.start();
    }
    private void addBackground() {
        ImageView imageView = new ImageView(new Image("background1.jpg"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        root.getChildren().add(imageView);
    }
    private void addCharacters(){
        left.initialize();
        GridPane.setConstraints( left, 0,0,1,1);
        gridFight.getChildren().add(left);
    }

    private void addMonsters(){
        ImageView imageView = new ImageView(new Image("Cultist-pretty.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(width/8);

        right.getChildren().add( imageView );
        right.setAlignment( Pos.BOTTOM_CENTER );
        GridPane.setConstraints( right, 1,0,1,1);
        gridFight.getChildren().add(right);
    }
    private void initialize()
    {
        division.setPadding(new Insets(5,5,5,5));
        root.getChildren().add(division);

        division.setMinWidth(width);
        division.setMinHeight(height);
        division.setGridLinesVisible(true);


        GridPane.setConstraints(upper, 0,0,1,1);
        division.getChildren().add(upper);
        upper.setMinWidth(width);
        upper.setMinHeight(height/9);
        upper.setBackground( new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)) );


        GridPane.setConstraints(fightPane, 0,1,1,1);
        division.getChildren().add(fightPane);
        fightPane.setMinWidth(width);
        fightPane.setMinHeight(height/9*6);
        fightPane.getChildren().add(gridFight);



        GridPane.setConstraints(lower, 0,2,1,1);
        division.getChildren().add(lower);
        lower.setMinWidth(width);
        lower.setMinHeight(height/9*2);
        lower.setBackground( new Background(new BackgroundFill(Color.DARKMAGENTA, CornerRadii.EMPTY, Insets.EMPTY)) );


        //addClickables();

        //
        // Rectangle frame = new Rectangle();
        //frame.setStroke( Color.GREEN );
        //lower.getChildren().add( frame );

        lower.initialize();
        addCharacters();
        addMonsters();
        gridFight.setGridLinesVisible(true);


        game.getDungeon().generate();
    }
    private void update()
    {

    }
    private void draw()
    {

    }

    private void addClickables(){

        Button button1 = new Button("Button 1");
        root.getChildren().add(button1);
    }
}