package application;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URISyntaxException;

public class MusicPlayer {
    private static MediaPlayer musicPlayer;
    private static boolean isPlaying = false;

    public static void toggleMusic() {
        try {
            if (musicPlayer == null) {
                String path = MusicPlayer.class.getResource("/media/lesotho_audio.mp3").toURI().toString();
                Media media = new Media(path);
                musicPlayer = new MediaPlayer(media);
                musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            }

            if (isPlaying) {
                musicPlayer.pause();
                isPlaying = false;
            } else {
                musicPlayer.play();
                isPlaying = true;
            }
        } catch (URISyntaxException | NullPointerException e) {
            System.err.println("Error loading music file: " + e.getMessage());
        }
    }

    public static void stopMusic() {
        if (musicPlayer != null) {
            musicPlayer.stop();
            isPlaying = false;
        }
    }

}
