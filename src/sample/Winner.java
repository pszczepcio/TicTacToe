package sample;

import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Winner {
    private List<Long>list = new ArrayList<>();
    private String name;
    private String sign;
    private int fields;

    public Winner(String sign, String name) {
        this.sign = sign;
        this.name = name;
    }

    public boolean findWinner() {
        list.clear();
        list.add(checkRow(0,3));
        list.add(checkRow(3,6));
        list.add(checkRow(6,9));

        list.add(checkColumn(0, 3));
        list.add(checkColumn(1, 3));
        list.add(checkColumn(2, 3));

        list.add(checkDiagonal(0, 4));
        list.add(checkDiagonal(2, 2));

        return list.stream()
                .anyMatch(n -> n == 3);
    }

    private Long checkRow(int firstFieldInRow, int stepNextRow) {
         return IntStream.range(firstFieldInRow, stepNextRow)
                .filter(n -> Board.getBoard().get(n).getChildren().size() == 2)
                .mapToObj(s -> (Text)Board.getBoard().get(s).getChildren().get(1))
                .filter(d -> d.getText().equals(sign))
                .count();
    }

    private Long checkColumn(int firstFieldInColumn, int stepToNextColumn) {
        return IntStream.iterate(firstFieldInColumn, n -> n + stepToNextColumn)
                .limit(3)
                .filter(n -> Board.getBoard().get(n).getChildren().size() == 2)
                .mapToObj(s -> (Text)Board.getBoard().get(s).getChildren().get(1))
                .filter(d -> d.getText().equals(sign))
                .count();
    }

    private Long checkDiagonal(int firstFieldOnDiagonal, int stepNexTFieldOnDiagonal) {
        return IntStream.iterate(firstFieldOnDiagonal, n -> n + stepNexTFieldOnDiagonal)
                .limit(3)
                .filter(n -> Board.getBoard().get(n).getChildren().size() ==2)
                .mapToObj(n -> (Text)Board.getBoard().get(n).getChildren().get(1))
                .filter(n -> n.getText().equals(sign))
                .count();
    }

    public List<Integer> getWinnersFields() {
        List<Integer> winnersFields = new ArrayList<>();
        for (int i = 0 ; i < list.size() ; i++){
            if (list.get(i) == 3) {
                fields = i;
                break;
            }
        }
        switch (fields) {
            case 0:
                winnersFields.add(0);
                winnersFields.add(1);
                winnersFields.add(2);
                break;

            case 1:
                winnersFields.add(3);
                winnersFields.add(4);
                winnersFields.add(5);
                break;

            case 2:
                winnersFields.add(6);
                winnersFields.add(7);
                winnersFields.add(8);
                break;

            case 3:
                winnersFields.add(0);
                winnersFields.add(3);
                winnersFields.add(6);
                break;

            case 4:
                winnersFields.add(1);
                winnersFields.add(4);
                winnersFields.add(7);
                break;
            case 5:
                winnersFields.add(2);
                winnersFields.add(5);
                winnersFields.add(8);
                break;

            case 6:
                winnersFields.add(0);
                winnersFields.add(4);
                winnersFields.add(8);
                break;

            case 7:
                winnersFields.add(2);
                winnersFields.add(4);
                winnersFields.add(6);
                break;
        }
        return winnersFields;
    }
}
