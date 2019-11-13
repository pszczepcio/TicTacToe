package sample;

public class PlayerDto {
    private String firstPlayerName;
    private String secondPlayerName;
    private double difficultyLevel;

    public PlayerDto(String firstPlayerName, double difficultyLevel) {
        this.firstPlayerName = firstPlayerName;
        this.difficultyLevel = difficultyLevel;
    }

    public PlayerDto(String firstPlayerName, String secondPlayerName) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public double getDifficultyLevel() {
        return difficultyLevel;
    }
}
