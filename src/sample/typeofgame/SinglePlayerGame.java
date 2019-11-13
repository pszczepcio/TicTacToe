package sample.typeofgame;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import sample.*;
import sample.difficultylevel.Difficulty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SinglePlayerGame implements Game {
    private final static String COMPUTER = "Computer";
    private boolean movement = false;
    private String playerName;
    private String playerSign;
    private String computerSign;
    private Difficulty difficulty;
    private static boolean win = false;
    private WinnerDto winnerDto = new WinnerDto("", new ArrayList<>());

    public SinglePlayerGame(String playerName, final Difficulty difficulty, final List<String> signslist) {
        this.playerName = playerName;
        this.difficulty = difficulty;
        playerSign = signslist.get(0);
        computerSign = signslist.get(1);
    }

    @Override
    public WinnerDto play(MouseEvent event) {
        if (!movement && !win ) {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                for (StackPane pane : Board.getBoard()) {
                    if (event.getSource().equals(pane) && pane.getChildren().size() == 1) {
                        pane.getChildren().add(Sign.getSign(playerSign));
                        movement = true;
                    }
                    find(playerSign,playerName);
                }
            }
        }
        if(movement && !win){
            difficulty.computerMove(computerSign);
            find(computerSign, COMPUTER);
            movement = false;
        }
        if (!win && checkAllSign()) {
            winnerDto = new WinnerDto("Dead heat", new ArrayList<>());
        }
        return winnerDto;
    }

    private void find(String sign, String name) {
        Winner winner = new Winner(sign, name);
        if(winner.findWinner()) {
            win = true;
            winnerDto = new WinnerDto(name, winner.getWinnersFields());
        }
    }

    private boolean checkAllSign () {
        List<StackPane> stackPaneList = Board.getBoard().stream()
                .filter(stackPane -> stackPane.getChildren().size() == 2)
                .collect(Collectors.toList());
        return stackPaneList.stream()
                .anyMatch(n ->stackPaneList.size() == 9);
    }

    public static void setWin(boolean win) {
        SinglePlayerGame.win = win;
    }
}
