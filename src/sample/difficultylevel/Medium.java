package sample.difficultylevel;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import sample.Board;
import sample.Sign;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Medium implements Difficulty {
    private long numberCharactersOnTheFirstDiagonal;
    private long numberCharactersOnTheSecondDiagonal;
    private List<StackPane> stackPaneList1;
    private List<StackPane> stackPaneList2;

    @Override
    public void computerMove(String sign) {
        checkDiagonal(sign);

        if (Board.getBoard().get(4).getChildren().size() == 1) {
            Board.getBoard().get(4).getChildren().add(Sign.getSign(sign));
        }else if(checkingOwnMarkInColumn(0, sign)) {
            IntStream.iterate(0,n -> n + 3)
                    .limit(3)
                    .filter(n -> Board.getBoard().get(n).getChildren().size() == 1)
                    .mapToObj(n -> Board.getBoard().get(n))
                    .forEach(n -> n.getChildren().add(Sign.getSign(sign)));
        }else if(checkingOwnMarkInColumn(1, sign)) {
            IntStream.iterate(1, n -> n + 3)
                    .limit(3)
                    .filter(n -> Board.getBoard().get(n).getChildren().size() == 1)
                    .mapToObj(n -> Board.getBoard().get(n))
                    .forEach(n -> n.getChildren().add(Sign.getSign(sign)));
        }else if (numberCharactersOnTheFirstDiagonal == 2
                && stackPaneList1.size() != 0){
            stackPaneList1.get(0).getChildren().add(Sign.getSign(sign));
        }else if (numberCharactersOnTheSecondDiagonal == 2
                && stackPaneList2.size() != 0) {
            stackPaneList2.get(0).getChildren().add(Sign.getSign(sign));
        }else if (checkingOpponentMarkInColumn(0, sign)){
            IntStream.iterate(0,n -> n + 3)
                    .limit(3)
                    .filter(n -> Board.getBoard().get(n).getChildren().size() == 1)
                    .mapToObj(n -> Board.getBoard().get(n))
                    .forEach(n -> n.getChildren().add(Sign.getSign(sign)));
        }else if (checkingOpponentMarkInColumn(1, sign)){
            IntStream.iterate(1,n -> n + 3)
                    .limit(3)
                    .filter(n -> Board.getBoard().get(n).getChildren().size() == 1)
                    .mapToObj(n -> Board.getBoard().get(n))
                    .forEach(n -> n.getChildren().add(Sign.getSign(sign)));
        }else if (checkingOpponentMarkInColumn(2, sign)){
            IntStream.iterate(2,n -> n + 3)
                    .limit(3)
                    .filter(n -> Board.getBoard().get(n).getChildren().size() == 1)
                    .mapToObj(n -> Board.getBoard().get(n))
                    .forEach(n -> n.getChildren().add(Sign.getSign(sign)));
        }else if (checkingOpponentMarkInRow(0, sign)){
            IntStream.iterate(0,n -> n + 1)
                    .limit(3)
                    .filter(n -> Board.getBoard().get(n).getChildren().size() == 1)
                    .mapToObj(n -> Board.getBoard().get(n))
                    .forEach(n -> n.getChildren().add(Sign.getSign(sign)));
        }else if (checkingOpponentMarkInRow(3, sign)){
            IntStream.iterate(3,n -> n + 1)
                    .limit(3)
                    .filter(n -> Board.getBoard().get(n).getChildren().size() == 1)
                    .mapToObj(n -> Board.getBoard().get(n))
                    .forEach(n -> n.getChildren().add(Sign.getSign(sign)));
        }else if (checkingOpponentMarkInRow(6, sign)){
            IntStream.iterate(6,n -> n + 1)
                    .limit(3)
                    .filter(n -> Board.getBoard().get(n).getChildren().size() == 1)
                    .mapToObj(n -> Board.getBoard().get(n))
                    .forEach(n -> n.getChildren().add(Sign.getSign(sign)));
        }else {
            Easy easy = new Easy();
            easy.computerMove(sign);
        }
    }

    public void checkDiagonal(String sign) {
        numberCharactersOnTheFirstDiagonal = IntStream.iterate(0, n -> n + 4)
                .limit(3)
                .filter(n -> Board.getBoard().get(n).getChildren().size() == 2)
                .mapToObj(n -> (Text)Board.getBoard().get(n).getChildren().get(1))
                .filter(n -> !n.getText().equals(sign))
                .count();

        stackPaneList1 = IntStream.iterate(0, n -> n + 4)
                .limit(3)
                .filter(n -> Board.getBoard().get(n).getChildren().size() == 1)
                .mapToObj(n -> Board.getBoard().get(n))
                .collect(Collectors.toList());

        numberCharactersOnTheSecondDiagonal = IntStream.iterate(2, n -> n + 2)
                .limit(3)
                .filter(n -> Board.getBoard().get(n).getChildren().size() ==2)
                .mapToObj(n -> (Text)Board.getBoard().get(n).getChildren().get(1))
                .filter(n -> !n.getText().equals(sign))
                .count();

        stackPaneList2 = IntStream.iterate(2, n -> n + 2)
                .limit(3)
                .filter(n -> Board.getBoard().get(n).getChildren().size() == 1)
                .mapToObj(n -> Board.getBoard().get(n))
                .collect(Collectors.toList());
    }

    private boolean checkingOpponentMarkInColumn(int firstFieldInColumn, String sign){
        long numberOfColumns = IntStream.iterate(firstFieldInColumn, n -> n + 3)
                .limit(2)
                .filter(n -> Board.getBoard().get(n).getChildren().size() == 2)
                .mapToObj(n -> (Text)Board.getBoard().get(n).getChildren().get(1))
                .filter(n -> !n.getText().equals(sign))
                .count();

        long emptyfield = IntStream.iterate(firstFieldInColumn,n -> n + 3)
                .limit(3)
                .filter(n -> Board.getBoard().get(n).getChildren().size() == 1)
                .count();

        return numberOfColumns == 2 && emptyfield == 1;
    }

    private boolean checkingOpponentMarkInRow(int firstFieldInRow, String sign) {
        long numberOfRows = IntStream.iterate(firstFieldInRow, n -> n + 1)
                .limit(3)
                .filter(n -> Board.getBoard().get(n).getChildren().size() == 2)
                .mapToObj(n -> (Text)Board.getBoard().get(n).getChildren().get(1))
                .filter(n -> !n.getText().equals(sign))
                .count();
        long emptyfield = findEmptyField(firstFieldInRow, 1);
        return numberOfRows == 2 && emptyfield == 1;
    }

    private boolean checkingOwnMarkInColumn(int firstFieldInColumn, String sign) {
        long signsInColumn = IntStream.iterate(firstFieldInColumn, n -> n + 3)
                .limit(3)
                .filter(n -> Board.getBoard().get(n).getChildren().size() == 2)
                .mapToObj(n -> (Text)Board.getBoard().get(n).getChildren().get(1))
                .filter(n -> n.getText().equals(sign))
                .count();

        long emptyfield = findEmptyField(firstFieldInColumn, 3);
        return signsInColumn == 2 && emptyfield == 1;
    }

    private long findEmptyField(int firstField, int step){
        return IntStream.iterate(firstField,n -> n + step)
                .limit(3)
                .filter(n -> Board.getBoard().get(n).getChildren().size() == 1)
                .count();
    }
}
