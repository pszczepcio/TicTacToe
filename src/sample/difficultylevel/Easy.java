package sample.difficultylevel;

import javafx.scene.layout.StackPane;
import sample.Board;
import sample.Sign;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Easy implements Difficulty {

    @Override
    public void computerMove(final String sign) {
        Random random = new Random();
        List<StackPane> list = Board.getBoard().stream()
                .filter(stackPane -> stackPane.getChildren().size() == 1)
                .collect(Collectors.toList());

        if (Board.getBoard().get(4).getChildren().size() == 1) {
            Board.getBoard().get(4).getChildren().add(Sign.getSign(sign));
        }else if (list.size() != 0 && list.size() > 1){
            int number = random.ints(0, list.size() - 1).findAny().getAsInt();
            list.get(number).getChildren().add(Sign.getSign(sign));
        }else if (list.size() == 1) {
            list.get(0).getChildren().add(Sign.getSign(sign));
        }
    }
}
