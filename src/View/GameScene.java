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

/**package View;


 public abstract class RoomScene extends Scene {

 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
 protected int width = screenSize.width;
 protected int height = screenSize.height;

StackPane root;

    public RoomScene(StackPane stackPane){
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
*/