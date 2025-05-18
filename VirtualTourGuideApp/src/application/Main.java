package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Load background video
        String videoPath = getClass().getResource("/media/mountain.mp4").toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(1024);
        mediaView.setPreserveRatio(true);
        mediaView.setOpacity(0.6); // Dimmed effect

        // Buttons
        Button viewMapButton = new Button("View Map");
        Button mediaTourButton = new Button("Media Tour");
        Button takeQuizButton = new Button("Take Quiz");
        Button playMusicButton = new Button("Play/Pause Music");

        Object MapViewer = null;
        viewMapButton.setOnAction(e -> MapViewer.hashCode());
        mediaTourButton.setOnAction(e -> MediaPlayerViewer.launchMediaPlayer());
        takeQuizButton.setOnAction(e -> new QuizViewer().show());
        playMusicButton.setOnAction(e -> MusicPlayer.toggleMusic());

        // Apply custom button styles
        viewMapButton.getStyleClass().add("main-button");
        mediaTourButton.getStyleClass().add("main-button");
        takeQuizButton.getStyleClass().add("main-button");
        playMusicButton.getStyleClass().add("main-button");

        VBox buttonBox = new VBox(20, viewMapButton, mediaTourButton, takeQuizButton, playMusicButton);
        buttonBox.setAlignment(Pos.CENTER);

        StackPane root = new StackPane(mediaView, buttonBox);
        root.setStyle("-fx-padding: 50;");

        Scene scene = new Scene(root, 1024, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setTitle("Virtual Tour Guide");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
