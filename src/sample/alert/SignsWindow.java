package sample.alert;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SignsWindow {

    public static void showSigns(final String playerOneName, final String playerTwoName, final String playerOneSign, final String playerTwoSign ) {
        Stage window = new Stage();
        window.setTitle("Players");
        window.setMinHeight(200);
        window.setMinWidth(500);
        window.initModality(Modality.APPLICATION_MODAL);

        Label playerOne = new Label(playerOneName + " player sign: " + playerOneSign);
        Label playerTwo = new Label(playerTwoName + " sign: " + playerTwoSign);

        Button button = new Button("Ok");
        button.setOnAction(event -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(playerOne, playerTwo, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
