package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.alert.AlertWindow;
import sample.alert.SignsWindow;
import sample.alert.WinnerWindow;
import sample.menu.FileMenu;
import sample.typeofgame.SinglePlayer;
import java.util.List;

public class Main extends Application {
    private final static String MESSAGEWINNER = "The winner is:";
    private final static String DEAD_HEAT = "Dead heat";
    private Stage primaryStage;
    private BorderPane borderPane = new BorderPane();
    private Player player = new Player();
    private TilePane pane = new TilePane();
    private Board board = new Board();
    private FileMenu menu = new FileMenu();
    private String name;

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
            name = player.createPlayer();
            clearBoard();
            initBoard();
            startSinglePlayerGame(CharacterDraw.characterDetermination());
        });
    }

    private Text setText() {
        Text text = new Text("Tic Tac Toe\n    Game");
        text.setFont(Font.font(100));
        text.setFill(Color.BLUE);
        return text;
    }

    private void startSinglePlayerGame(List<String> signslist) {
        SinglePlayer singlePlayer = new SinglePlayer(name, signslist);
        SignsWindow.showSigns(name, "Computer",signslist.get(0), signslist.get(1));
        if (signslist.get(1).equals("X")){
            singlePlayer.computerMove();
        }
        for (StackPane stackPane : Board.getBoard()) {
            stackPane.setOnMouseClicked(event -> {
                WinnerDto winnerDto = singlePlayer.play(event);
                if(!winnerDto.getWinnerName().equals("") && !winnerDto.getWinnerName().equals("Dead heat")){
                    changeColorWinningFields(winnerDto.getWinnersFields());
                    WinnerWindow.showWinner(winnerDto.getWinnerName(), MESSAGEWINNER);
                }else if (!winnerDto.getWinnerName().equals("") && winnerDto.getWinnerName().equals("Dead heat")){
                    WinnerWindow.showWinner("", DEAD_HEAT);
                }
            });
        }
    }

    private void clearBoard() {
        Board.getBoard().clear();
        pane.getChildren().clear();
    }

    private void initBoard() {
        SinglePlayer.setWin(false);
        board.drawBoard();
        pane.getChildren().addAll(Board.getBoard());
        borderPane.setCenter(pane);
        BorderPane.setAlignment(borderPane.getCenter(), Pos.CENTER);
        BorderPane.setMargin(borderPane.getCenter(), new Insets(30,200,50,200));
    }

    private void changeColorWinningFields(final List<Integer> fieldsList) {
        Board.setRectangleFill(fieldsList);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
