package View;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static View.Main.game;

public class SmithPane extends StackPane {
    Pane pane;
    VBox vbox;
    int padX;
    int padY;
    //static int price;
    private int width;
    private int height;
    ImageView back;

    public SmithPane(int width, int height){
        this.width = width;
        this.height = height;
        padX = width*3/2;
        padY = height*9/6;
        //price = 75;
        back = new ImageView(new Image("up.png"));
        pane = new Pane();
        vbox = new VBox(padY/70);
        vbox.setPadding(new Insets(padX/9,padY/3,padY/7,padX/5));
        //vbox.setPadding(new Insets(padX/9,400,100,250));
        this.setMinSize( width, height);
        this.getChildren().add(back);
        this.getChildren().add(pane);
        pane.getChildren().add(vbox);
    }

    public void visible(boolean bool){
        this.setVisible(bool);
    }


    public void initialize() {

        System.out.println("you have "+ game.getPlayer().masterDeck.getSize() + "card");
        vbox.getChildren().removeAll();
        vbox.getChildren().clear();
        int size = game.getPlayer().masterDeck.getSize();
        for (int i = 0; i < size; i++){
            HBox hbox = new HBox(); // ilk row last column'a üst üste iki tane ekliyor
            hbox.getChildren().clear();
            for(int count = 0; count < 7; count++ ){
                if(game.getPlayer().masterDeck.getCard(i).isUpgradable() == false)
                    continue;
                String name = game.getPlayer().masterDeck.getCard(i).getName();
                System.out.println(name);
                name = name + ".png";
                Rectangle rect = new Rectangle();
                rect.setFill(new ImagePattern(new Image(name)));
                rect.setWidth(padX/13.0);
                rect.setHeight(padY/(46/10));
                rect.setVisible(true);
                int price = 0; // şimdilik
                int j = i;
                rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        if(game.getPlayer().masterDeck.getCard(j).isUpgradable() && game.getPlayer().getGold()>price){
                            game.getPlayer().masterDeck.getCard(j).upgrade();
                            rect.setStroke(Color.GREEN);
                            visible(false);
                        }

                    }
                });

                rect.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), rect);
                        scaleTransition.setToX(1.5f);
                        scaleTransition.setToY(1.5f);
                        scaleTransition.setCycleCount(1);
                        scaleTransition.setAutoReverse(true);
                        scaleTransition.play();
                    }
                });

                rect.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), rect);
                        scaleTransition.setToX(1);
                        scaleTransition.setToY(1);
                        scaleTransition.setAutoReverse(true);
                        scaleTransition.play();
                    }
                });

                hbox.getChildren().add(rect);
                if(count  != 6)
                i++;
                if(i == size)
                    break;
            }
            vbox.getChildren().add(hbox);
        }
    }

    public void addBackground() {
        back.setFitWidth(width);
        back.setFitHeight(height);
        back.setOpacity(0.80);

    }

}
