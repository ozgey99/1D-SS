package View;


import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;

import Controller.Game;


import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static Controller.SaveIO.loadObjectFromFile;

public class MenuScene extends GameScene {
    private VBox menuBox;


    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("START NEW RUN", () -> startNewRun() ),
            new Pair<String, Runnable>("CONTINUE EXISTING RUN", () -> Main.window.setScene( new MapScene() )),
            new Pair<String, Runnable>("LOAD A SAVED RUN", () -> loadRun()),
            new Pair<String, Runnable>("VIEW COMPENDIUM",  () ->  Main.window.setScene( new CompendiumScene() )),
            new Pair<String, Runnable>("OPTIONS",  () -> Main.window.setScene( new OptionsScene() ) ),
            new Pair<String, Runnable>("INFO",  () -> openInfo() ),
            new Pair<String, Runnable>("EXIT", Platform::exit)
    );

    public MenuScene(){
        super();
        menuBox = new VBox(-5);
        root = (StackPane) createContent();
        root.setMinSize(width, height);
        //root.setBackground( new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void draw()
    {

    }


    @Override
    public void initialize() {

    }

    private Parent createContent() {

        addBackground();
        addTitle();

        double lineX = width / 2 - 100;
        double lineY = height / 3 + 50;

        //  addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);

        startAnimation();
        Main.mediaPlayer.addMenuMusic();
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

            javafx.scene.shape.Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });

        root.getChildren().add(menuBox);
    }
    private void startNewRun(){
        Main.game.startMap();
        Main.window.setScene( new MapScene() );
    }
    private void openInfo(){
        try {
            URI uri= new URI("https://slay-the-spire.fandom.com/wiki/Slay_the_Spire_Wiki");
            java.awt.Desktop.getDesktop().browse(uri);
            System.out.println("Web page opened in browser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadRun(){
        Main.game = (Game) loadObjectFromFile();
        Main.window.setScene( new MapScene() );
    }
}


