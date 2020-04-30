package sts;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.awt.*;

public abstract class RoomScene extends Scene {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected int width = screenSize.width;
    protected int height = screenSize.height;

    /*protected int width = 1300;
    protected int height = 700;*/
    StackPane root;

    public RoomScene(){
        super(new StackPane());
        this.root = (StackPane) this.getRoot();
        //addBackground();
    }
    public abstract void draw();
    public abstract void initialize();


    private void addBackground() {
        ImageView imageView = new ImageView(new Image("background1.jpg"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        root.getChildren().add(imageView);
    }
}