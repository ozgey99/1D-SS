package Controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class MusicPlayer {

   MediaPlayer mediaPlayer;

   public MusicPlayer(){
       String s = "Battle.mp3";
       Media h = new Media(Paths.get(s).toUri().toString());
       mediaPlayer = new MediaPlayer(h);

   }

   public void addMusic(){
       stopMusic();
       String s = "Battle.mp3";
       Media h = new Media(Paths.get(s).toUri().toString());
       mediaPlayer = new MediaPlayer(h);
       mediaPlayer.setVolume(0.05);
       mediaPlayer.play();
   }

    public void stopMusic(){
       mediaPlayer.stop();
    }

    public void addMenuMusic(){
        stopMusic();
        String s = "SlaytheSpire.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setVolume(0.07);
        mediaPlayer.play();
    }
    public void setVolume( double volume ){
        mediaPlayer.setVolume(volume);
    }
}
