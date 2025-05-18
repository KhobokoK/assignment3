package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class MediaPlayerViewer {

    public static void launchMediaPlayer() {
        Stage stage = new Stage();

        // Load media files
        Media audioMedia;
        Media videoMedia;
        try {
            audioMedia = new Media(MediaPlayerViewer.class.getResource("/media/lesotho_audio.mp3").toExternalForm());
            videoMedia = new Media(MediaPlayerViewer.class.getResource("/media/lesotho_video.mp4").toExternalForm());
        } catch (NullPointerException e) {
            System.err.println("Media files not found.");
            return;
        }

        // Create MediaPlayers
        MediaPlayer audioPlayer = new MediaPlayer(audioMedia);
        MediaPlayer videoPlayer = new MediaPlayer(videoMedia);

        // MediaView to show video output
        MediaView mediaView = new MediaView(videoPlayer);
        mediaView.getStyleClass().add("media-view");
        mediaView.setFitWidth(700);
        mediaView.setPreserveRatio(true);

        // Buttons to control audio and video
        Button playAudioButton = new Button("Play Audio Guide");
        playAudioButton.setOnAction(e -> audioPlayer.play());

        Button playVideoButton = new Button("Play Video Guide");
        playVideoButton.setOnAction(e -> videoPlayer.play());

        // Layout container
        VBox root = new VBox(15, playAudioButton, playVideoButton, mediaView);
        root.getStyleClass().add("root");

        // Scene setup
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(MediaPlayerViewer.class.getResource("/style.css").toExternalForm());

        // Stage setup
        stage.setTitle("Audio & Video Guide");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
