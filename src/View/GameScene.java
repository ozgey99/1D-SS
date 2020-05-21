package View;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.io.Serializable;

public abstract class GameScene extends Scene implements Serializable {
    protected int width;
    protected int height;
    StackPane root;
    public GameScene(){
        super(new StackPane() );
        this.root = (StackPane) this.getRoot();
        width = Main.optionsManager.getWidth();
        height = Main.optionsManager.getHeight();
        root.setPrefSize(width, height);
    }
    public abstract void initialize();
    public abstract void draw();
}
