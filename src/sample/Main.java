package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.alert.AlertWindow;
import sample.menu.FileMenu;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane borderPane = new BorderPane();
    private Player player = new Player();
    private TilePane pane = new TilePane();
    private Board board = new Board();
    private FileMenu menu = new FileMenu();

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        setStage();
        setMenuBar();

        borderPane.setCenter(setText());
        borderPane.setTop(menu.getMenuBar());

        Scene scene = new Scene(borderPane, 1000,700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void closeProgram(Stage stage) {
        boolean close = AlertWindow.show("Close", "Are you sure you want to close?");
        if (close) {
            stage.close();
        }
    }

    private void setStage(){
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            closeProgram(primaryStage);
        });
    }

    private void setMenuBar() {
        menu.createMenuBar();
        menu.getCloseGame().setOnAction(e -> closeProgram(primaryStage));
        menu.getNewGame().setOnAction(e -> {
            String name = player.createPlayer();
            System.out.println(name);
            board.drawBoard();
            pane.getChildren().addAll(board.getBoard());
            borderPane.setCenter(pane);
            BorderPane.setAlignment(borderPane.getCenter(), Pos.CENTER);
            BorderPane.setMargin(borderPane.getCenter(), new Insets(30,200,50,200));
        });
    }

    private Text setText() {
        Text text = new Text("Tic Tac Toe\n    Game");
        text.setFont(Font.font(100));
        text.setFill(Color.BLUE);
        return text;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
