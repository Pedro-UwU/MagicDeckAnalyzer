package Front.Menu;


import Front.Windows.ImportDeckWindow;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class AppMenu extends MenuBar {
    public AppMenu() {
        super();


        Menu deckMenu = new Menu("Deck");
        MenuItem importDeckMenuItem = new MenuItem("Import Deck");
        importDeckMenuItem.setOnAction(event -> {
            new ImportDeckWindow().display();
        });

        deckMenu.getItems().addAll(importDeckMenuItem);
        this.getMenus().addAll(deckMenu);
    }
}
