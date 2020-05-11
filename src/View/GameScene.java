package View;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import java.awt.*;

public abstract class GameScene extends Scene {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected int width = screenSize.width;
    protected int height = screenSize.height;
    StackPane root;
    public GameScene(){
        super(new StackPane() );
        this.root = (StackPane) this.getRoot();
    }

    public abstract void initialize();
    public abstract void draw();
}
