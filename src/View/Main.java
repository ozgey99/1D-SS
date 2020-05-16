                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    package View;


import Controller.Game;
import Controller.OptionsManager;
import javafx.application.Application;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import Controller.MusicPlayer;


public class Main extends Application {

    public static Game game = new Game();
    public static OptionsManager optionsManager = new OptionsManager();
    int height;
    int width;
   // public static MusicPlayer mediaPlayer = new MusicPlayer();

    public static Stage window;


    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Slay The Spire Menu");
        primaryStage.setScene(new MenuScene());
        window = primaryStage;
        primaryStage.show();

        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                game.currentScene = new MenuScene();
                Main.window.setScene(
                        game.currentScene);
                game.currentScene.initialize();
            }
        });

    }
    public static void main(String[] args) {
        game.startMap();
        launch(args);

    }


}