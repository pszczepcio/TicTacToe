package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Player {
    private String name;

    public String createPlayer() {
        Stage windowPlayer = new Stage();
        windowPlayer.setTitle("Create Player");
        windowPlayer.setMinWidth(500);
        windowPlayer.setMinHeight(300);
        windowPlayer.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Player name: ");
        Label label1 = new Label("!...");
        label1.setTextFill(Color.RED);
        label1.setFont(Font.font(30));
        label1.setVisible(false);

        TextField textField = new TextField();
        textField.setMinWidth(50);
        textField.setMaxWidth(100);

        Button button = new Button("Confirm");
        button.setOnAction(e -> {
            if (textField.getText().equals("")) {
                textField.getPromptText();
                textField.setPromptText("Write name");
                label1.setVisible(true);
            }else {
                name = textField.getText();
                windowPlayer.close();
            }
        });

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(label, textField, button);
        vBox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox, label1);
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(hBox);
        windowPlayer.setScene(scene);
        windowPlayer.showAndWait();

        return name;
    }
}
