package application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

public class MapViewer {
    public static void launchMap() {
        Stage stage = new Stage();

        Image mapImage;
        try {
            mapImage = new Image(Objects.requireNonNull(MapViewer.class.getResource("/media/lesotho_map.jpeg")).toExternalForm());
        } catch (NullPointerException e) {
            System.err.println("Map image not found.");
            return;
        }

        ImageView imageView = new ImageView(mapImage);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(800);
        imageView.getStyleClass().add("image-view");

        StackPane root = new StackPane(imageView);
        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("Lesotho Map - Tour Landmarks");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


}
