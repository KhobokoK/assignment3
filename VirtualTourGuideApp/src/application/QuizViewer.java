package application;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class QuizViewer {
    private MediaPlayer backgroundPlayer;

    public static void launchQuiz() {
        new QuizViewer().show();
    }

    public void show() {
        Stage stage = new Stage();

        // ✅ Background music setup using resource path
        try {
            Media bgMusic = new Media(getClass().getResource("/media/quizbackground.mp3").toExternalForm());
            backgroundPlayer = new MediaPlayer(bgMusic);
            backgroundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundPlayer.setVolume(0.5);
            backgroundPlayer.play();
        } catch (Exception e) {
            System.err.println("Failed to load background music: " + e.getMessage());
        }

        // Questions
        String[][] questions = {
                {"What is the capital city of Lesotho?", "Maseru", "Teyateyaneng", "Leribe", "Maseru"},
                {"Which river runs through Lesotho?", "Orange River", "Nile", "Zambezi", "Orange River"},
                {"What currency is used in Lesotho?", "Loti", "Rand", "Dollar", "Loti"},
                {"What is the highest point in Lesotho?", "Thabana Ntlenyana", "Mount Kilimanjaro", "Drakensberg", "Thabana Ntlenyana"},
                {"Lesotho is completely surrounded by which country?", "South Africa", "Zimbabwe", "Namibia", "South Africa"}
        };

        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-alignment: center;");

        // ✅ Set background image from resources
        try {
            BackgroundImage bgImage = new BackgroundImage(
                    new Image(getClass().getResource("/images/quiz2.jpg").toExternalForm(), 600, 500, false, true),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT
            );
            root.setBackground(new Background(bgImage));
        } catch (Exception e) {
            System.err.println("Failed to load background image: " + e.getMessage());
        }

        Label questionCounter = new Label();
        questionCounter.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
        questionCounter.getStyleClass().add("question-counter"); // ✅ Apply CSS class

        Label questionLabel = new Label();
        questionLabel.setWrapText(true);
        questionLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");
        questionLabel.getStyleClass().add("main-question"); // ✅ Apply CSS class

        ToggleGroup group = new ToggleGroup();
        RadioButton opt1 = new RadioButton();
        RadioButton opt2 = new RadioButton();
        RadioButton opt3 = new RadioButton();
        opt1.setToggleGroup(group);
        opt2.setToggleGroup(group);
        opt3.setToggleGroup(group);

        opt1.getStyleClass().add("radio-button");
        opt2.getStyleClass().add("radio-button");
        opt3.getStyleClass().add("radio-button");

        Button submit = new Button("✔️ Submit Answer");
        submit.getStyleClass().add("main-button");
        Button hint = new Button("💡 Hint");
        hint.getStyleClass().add("main-button");

        int[] index = {0};

        updateQuestion(questions, index[0], questionCounter, questionLabel, opt1, opt2, opt3);

        submit.setOnAction(e -> {
            RadioButton selected = (RadioButton) group.getSelectedToggle();
            if (selected == null) {
                showAlert(Alert.AlertType.WARNING, "Please select an answer.");
                return;
            }

            boolean isCorrect = selected.getText().equals(questions[index[0]][4]);
            animateFeedback(root, isCorrect);

            if (isCorrect) {
                showAlert(Alert.AlertType.INFORMATION, "✅ Correct!");
            } else {
                showAlert(Alert.AlertType.ERROR, "❌ Incorrect. Correct Answer: " + questions[index[0]][4]);
            }

            index[0]++;
            if (index[0] < questions.length) {
                group.selectToggle(null);
                updateQuestion(questions, index[0], questionCounter, questionLabel, opt1, opt2, opt3);
            } else {
                showCompletionEffect(root);
                showAlert(Alert.AlertType.INFORMATION, "🎉 Quiz Complete!");
                if (backgroundPlayer != null) backgroundPlayer.stop();
                stage.close();
            }
        });

        hint.setOnAction(e -> showAlert(Alert.AlertType.INFORMATION, "Think about what you know about Lesotho's geography and culture!"));

        root.getChildren().addAll(questionCounter, questionLabel, opt1, opt2, opt3, submit, hint);

        Scene scene = new Scene(root, 600, 500);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setTitle("🎓 Lesotho Quiz Challenge");
        stage.setScene(scene);
        stage.show();
    }

    private void updateQuestion(String[][] q, int i, Label count, Label question, RadioButton o1, RadioButton o2, RadioButton o3) {
        count.setText("📘 Question " + (i + 1) + " of " + q.length);
        question.setText(q[i][0]);
        o1.setText(q[i][1]);
        o2.setText(q[i][2]);
        o3.setText(q[i][3]);

        FadeTransition fade = new FadeTransition(Duration.millis(500), question);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        ScaleTransition scale = new ScaleTransition(Duration.millis(500), question);
        scale.setFromX(0.8);
        scale.setToX(1.0);
        scale.setFromY(0.8);
        scale.setToY(1.0);
        scale.play();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void animateFeedback(VBox container, boolean correct) {
        String color = correct ? "#c8e6c9" : "#ffcdd2";
        container.setStyle("-fx-background-color: " + color + "; -fx-alignment: center;");

        FadeTransition fade = new FadeTransition(Duration.seconds(0.5), container);
        fade.setFromValue(0.5);
        fade.setToValue(1.0);
        fade.setAutoReverse(true);
        fade.setCycleCount(2);
        fade.setOnFinished(e -> {
            container.setStyle("-fx-background-color: transparent; -fx-alignment: center;");
        });
        fade.play();
    }

    private void showCompletionEffect(VBox container) {
        Label celebration = new Label("🎊🎉 Congratulations! 🎉🎊");
        celebration.setFont(new Font("Arial", 26));
        celebration.setStyle("-fx-text-fill: yellow;");
        celebration.getStyleClass().add("celebration"); // ✅ Apply CSS class
        container.getChildren().add(celebration);

        FadeTransition fade = new FadeTransition(Duration.seconds(1.5), celebration);
        fade.setFromValue(0);
        fade.setToValue(1.0);
        fade.setCycleCount(4);
        fade.setAutoReverse(true);
        fade.play();
    }
}
