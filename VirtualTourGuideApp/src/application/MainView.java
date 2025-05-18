package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MainView extends StackPane {

    public MainView() {
        // Load the background video
        String videoPath = getClass().getResource("/media/mountain.mp4").toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the video
        mediaPlayer.setAutoPlay(true);

        MediaView backgroundView = new MediaView(mediaPlayer);
        backgroundView.setFitWidth(1024); // Adjust width as needed
        backgroundView.setPreserveRatio(true);
        backgroundView.setOpacity(0.6); // Optional: dim the video

        // UI Buttons
        Button mapButton = createStyledButton("View Map");
        Button mediaButton = createStyledButton("Media Tour");
        Button quizButton = createStyledButton("Take Quiz");
        Button musicButton = createStyledButton("Play Music");

        // Button actions
        mapButton.setOnAction(e -> MapViewer.launchMap());
        mediaButton.setOnAction(e -> MediaPlayerViewer.launchMediaPlayer());
        quizButton.setOnAction(e -> new QuizViewer().show());
        musicButton.setOnAction(e -> MusicPlayer.toggleMusic());

        VBox uiLayer = new VBox(20, mapButton, mediaButton, quizButton, musicButton);
        uiLayer.setAlignment(Pos.CENTER);

        this.getChildren().addAll(backgroundView, uiLayer);
        this.setStyle("-fx-padding: 50;");
    }

    private Button createStyledButton(String label) {
        Button btn = new Button(label);
        btn.getStyleClass().add("main-button");
        return btn;
    }

}
