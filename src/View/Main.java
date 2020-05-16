package View;


import Controller.Game;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import Controller.MusicPlayer;


public class Main extends Application {

    public static Game game = new Game();
    public static MusicPlayer mediaPlayer = new MusicPlayer();

    static int width;
    static int height;
    public static Stage window;


    @Override
    public void start(Stage primaryStage) throws Exception {

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

    public static void changeWindowSize( int w, int h ){
        width = w;
        height = h;
    }

    public static void main(String[] args) {
        game.startMap();
        launch(args);

    }
}