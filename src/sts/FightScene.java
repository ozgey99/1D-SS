package sts;

import Models.Cards.Deck;
import Models.Creatures.AbstractCharacter;
import Models.Creatures.Monsters.AbstractMonster;
import Models.Dungeon.Room.Fight;
import Models.Game;
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

import static sts.Main.game;


public class FightScene extends Scene {
    private int width = 1920;
    private int height = 1080;
    private Text healthText = new Text();
    private Text goldText = new Text();
    private StackPane root;
    private StackPane fightPane = new StackPane();
    private GridPane gridFight = new GridPane();
    CharPane left; //character pane
    MonsterPane right; //monster pane
    private Pane upper = new  Pane(); // Adjust this upper pane part
    public static CardPane lower; // Adjust this lower pane part
    private GridPane division = new GridPane();
    private Text monsterText = new Text();
    private Text energyText = new Text();
    private Text blockText = new Text();
    private Deck handDeck;
    private AbstractCharacter player;
    private AbstractMonster monster;
    Rectangle endTurn = new Rectangle();


    public FightScene(StackPane pane)
    {
        super( pane );
        root = pane;
        root.setMinSize( width, height);
        addBackground();





    }

    public void setHandDeck(Deck deck)
    {
        handDeck = deck;
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
        right.initialize();
        GridPane.setConstraints( right, 1,0,1,1);
        gridFight.getChildren().add(right);
    }
    public void initialize(Deck deck,AbstractCharacter player, AbstractMonster monster)
    {
        this.monster = monster;
        this.player = player;
        this.handDeck = deck;

        lower  = new CardPane(width , height/9*6,handDeck,this);
        left = new CharPane( width/2 , height/9*6,player);
        right = new MonsterPane(width / 2, height / 9 * 6,monster);

        division.setPadding(new Insets(5,5,5,5));
        root.getChildren().add(division);
        division.setMinWidth(width);
        division.setMinHeight(height);
        division.setGridLinesVisible(true);


        endTurn.setFill(new ImagePattern(new Image("endturn.png")));
        endTurn.setX(200);
        endTurn.setY(0);
        endTurn.setWidth(100);
        endTurn.setHeight(100);
        endTurn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                lower.draw();
                draw();
                ( (Fight) game.getDungeon().getCurrentRoom()).nextState();
            }
        });



        energyText.setX(0);
        energyText.setY(30);
        energyText.setText("Your energy:   " + player.currentEnergy);
        energyText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));


        monsterText.setX(1000);
        monsterText.setY(50);
        monsterText.setText("Monster health:   " + monster.getCurrentHP());
        monsterText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        healthText.setX(0);
        healthText.setY(100);
        healthText.setText("Your health: " + player.getCurrentHP());
        healthText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        blockText.setX(0);
        blockText.setY(16);
        blockText.setText("Your block: " + player.getBlock());
        blockText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        goldText.setX(0);
        goldText.setY(50);
        goldText.setText("Your gold is: " + player.getGold());
        goldText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        upper.getChildren().addAll(healthText);
        upper.getChildren().addAll(goldText);
        upper.getChildren().addAll(monsterText);
        upper.getChildren().addAll(energyText);
        upper.getChildren().addAll(endTurn);
        upper.getChildren().addAll(blockText);


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

        lower.initialize();
        addCharacters();
        addMonsters();
        gridFight.setGridLinesVisible(true);


    }

    public void update()
    {
        //setMonsterName();
        //setMonsterHealth();
        //setenergy();
        //setGold();
        //setEnergy();
        //setDrawDeck();
        //setEverything();
        left.update(player);
        right.update(monster); //call this for every monster

    }
    public void draw()
    {


        energyText.setText("Your energy:   " + player.currentEnergy);
        healthText.setText("Your health: " + player.getCurrentHP());
        monsterText.setText("Monster health:   " + monster.getCurrentHP());
        goldText.setText("Your gold is: " + player.getGold());
        blockText.setText("Your block: " + player.getBlock());
        //lower.draw();







    }

    private void addClickables(){

        Button button1 = new Button("Button 1");
        root.getChildren().add(button1);
    }

}