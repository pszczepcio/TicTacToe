package sample;

import java.util.List;

public class WinnerDto {
    private String winnerName;
    private List<Integer> winnersFields;

    public WinnerDto(String winnerName, List<Integer> winnersFields) {
        this.winnerName = winnerName;
        this.winnersFields = winnersFields;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public List<Integer> getWinnersFields() {
        return winnersFields;
    }
}
