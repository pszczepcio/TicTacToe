package sample.window;

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
import sample.PlayerDto;

public class TwoPlayersWindow {
    private String firstName;
    private String secondName;

    public PlayerDto createPlayers() {
        Stage windowPlayer = new Stage();
        windowPlayer.setTitle("Create players");
        windowPlayer.setMinWidth(500);
        windowPlayer.setMinHeight(300);
        windowPlayer.initModality(Modality.APPLICATION_MODAL);

        Label playerOne = new Label("First player name:");
        Label playerTwo = new Label("Second player name:");

        Label label = new Label("!... you have not entered\n players names\n or the names of both players\n are the same.");
        label.setTextFill(Color.RED);
        label.setFont(Font.font(10));
        label.setVisible(false);


        TextField playerOneTextField = new TextField();
        playerOneTextField.setMinWidth(50);
        playerOneTextField.setMaxWidth(100);

        TextField playerTwoTextField = new TextField();
        playerTwoTextField.setMinWidth(50);
        playerTwoTextField.setMaxWidth(100);


        windowPlayer.setOnCloseRequest(event -> {
            playerOneTextField.setText("");
            playerTwoTextField.setText("");
            windowPlayer.close();
        });
        Button button = new Button("Confirm");
        button.setOnAction(e -> {
            if (playerOneTextField.getText().equals("")
                    || playerTwoTextField.getText().equals("")
                    || playerOneTextField.getText().equals(playerTwoTextField.getText())) {
                playerOneTextField.setPromptText("Write name");
                playerTwoTextField.setPromptText("Write name");
                label.setVisible(true);

            }else {
                firstName = playerOneTextField.getText();
                secondName = playerTwoTextField.getText();
                windowPlayer.close();
            }
        });

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(playerOne, playerOneTextField, playerTwo, playerTwoTextField, button);
        vBox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox, label);
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(hBox);
        windowPlayer.setScene(scene);
        windowPlayer.showAndWait();

        return new PlayerDto(firstName, secondName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }
}
