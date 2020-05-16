package View;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import java.awt.*;

public abstract class GameScene extends Scene {
    protected int width;
    protected int height;
    StackPane root;
    public GameScene(){
        super(new StackPane() );
        width = Main.optionsManager.getWidth();
        height = Main.optionsManager.getHeight();
        this.root = (StackPane) this.getRoot();
    }

    public abstract void initialize();
    public abstract void draw();
}
