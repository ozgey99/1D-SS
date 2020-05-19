package View;

import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
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
    VBox vbox;
    int padX;
    int padY;
    static  int price = 75;
    private int width;
    private int height;
    ImageView back;
    ImageView nextButton;
    StackPane stack;
    ScrollPane sp = new ScrollPane();

    public RemoveCard(int width, int height){
        this.width = width;
        this.height = height;
        padX = width*3/2;
        padY = height*9/6;
        back = new ImageView(new Image("up.png"));

        nextButton = new ImageView(new Image("goAhead.png"));
        stack = new StackPane();
        back.setFitWidth(padX);
        back.setFitHeight(padY);
        stack.getChildren().add(back);
        stack.setPadding(new Insets(150, 200, 150, 250));
        vbox = new VBox(height/70);

        stack.getChildren().add(sp);
        stack.setAlignment(sp, Pos.CENTER);

        this.setMinSize( width, height);
        this.getChildren().add(stack);

    }


    public void visible(boolean bool){
        this.setVisible(bool);
    }

    public void draw(){
        initialize();
        vbox.setDisable(true);
    }

    public void initialize() {

        sp.setContent(vbox);
        sp.setPrefSize(width,height);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        vbox.getChildren().removeAll();
        vbox.getChildren().clear();
        int size = game.getPlayer().masterDeck.getSize();

        for (int i = 0; i < size; i++){
            HBox hbox = new HBox(height/70.0);
            hbox.getChildren().clear();
            for(int count = 0; count < 7; count++ ){
                String name = game.getPlayer().masterDeck.getCard(i).getName();
                System.out.println(name);
                name = name + ".png";
                Rectangle rect = new Rectangle();
                rect.setFill(new ImagePattern(new Image(name)));
                rect.setWidth(padX/13.0);
                rect.setHeight(padY/(46.0/10));
                rect.setVisible(true);

                int j = i;
                rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        if (game.getPlayer().getGold() >= price) {
                            game.getPlayer().changeGold(-price);
                            game.getPlayer().masterDeck.removeCard(game.getPlayer().masterDeck.getCard(j));
                            draw();
                            price += 25;
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

    public int getPrice(){
        return price;
    }

    public void addBackground() {
        back.setFitWidth(width);
        back.setFitHeight(height);
        back.setOpacity(0.80);

    }
}
