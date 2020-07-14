package Front;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class Desk extends HBox {

    public Desk(int space, int width) {
        super(space);
        this.setPadding(new Insets(space));
        this.setWidth(width);
    }
}
