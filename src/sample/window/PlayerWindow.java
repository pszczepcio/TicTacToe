package sample.window;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import sample.PlayerDto;

public class PlayerWindow {
    private String name;
    private double difficultyLevel;
    private final static double MIN = 0;
    private final static double MAX = 1;
    private final static double STEP = 0;

    public PlayerDto createPlayer() {
        Stage windowPlayer = new Stage();

        windowPlayer.setTitle("Create PlayerWindow");
        windowPlayer.setMinWidth(500);
        windowPlayer.setMinHeight(300);
        windowPlayer.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("PlayerWindow name: ");
        Label label1 = new Label("!...");
        label1.setTextFill(Color.RED);
        label1.setFont(Font.font(30));
        label1.setVisible(false);


        TextField textField = new TextField();
        textField.setMinWidth(50);
        textField.setMaxWidth(100);

        windowPlayer.setOnCloseRequest(event -> {
            textField.setText("");
            windowPlayer.close();
        });

        Slider slider = new Slider(MIN, MAX, STEP);
        slider.setMajorTickUnit(1.0);
        slider.setMinorTickCount(0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        slider.setBlockIncrement(1);
        slider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                if (object == 0) return "Easy";
                return "Medium";
            }

            @Override
            public Double fromString(String string) {
                return null;
            }
        });


        Button button = new Button("Confirm");
        button.setOnAction(e -> {
            if (textField.getText().equals("")) {
                textField.getPromptText();
                textField.setPromptText("Write name");
                label1.setVisible(true);

            }else {
                name = textField.getText();
                difficultyLevel = slider.getValue();
                windowPlayer.close();
            }
        });

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(label, textField, slider, button);
        vBox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox, label1);
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(hBox);
        windowPlayer.setScene(scene);
        windowPlayer.showAndWait();

        return new PlayerDto(name, difficultyLevel);
    }
}
