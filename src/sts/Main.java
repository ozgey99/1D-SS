package sts;


import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    private static int width = 1600;
    private static int height = 900;

    private Stage window;

    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("START RUN", () -> window.setScene( new FightScene(new StackPane()) )),
            new Pair<String, Runnable>("VIEW COMPENDIUM", () -> {}),
            new Pair<String, Runnable>("VIEW STATISTICS", () -> {}),
            new Pair<String, Runnable>("OPTIONS", () -> {}),
            new Pair<String, Runnable>("EXIT", Platform::exit)
    );

    private Pane root = new Pane();
    private VBox menuBox = new VBox(-5);

    private Parent createContent() {
        addBackground();
        addTitle();

        double lineX = width / 2 - 100;
        double lineY = height / 3 + 50;

      //  addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);

        startAnimation();

        return root;
    }

    private void addBackground() {
        ImageView imageView = new ImageView(new Image("hall.jpg"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        root.getChildren().add(imageView);
    }

    private void addTitle() {
        StsTitle title = new StsTitle("Slay The Spire");
        title.setTranslateX(width / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(height / 3);

        root.getChildren().add(title);
    }

    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1) );
        st.setToY(1);
        st.setOnFinished(e -> {

            for (int i = 0; i < menuBox.getChildren().size(); i++) {
                Node n = menuBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }

    private void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            StsMenuPane item = new StsMenuPane(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(-300);

            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });

        root.getChildren().add(menuBox);
    }
    public void addMusic()
    {
        Media media = new Media( "src/music.mp3" );
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Slay The Spire Menu");
        primaryStage.setScene(new Scene(createContent()));
        window = primaryStage;
        primaryStage.show();
        //addMusic();
    }

    public void changeWindowSize( int width, int height ){

            this.width = width;
            this.height = height;
    }
    public void switch2Fight( Stage stage ){
        Pane fight = new Pane();
       // stage.setScene( new Scene() );
    }

    public static void main(String[] args) {
        launch(args);
    }
}