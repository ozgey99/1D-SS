package GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FightScene extends Scene {
    private int width = 1600;
    private int height = 900;

    private StackPane root;
    //private Pane fightPane = new Pane();
    private GridPane gridFight = new GridPane();
    private Pane upper = new  Pane(); // Adjust this upper pane part
    private GridPane lower = new GridPane(); // Adjust this lower pane part
    private GridPane division = new GridPane();

    public FightScene(StackPane pane)
    {
        super( pane );
        root = pane;
        addBackground();

        division.setPadding(new Insets(5,5,5,5));
        division.setGridLinesVisible(true);
        root.getChildren().add(division);
        division.setGridLinesVisible(true);



        GridPane.setConstraints(upper, 0,0,1,1);
        division.getChildren().add(gridFight);
        division.setGridLinesVisible(true);

        gridFight.setPadding(new Insets(5,5,5,5));
        gridFight.setGridLinesVisible(true);

       // GridPane.setConstraints(gridFight, 0,1,1,1);
       // division.getChildren().add(gridFight);

       // GridPane.setConstraints(lower, 0,2,1,1);
      //  division.getChildren().add(gridFight);

        //addClickables();

        //Rectangle frame = new Rectangle(5, 5);
        //frame.setStroke( Color.GREEN );

        //root.getChildren().add(fightPane);

       // fightPane.setEffect( new DropShadow(30, Color.YELLOW) );

        addCharacters();
        addMonsters();
    }
    private void addBackground() {
        ImageView imageView = new ImageView(new Image("background1.jpg"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        root.getChildren().add(imageView);
    }
    private void addCharacters(){
        ImageView imageView = new ImageView(new Image("Ironclad.png"));
        imageView.setFitWidth(width/8);
        imageView.setFitHeight(height/8);
        //imageView.setTranslateX(width / 4 - (width/8) / 2);
        //imageView.setTranslateY(height / 2);
        GridPane.setConstraints( imageView, 0,0,1,1);
        gridFight.getChildren().add(imageView);
    }
    private void addMonsters(){
        ImageView imageView = new ImageView(new Image("Cultist-pretty.png"));
        imageView.setFitWidth(width/8);
        imageView.setFitHeight(height/8);
        //imageView.setTranslateX(width / 4 - (width/8) / 2);
        //imageView.setTranslateY(height / 2);
        GridPane.setConstraints( imageView, 1,0,1,1);
        gridFight.getChildren().add(imageView);
    }
    private void addClickables(){

        Button button1 = new Button("Button 1");
        root.getChildren().add(button1);
    }
}
