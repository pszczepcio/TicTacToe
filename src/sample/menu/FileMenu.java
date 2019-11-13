package sample.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class FileMenu {
    private MenuBar menuBar;
    private Menu fileMenu = new Menu("File");
    private MenuItem singlePlayer = new MenuItem("Single player");
    private MenuItem twoPlayers = new MenuItem("Two players");
    private MenuItem closeGame = new MenuItem("Close game");

    public void createMenuBar() {
        fileMenu.getItems().addAll(singlePlayer, twoPlayers, closeGame);
        menuBar = new MenuBar(fileMenu);
    }

    public MenuItem getSinglePlayer() {
        return singlePlayer;
    }

    public MenuItem getTwoPlayers() {
        return twoPlayers;
    }

    public MenuItem getCloseGame() {
        return closeGame;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}

