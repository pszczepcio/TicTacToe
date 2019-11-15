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
import sample.typeofgame.TwoPlayerGame;
import sample.window.*;
import sample.difficultylevel.Difficulty;
import sample.difficultylevel.Easy;
import sample.difficultylevel.Medium;
import sample.menu.FileMenu;
import sample.typeofgame.SinglePlayerGame;
import java.util.List;

public class Main extends Application {
    private final static String MESSAGEWINNER = "The winner is:";
    private final static String DEAD_HEAT = "Dead heat";
    private Stage primaryStage;
    public static BorderPane borderPane = new BorderPane();
    private static TilePane pane = new TilePane();
    private Board board = new Board();
    private FileMenu menu = new FileMenu();
    private Difficulty difficulty;

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
        menu.getSinglePlayer().setOnAction(e -> {
            PlayerWindow playerWindow = new PlayerWindow();
            PlayerDto playerDto = playerWindow.createPlayer();
            try {
                if (!playerDto.getFirstPlayerName().equals("")) {
                    difficulty = choiceOfDifficultyLevel(playerDto.getDifficultyLevel());
                    clearBoard();
                    initBoard();
                    startSinglePlayerGame(CharacterDraw.characterDetermination(), playerDto);
                }
            }catch (NullPointerException ex){
                borderPane.setCenter(setText());
                System.out.println("Window shut down error \"PlayerWindow\" " + ex);
            }
        });

        menu.getTwoPlayers().setOnAction(e -> {
            TwoPlayersWindow twoPlayersWindow = new TwoPlayersWindow();
            PlayerDto twoPlayers = twoPlayersWindow.createPlayers();
            try {
                if (!twoPlayers.getFirstPlayerName().equals("")
                        || !twoPlayers.getSecondPlayerName().equals("")) {
                    clearBoard();
                    initBoard();
                    startTwoPlayersGame(CharacterDraw.characterDetermination(), twoPlayers);
                }
            }catch (NullPointerException ex) {
                borderPane.setCenter(setText());
                System.out.println("Window shut down error \"TwoPlayersWindow\" " + ex);
            }
        });
    }

    public static Text setText() {
        Text text = new Text("Tic Tac Toe\n    Game");
        text.setFont(Font.font(100));
        text.setFill(Color.BLUE);
        return text;
    }

    private void startSinglePlayerGame(List<String> signslist, PlayerDto playerDto) {
        SinglePlayerGame singlePlayerGame = new SinglePlayerGame(playerDto.getFirstPlayerName(),difficulty, signslist);
        SignsWindow.showSigns(playerDto.getFirstPlayerName(), "Computer",signslist.get(0), signslist.get(1));
        if (signslist.get(1).equals("X")){
            difficulty.computerMove("X");
        }
        for (StackPane stackPane : Board.getBoard()) {
            stackPane.setOnMouseClicked(event -> singlePlayerGame.play(event));
        }
    }

    private void startTwoPlayersGame(List<String> signslist, PlayerDto playerDto) {
        TwoPlayerGame twoPlayerGame = new TwoPlayerGame(playerDto, signslist);
        SignsWindow.showSigns(playerDto.getFirstPlayerName(), playerDto.getSecondPlayerName(),
                                signslist.get(0), signslist.get(1));
        for (StackPane stackPane : Board.getBoard()) {
            stackPane.setOnMouseClicked(event -> {
                WinnerDto winnerDto = twoPlayerGame.play(event);
                findWinnerPlayer(winnerDto);
            });
        }
    }

    public static void clearBoard() {
        Board.getBoard().clear();
        pane.getChildren().clear();
    }

    private void initBoard() {
        SinglePlayerGame.setWin(false);
        board.drawBoard();
        pane.getChildren().addAll(Board.getBoard());
        borderPane.setCenter(pane);
        BorderPane.setAlignment(borderPane.getCenter(), Pos.CENTER);
        BorderPane.setMargin(borderPane.getCenter(), new Insets(30,200,50,200));
    }

    private void changeColorWinningFields(final List<Integer> fieldsList) {
        Board.setRectangleFill(fieldsList);
    }

    private Difficulty choiceOfDifficultyLevel (double difficultyLevel) {
        if (difficultyLevel == 0){
            return new Easy();
        }
        return new Medium();
    }

    private void findWinnerPlayer(WinnerDto winnerDto) {
        if(!winnerDto.getWinnerName().equals("") && !winnerDto.getWinnerName().equals("Dead heat")){
            changeColorWinningFields(winnerDto.getWinnersFields());
            WinnerWindow.showWinner(winnerDto.getWinnerName(), MESSAGEWINNER, false);
            clearBoard();
            borderPane.setCenter(setText());
        }else if (!winnerDto.getWinnerName().equals("") && winnerDto.getWinnerName().equals("Dead heat")){
            WinnerWindow.showWinner("", DEAD_HEAT, false);
            clearBoard();
            borderPane.setCenter(setText());
        }
    }

    public static String getMESSAGEWINNER() {
        return MESSAGEWINNER;
    }

    public static String getDeadHeat() {
        return DEAD_HEAT;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
