package View;


import Controller.Game;
import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

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
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;


public class Main extends Application {

    public static Game game = new Game();

    static int width;
    static int height;

    public static Stage window;


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Slay The Spire Menu");
        primaryStage.setScene(new MenuScene());
        window = primaryStage;
        primaryStage.show();

        //addMusic();
    }

    public static void changeWindowSize( int w, int h ){
        width = w;
        height = h;
    }

    public static void main(String[] args) {

        game.getDungeon().generate();
        launch(args);

    }
}