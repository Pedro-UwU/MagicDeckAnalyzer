package Front.Nodes;

import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ImageCheckBox extends HBox {
    private Image icon;
    private CheckBox checkBox = new CheckBox();
    private String value;

    public ImageCheckBox(int width, String value, Image icon) {
        super(0);
        this.value = value;
        this.icon = icon;
        ImageView imgView = new ImageView(icon);
        imgView.setPreserveRatio(true);
        imgView.setFitWidth(width/10);
        this.getChildren().addAll(checkBox, imgView);
    }

    public boolean isSelected() {
        return checkBox.isSelected();
    }

    public String getValue() {
        return value;
    }
}
