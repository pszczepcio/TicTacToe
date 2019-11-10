package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Board extends StackPane {
    private final static int COLUMNS = 3;
    private final static int ROWS = 3;
    private final static double WIDTH = 200;
    private final static double HEIGHT = 200;
    private static List<StackPane> board = new ArrayList<>();

    public void drawBoard() {
        for (int i = 0 ; i < COLUMNS ; i++){
            for (int j = 0 ; j < ROWS ; j++){
                Rectangle rectangle = new Rectangle(i*WIDTH, j*HEIGHT, WIDTH , HEIGHT);
                rectangle.setFill(Color.WHITE);
                rectangle.setStroke(Color.BLACK);
                board.add(new StackPane(rectangle));
            }
        }
    }

    public static List<StackPane> getBoard() {
        return board;
    }

    public static void setRectangleFill(List<Integer> list) {
        for (int i = 0 ; i < list.size() ; i++) {
            Rectangle rectangleWithNewFill = (Rectangle) getBoard().get(list.get(i)).getChildren().get(0);
            rectangleWithNewFill.setFill(Color.RED);
        }
    }
}
