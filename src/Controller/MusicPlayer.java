package Controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class MusicPlayer {

   MediaPlayer mediaPlayer;
   double volume;

   public MusicPlayer(){
       String s = "Battle.mp3";
       Media h = new Media(Paths.get(s).toUri().toString());
       mediaPlayer = new MediaPlayer(h);
       volume = 0;
   }

   public void addMusic(){
       stopMusic();
       String s = "Battle.mp3";
       Media h = new Media(Paths.get(s).toUri().toString());
       mediaPlayer = new MediaPlayer(h);
       mediaPlayer.play();
       setVolume( volume );
   }

    public void stopMusic(){
       mediaPlayer.stop();
    }

    public void addMenuMusic(){
        stopMusic();
        String s = "SlaytheSpire.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
        setVolume( volume );

    }
    public void setVolume( double volume ){
        mediaPlayer.setVolume(volume);
        this.volume = volume;
    }

}
