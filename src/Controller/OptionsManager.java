package Controller;

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
        System.out.println(width + " " + height );
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
