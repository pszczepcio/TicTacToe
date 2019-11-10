package sample;

import javafx.scene.input.MouseEvent;

public interface Game {
    WinnerDto play(MouseEvent event);
}
