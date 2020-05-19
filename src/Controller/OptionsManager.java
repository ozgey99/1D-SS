package Controller;

import View.GameScene;
import View.Main;

import java.awt.*;

public class OptionsManager {
    int height;
    int width;
    public OptionsManager(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.width;
        height = screenSize.height;
    }
    public void changeSize( int width, int height){
        this.width = width;
        this.height = height;
        System.out.println( "HEREEE");
        ( (GameScene) Main.window.getScene()).draw();
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
