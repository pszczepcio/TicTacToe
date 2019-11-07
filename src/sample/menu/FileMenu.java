package sample.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class FileMenu {
    private MenuBar menuBar;
    private Menu fileMenu = new Menu("File");
    private MenuItem newGame = new MenuItem("new game");
    private MenuItem closeGame = new MenuItem("close game");

    public void createMenuBar() {
        fileMenu.getItems().addAll(newGame, closeGame);
        menuBar = new MenuBar(fileMenu);
    }

    public Menu getFileMenu() {
        return fileMenu;
    }

    public MenuItem getNewGame() {
        return newGame;
    }

    public MenuItem getCloseGame() {
        return closeGame;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}

