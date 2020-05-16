package View;


import Controller.Game;
import Controller.OptionsManager;
import javafx.application.Application;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.stage.Stage;



public class Main extends Application {

    public static Game game = new Game();
    public static OptionsManager optionsManager = new OptionsManager();
    int height;
    int width;
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
        //addMusic();
    }
    public void changeSize( int width, int height ){
        this.height = height;
        this.width = width;
    }
    public static void main(String[] args) {

        game.startMap();
        launch(args);

    }


}