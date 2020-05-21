package View;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;
import Controller.MusicPlayer;

import java.util.Arrays;
import java.util.List;



public class OptionsScene extends GameScene {
    private List<Pair<String, Runnable>> resolutions = Arrays.asList(
            new Pair<String, Runnable>("1920x1080", () ->{ setSize(1920,1080)  ; draw();} ),
            new Pair<String, Runnable>("1600x900", () -> { setSize(1600,900); draw();} ),
            new Pair<String, Runnable>("1366x768", () -> { setSize(1366,768) ; draw();}),
            new Pair<String, Runnable>("1280x720", () -> { setSize(1280,720) ; draw();} ),
            new Pair<String, Runnable>("Confirm", () -> confirm() )

    );
    private List<Pair<String, Runnable>> volumeOptions = Arrays.asList(
            new Pair<String, Runnable>("High", () ->  setVolume( 0.1) ),
            new Pair<String, Runnable>("Normal", () ->  setVolume( 0.06) ),
            new Pair<String, Runnable>("Low", () ->  setVolume( 0.04)),
            new Pair<String, Runnable>("Off", () ->  setVolume( 0.0 ))

    );
    VBox resBox;
    VBox volBox;
    StackPane root;
    public OptionsScene(){
        super();
        initialize();

    }
    public void initialize(){

        root = (StackPane) this.getRoot();
        root.setMinSize(width, height);
        addBackground();
        root.setBackground( new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        resBox = new VBox();
        resBox.setVisible(true);

        volBox = new VBox();
        volBox.setVisible(true);


        resBox.setAlignment(Pos.CENTER);
        StackPane.setAlignment(resBox, Pos.CENTER);

        addOptions();
    }
    private void addOptions(){

        resolutions.forEach(data -> {
            StsMenuPane item = new StsMenuPane(data.getKey());
            item.setOnAction(data.getValue());


            Rectangle clip = new Rectangle(width/6, height/6);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            resBox.getChildren().addAll(item);
        });
        root.getChildren().add(resBox);
        startAnimation();


      /*  volumeOptions.forEach(data -> {
            StsMenuPane item = new StsMenuPane(data.getKey());
            item.setOnAction(data.getValue());


            Rectangle clip = new Rectangle(width/6, height/6);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            volBox.getChildren().addAll(item);
        });
        root.getChildren().add(volBox);
        startAnimation();*/
    }

    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1));
        st.setToY(1);
        st.setOnFinished(e -> {

            for (int i = 0; i < resBox.getChildren().size(); i++) {
                Node n = resBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
       /* st.setToY(1);
        st.setOnFinished(e -> {

            for (int i = 0; i < volBox.getChildren().size(); i++) {
                Node n = volBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();*/
    }
    public void draw(){
        Main.window.setScene( new OptionsScene() );
    }
    private void setSize( int width, int height ){
        Main.optionsManager.changeSize( width, height );
    }
    private void confirm(){
        Main.window.setScene( new MenuScene() );
    }
    private void addBackground() {
        ImageView imageView = new ImageView(new Image("background1.jpg"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        root.getChildren().add( imageView);
    }
    private void setVolume( double volume ){
        Main.mediaPlayer.setVolume( volume );
    }

}