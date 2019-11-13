package sample.window;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertWindow {
    private static  boolean result;

    public static boolean show(String title, String message) {
        Stage window = new Stage();
        window.setTitle(title);
        window.setMinHeight(200);
        window.setMinWidth(500);
        window.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label(message);
        Button button1 = new Button("Yes");
        Button button2 = new Button("No");

        button1.setOnAction(e -> {
            result = true;
            window.close();
        });

        button2.setOnAction(e -> {
            result = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button1, button2);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return result;
    }
}
