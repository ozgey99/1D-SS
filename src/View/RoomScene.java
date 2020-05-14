package View;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.awt.*;

public abstract class RoomScene extends GameScene {
    public RoomScene(){
        super();
        //addBackground();
    }


    private void addBackground() {
        ImageView imageView = new ImageView(new Image("background1.jpg"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        root.getChildren().add(imageView);
    }
}