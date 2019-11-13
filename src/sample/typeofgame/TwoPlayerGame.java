package sample.typeofgame;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import sample.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TwoPlayerGame implements Game {

    private static int countClick;
    private String playerOneName;
    private String playerTwoName;
    private String playerOneSign;
    private String playerTwoSign;
    private WinnerDto winnerDto = new WinnerDto("", new ArrayList<>());

    public TwoPlayerGame(final PlayerDto playerDto, List<String> signList) {
        this.playerOneName = playerDto.getFirstPlayerName();
        this.playerTwoName = playerDto.getSecondPlayerName();
        this.playerOneSign = signList.get(0);
        this.playerTwoSign = signList.get(1);
        if (playerOneSign.equals("X")){
            countClick = 0;
        }else {
            countClick = 1;
        }
    }

    @Override
    public WinnerDto play(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY) && countClick % 2 == 0) {
            for (StackPane pane : Board.getBoard()) {
                if (event.getSource().equals(pane) && pane.getChildren().size() == 1) {
                    pane.getChildren().add(Sign.getSign(playerOneSign));
                    find(playerOneSign, playerOneName);
                    countClick++;
                }
            }
        } else if (event.getButton().equals(MouseButton.PRIMARY) && countClick % 2 == 1) {
            for (StackPane pane : Board.getBoard()) {
                if (event.getSource().equals(pane) && pane.getChildren().size() == 1) {
                    pane.getChildren().add(Sign.getSign(playerTwoSign));
                    find(playerTwoSign,playerTwoName);
                    countClick--;
                }
            }
        }

        if (checkAllSign()){
            winnerDto = new WinnerDto("Dead heat", new ArrayList<>());
        }
        return winnerDto;
    }

    private void find(String sign, String name) {
        Winner winner = new Winner(sign, name);
        if(winner.findWinner()) {
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
}
