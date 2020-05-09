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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static View.Main.game;

public class RemoveCard extends StackPane {
    Pane pane;
    VBox vbox;
    int padX;
    int padY;
    static  int cardY;
    private int width;
    private int height;
    ImageView back;

    public RemoveCard(int width, int height){
        this.width = width;
        this.height = height;
        padX = width*3/2;
        padY = height*9/6;
        back = new ImageView(new Image("up.png"));
        pane = new Pane();
        vbox = new VBox(height/70);
        vbox.setPadding(new Insets(padX/9,padY/3,padY/7,padX/5));
        //vbox.setPadding(new Insets(150,400,100,250));
        this.setMinSize( width, height);
        this.getChildren().add(back);
        this.getChildren().add(pane);
        pane.getChildren().add(vbox);
    }

    public void visible(boolean bool){
        this.setVisible(bool);
    }

    public void draw(){initialize();}

    public void initialize() {

        System.out.println("you have "+ game.getPlayer().masterDeck.getSize() + "card");
        vbox.getChildren().removeAll();
        vbox.getChildren().clear();
        int size = game.getPlayer().masterDeck.getSize();

        for (int i = 0; i < size; i++){
            HBox hbox = new HBox();
            hbox.getChildren().clear();
            for(int count = 0; count < 7; count++ ){
                String name = game.getPlayer().masterDeck.getCard(i).getName();
                System.out.println(name);
                name = name + ".png";
                Rectangle rect = new Rectangle();
                rect.setFill(new ImagePattern(new Image(name)));
                rect.setWidth(padX/13);
                rect.setHeight(padY/(46/10));
                rect.setVisible(true);
                int price = 0; // şimdilik
                int j = i;
                rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        if (game.getPlayer().getGold() >= price) {
                            System.out.println("gold before: "+ game.getPlayer().getGold());
                            game.getPlayer().changeGold(-price);

                            System.out.println("gold after :" + game.getPlayer().getGold());
                            game.getPlayer().masterDeck.removeCard(game.getPlayer().masterDeck.getCard(j));
                            draw();
                            System.out.println("========AFTER CLICK MASTER DECK=========");
                            for(int k=0; k<game.getPlayer().masterDeck.getSize(); k++){
                                System.out.println(game.getPlayer().masterDeck.getCard(k).getName());
                            }

                        } else {
                            System.out.println("You don't have enough gold for card");
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
                if(count != 6)
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
